import MoneyTracker.*;

public class Main {
    public static void main(String[] args) {
        Transaction[] transactions = new Transaction[]{
                new Income(5, "Test"),
                new Expenditure(10, "Test")
        };
        Category[] categories = new Category[]{
          new Category("Test Category")
        };

        Client client = new Client(transactions, categories);

        client.render();


    }
}