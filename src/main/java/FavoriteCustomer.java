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
