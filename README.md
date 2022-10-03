# JSON Lab

## Learning Goals

- Work with JSON files in Java.

## Instructions

Reusing the `CustomerDriver` and `FavoriteCustomer` classes from the CSV lab,
modify the code to read and write JSON formatted files instead of CSV formatted
files. The JSON file format would look like this:

```json
[
  {
    "firstName": "Leslie",
    "lastName": "Knope",
    "mealChoice": "waffles with whipped cream"
  }
]
```

To implement the reading and writing of a JSON file, consider the following
instructions and tips:

- Add the Jackson library to the module.
- Write the method to write to a JSON file.
  - Use the `ObjectMapper` class to write to a JSON file.
    - Refer back to the JSON lesson.
- Write the method to read from a JSON file.
  - Either add a default constructor to the `FavoriteCustomer` class or add
    Jackson annotations to an existing constructor.
  - Use the `ObjectMapper` class to read from a JSON file.
    - Refer back to the JSON lesson.
    - Read in an array of `FavoriteCustomer` objects.
  - Return a `String` to print out to the console the `FavoriteCustomer` objects
    that were read in from the JSON.
    - The output should look like:
      - `Leslie Knope always orders the waffles with whipped cream`
- Remove the `@Disabled` annotation from the unit tests and ensure that the tests
  pass successfully still. Refactor as necessary.
12345678910111213141516171819202122232425262728293031323334353637383940404143454

## Starter Code

Consider the following starter code:

```java
public class FavoriteCustomer {
    private String firstName;
    private String lastName;
    private String mealChoice;

    public FavoriteCustomer(String firstName, String lastName, String mealChoice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mealChoice = mealChoice;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMealChoice() {
        return mealChoice;
    }

    public void setMealChoice(String mealChoice) {
        this.mealChoice = mealChoice;
    }

    @Override
    public String toString() {
        return String.format("%s %s always orders the %s",
                firstName,
                lastName,
                mealChoice);
    }
}
```

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDriver {

    public static void main(String[] args) {
        List<FavoriteCustomer> customers = new ArrayList<FavoriteCustomer>();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            System.out.println("What is the first name of the favorite customer?");
            String firstName = scanner.nextLine();
            System.out.println("What is the last name of the favorite customer?");
            String lastName = scanner.nextLine();
            System.out.println("What is the meal choice of the favorite customer?");
            String mealChoice = scanner.nextLine();
            customers.add(new FavoriteCustomer(firstName, lastName, mealChoice));
            System.out.println("Add another customer? (type \"no\" to exit)");
            input = scanner.nextLine();
            System.out.println();
        } while(!input.equalsIgnoreCase("no"));

        writeFile("customer.json", customers);
        System.out.println(readFile("customer.json"));
    }

    public static String readFile(String filepath) {
        // Write your code here!
    }

    public static void writeFile(String filepath, List<FavoriteCustomer> customers) {
        // Write your code here!
    }
}
```

## Unit Tests

Consider the following unit tests to help you implement the `writeFile()` and
`readFile()` methods:

```java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDriverTest {

    FavoriteCustomer ron;
    FavoriteCustomer leslie;
    FavoriteCustomer ben;
    List<FavoriteCustomer> customers;

    @BeforeEach
    void setUp() {
        ron = new FavoriteCustomer(
                "Ron",
                "Swanson",
                "four-horse meals of the egg-pork-alypse"
        );
        leslie = new FavoriteCustomer(
                "Leslie",
                "Knope",
                "waffles with whipped cream"
        );
        ben = new FavoriteCustomer(
                "Ben",
                "Wyatt",
                "calzone"
        );

        customers = new ArrayList<FavoriteCustomer>();
    }

    @AfterEach
    void tearDown() {
    }

    @Disabled
    @Test
    void testReadOneCustomer() {
        String filename = "test-customer1.json";
        assertEquals(ron.toString(), CustomerDriver.readFile(filename));
    }

    @Disabled
    @Test
    void testReadMultipleCustomers() {
        String filename = "test-customer2.json";
        String expectedOutput = String.format("%s%n%s%n%s", ron.toString(), leslie.toString(), ben.toString());
        assertEquals(expectedOutput, CustomerDriver.readFile(filename));
    }

    @Disabled
    @Test
    void testReadNoCustomers() {
        String filename = "test-customer3.json";
        CustomerDriver.writeFile(filename, customers);
        assertEquals("", CustomerDriver.readFile(filename));
    }

    @Disabled
    @Test
    void testWriteOneCustomer() {
        Path path = Paths.get("test-customer4.json");
        customers.add(ben);
        CustomerDriver.writeFile(path.toString(), customers);
        String expectedOutput = "[ {\n" +
                "  \"firstName\" : \"Ben\"," +
                "\n  \"lastName\" : \"Wyatt\"," +
                "\n  \"mealChoice\" : \"calzone\"" +
                "\n} ]";
        try {
            assertEquals(expectedOutput, Files.readString(path));
        } catch (IOException ioException) {
            fail("Caught exception in testWriteOneCustomer - fail test.");
        }
    }

    @Disabled
    @Test
    void testWriteMultipleCustomers() {
        Path path = Paths.get("test-customer5.json");
        customers.add(ben);
        customers.add(leslie);
        customers.add(ron);
        CustomerDriver.writeFile(path.toString(), customers);
        String expectedOutput = "[ {\n" +
                "  \"firstName\" : \"Ben\"," +
                "\n  \"lastName\" : \"Wyatt\"," +
                "\n  \"mealChoice\" : \"calzone\"" +
                "\n}, {\n" +
                "  \"firstName\" : \"Leslie\"," +
                "\n  \"lastName\" : \"Knope\"," +
                "\n  \"mealChoice\" : \"waffles with whipped cream\"" +
                "\n}, {\n" +
                "  \"firstName\" : \"Ron\"," +
                "\n  \"lastName\" : \"Swanson\"," +
                "\n  \"mealChoice\" : \"four-horse meals of the egg-pork-alypse\"" +
                "\n} ]";
        try {
            assertEquals(expectedOutput, Files.readString(path));
        } catch (IOException ioException) {
            fail("Caught exception in testWriteMultipleCustomers - fail test.");
        }
    }

    @Disabled
    @Test
    void testWriteNoCustomers() {
        Path path = Paths.get("test-customer6.json");
        CustomerDriver.writeFile(path.toString(), customers);
        try {
            assertEquals("[ ]", Files.readString(path));
        } catch (IOException ioException) {
            fail("Caught exception in testWriteNoCustomers - fail test.");
        }
    }
}
```
