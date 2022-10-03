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

        writeFile("customer.csv", customers);
        System.out.println(readFile("customer.csv"));
    }

    public static String readFile(String filepath) {
        // Write your code here!
    }

    public static void writeFile(String filepath, List<FavoriteCustomer> customers) {
        // Write your code here!
    }
}

