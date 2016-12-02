Det finns en del oklarheter i instruktionerna. Ordet "backend" antyder för mig
en separat tjänst som kör på en annan maskin, eller åtminstone i en annan
process om det är på samma maskin.

Det som beskrivs är dock Java-klasser och -gränssnitt. Det antyder för mig att
uppgiften är att skriva affärslogiken (a.k.a "modellen" - M:et i MVC), inte en
fullskalig backend med HTTP-gränssnitt. Frontend-teamet antas implementera V-
och C-delarna.

Det står i beskrivningen att "ett annat frontend team är försenade och ska
koppla in sitt gränssnitt mot backendet du bygger," vilket jag tolkar som att
gränssnittet fortfarande kan omförhandlas. Mitt förslag blir då som följer:

## Min design

En bok har inget beteende i detta sammanhanget. Vi ska inte t.ex. läsa böcker.
Därför är det rimligt att implementera `Book` som en enkel data-struktur
(‘immutable’, med publika fält):

```java
public final class BookID {
    public final String isbn;
    public BookID(String isbn) {
        // ...
    }
}

public final class Book {
    public final BookID id;
    public final String title;
    public final String author;
    public final BigDecimal price;
    public Book(BookID id, String title, String author, BigDecimal price) {
        // ...
    }
}
```

Det står inget i beskrivningen om hur varukorgen ska sparas. Låt oss tills
vidare anta att den inte behöver sparas och att man helt enkelt skapar den
i frontend. En representation ska dock skickas till "backend." Förmodligen
räcker det med en `java.util.Collection<OrderLineItem>`:

```java
public final class OrderLineItem {
    public final BookID bookID;
    public final int quantity;
}

public class BookStore {
    void buy(java.util.Collection<LineItem> books)
            throws NoSuchBookException, NotInStockException;
    // ...
}
```

I uppgiftsbeskrivningen står det att `buy()` ska returnera en `int[]` med 
värdena `OK`, `NOT_IN_STOCK` och `DOES_NOT_EXIST`. Det är ett vanligt sätt
att returnera en status i C-kod, men Java har exceptions som är mycket bättre
att använda för att rapportera fel. För att ta reda på status för en specifik bok
kan vi lägga till ett API specifikt för det:

```java
public class BookStore {
    int getStockQuantity(BookID bookID)
            throws NoSuchBookException;
    // ...
}
```

Man ska också kunna söka efter böcker. Min erfarenhet är att Java-arrayer tenderar
att skapa komplex kod. Därför ändrar jag retur-värdet för `list()`-metoden:

```java
public class BookStore {
    java.util.Set<Book> list(String searchString);
    // ...
}
```

Hela `BookStore`-gränssnittet blir som följer:

```java
public class BookStore {
    void buy(java.util.Collection<LineItem> books)
            throws NoSuchBookException, NotInStockException;
    int getStockQuantity(BookID bookID)
            throws NoSuchBookException;
    java.util.Set<Book> list(String searchString);
}
```

För att köpa, måste man veta totalpris inklusive frakt mm. och göra enbetalning.
Hur detta gör till beskrivs inte i uppgiften, så jag antar att det ska läggas till
senare. Eftersom jag inte har de detaljerna väljer jag i sann "agile"-anda att
ignorera dem.

Notera att om det inte beskrevs ett separat frontend-team hade jag undvikit en
så här noggrann upfront-design. Enligt TDD ska designen göras inkrementellt och
förändras med tiden allteftersom man lär sig av att utföra implementationen. Har
man ett separat team antar jag att det tar tid att kommunicera med dem och att
man därför behöver bestämma en ganska detaljerad design innan arbetet påbörjas.

----

Sök-funktionen är inte väl testad. Algoritmen är implementerad i testet, inte i
BookStore. Jag känner att det kan bli svårt att hitta en algoritm som
implementeras av BookStore utan att generera SQL-frågor, och SQL-frågor kan
inte testas utan en databas. Se det skulle inte längre vara enhetstest.

----

Konsoll-appen har inga enhetstest. Den kan bara köras manuellt. Så det är så
jag har testat den. Anledningen är att den bara skulle vara en stand-in för
frontend-teamets arbete.
