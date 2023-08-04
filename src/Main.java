import MoneyTracker.*;

public class Main {
    public static void main(String[] args) {
        Income[] incomes = new Income[]{
                new Income(5, "Test","Salary")};

        Expenditure[] expenditures = new Expenditure[]{
                new Expenditure(10, "Test")

        };

        Client client = new Client(incomes, expenditures);

        client.render();


    }
}