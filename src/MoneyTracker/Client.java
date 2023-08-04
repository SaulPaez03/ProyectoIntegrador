package MoneyTracker;

import java.util.Arrays;
import java.util.Scanner;

public class Client {
    private  Income[] incomes ;
    private  Expenditure[] expenditures;


    public Client(Income[] incomes,Expenditure[] expenditures) {
        this.incomes = incomes;
        this.expenditures = expenditures;
    }

    public void render() {
        Scanner scanner = new Scanner(System.in);
        int selectedOption;
        do {
            System.out.println("Please select an option from the menu");
            System.out.println("1 - Show all incomes");
            System.out.println("2 - Show all expenditures");
            System.out.println("3 - Show total balance");
            System.out.println("4 - Select categories");
            System.out.println("5 - Exit application");
            selectedOption = scanner.nextInt();

            switch (selectedOption) {

                case 1 -> this.printIncomes();
                case 2 -> this.printExpenditures();
                case 3 -> this.showTotalBalance();
                case 5 -> System.out.println("Exiting application");

            }

        } while (selectedOption != 5);


    }

    private void showTotalBalance() {
        Double totalIncomes = Arrays.stream(this.incomes)
                             .reduce(0.0, (aDouble, transaction) -> transaction.sum(aDouble), Double::sum);
        Double totalSpent = Arrays.stream(this.expenditures).reduce(0.0, (aDouble, expenditure) -> expenditure.sum(aDouble), Double::sum);

        System.out.println("Total incomes: $" + totalIncomes);
        System.out.println("Total spent: $" + totalSpent);
        System.out.println("Total balance: $" + (totalIncomes + totalSpent));

    }


    private void printIncomes() {
        for (Income income : this.incomes) {
           income.print();
        }
    }

    private void printExpenditures() {
        for (Expenditure expenditure : this.expenditures) {
            expenditure.print();

        }
    }




}
