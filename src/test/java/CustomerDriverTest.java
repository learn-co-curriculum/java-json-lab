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