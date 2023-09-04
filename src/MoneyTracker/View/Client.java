package MoneyTracker.View;

import MoneyTracker.Controllers.CategoriesController;
import MoneyTracker.Controllers.TransactionsController;
import MoneyTracker.DAL.DatabaseConnection;
import MoneyTracker.DTOs.CategoryDTO;
import MoneyTracker.DTOs.TransactionDTO;

import java.time.LocalDate;
import java.util.*;

public class Client {


    private final TransactionsController transactionsController;
    private final CategoriesController categoriesController;

    public Client() {
        this.categoriesController = new CategoriesController();
        this.transactionsController = new TransactionsController();
    }


    public void start() {

        DatabaseConnection.getDatabaseConnection();

        this.renderMenu();
    }


    private void renderMenu() {
        Scanner scanner = new Scanner(System.in);
        int selectedOption;
        do {
            System.out.println("Please select an option from the menu");
            System.out.println("1 - Add transaction");
            System.out.println("2 - Show all transactions");
            System.out.println("3 - Manage categories");
            System.out.println("4 - Show transactions by category");
            System.out.println("5 - Exit application");
            selectedOption = scanner.nextInt();

            switch (selectedOption) {

                case 1 -> this.addTransaction();
                case 2 -> this.showTransactions();
                case 3 -> this.manageCategories();
                case 4 -> this.showTransactionsByCategory();
                case 5 -> System.out.println("Exiting application");

            }

        } while (selectedOption != 5);


    }

    private void addTransaction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the data for the new transaction");
        System.out.println("Enter the transaction amount (use negative values for expenditures)");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter a brief description or note for the transaction");
        String note = scanner.nextLine();
        System.out.println("Do you want to add a category to the transaction?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        Boolean shouldAddCategory = null;
        do {
            int questionResult = scanner.nextInt();
            scanner.nextLine();

            switch (questionResult) {
                case 1 -> shouldAddCategory = true;
                case 2 -> shouldAddCategory = false;
                default -> System.out.println("Select a valid option");
            }

        } while (shouldAddCategory == null);

        String categoryName = shouldAddCategory ? this.selectCategory() : null;
        boolean added = this.transactionsController.saveTransaction(amount, note, LocalDate.now(), categoryName);
        if (added) System.out.println("Transaction added");


    }

    private String selectCategory() {
        List<CategoryDTO> categories = this.categoriesController.getAllCategories();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a category");
        do {
            int selectedOption;


            int index = 0;
            for (CategoryDTO c : categories) {
                System.out.printf("%1$s - %2$s%n", ++index, c.name);
            }
            System.out.printf("%s - Cancel\r\n", categories.size() + 1);

            selectedOption = scanner.nextInt();
            scanner.nextLine();

            if (selectedOption > (categories.size() + 1) || selectedOption < 1) {
                System.out.println("Please insert a valid value");
                continue;
            }
            if (selectedOption == categories.size() + 1) return null;
            return categories.get(selectedOption - 1) != null ? categories.get(selectedOption - 1).name : null;


        } while (true);


    }


    private void showTransactions() {
        List<TransactionDTO> transactions = this.transactionsController.getAllTransactions();
        transactions.forEach(TransactionDTO::print);
    }

    private void manageCategories() {
        System.out.println("What do you want to do?");
        System.out.println("1 - Add category");
        System.out.println("2 - Delete category");
        System.out.println("3 - Go back");
        Scanner scanner = new Scanner(System.in);

        int selectedOption;


        while (true) {
            selectedOption = scanner.nextInt();
            scanner.nextLine();

            switch (selectedOption) {
                case 1 -> {
                    addCategory();
                    return;
                }

                case 2 -> {
                    deleteCategory();
                    return;
                }
                case 3 -> {
                    return;
                }
                default -> System.out.println("Select a valid option");
            }


        }
    }

    private void addCategory() {
        System.out.println("Enter the name for the new category");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        boolean added = this.categoriesController.addCategory(name);
        String response = added ? "Category added" : "Something went wrong adding the category, try again";
        System.out.println(response);
    }

    private void deleteCategory() {
        System.out.println("Which category do you want to delete?");
        int index = 0;
        List<CategoryDTO> categories = this.categoriesController.getAllCategories();
        listCategories(categories, index);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int selectedOption = scanner.nextInt();
            scanner.nextLine();
            if ((selectedOption > index) || (selectedOption < 1)) {
                System.out.println("Select a valid option");
                continue;
            } else if (selectedOption == ++index) {
                return;
            }
            boolean deleted = this.categoriesController.deleteCategory(categories.get(selectedOption - 1).id) >= 1;

            if (deleted)
                System.out.printf("Category %1$s has been deleted\r\n", categories.get(selectedOption - 1).name);
            else System.out.println("Could not delete the category. Try again later");
            return;
        }

    }

    private static int listCategories(List<CategoryDTO> categories, int index) {
        for (CategoryDTO c : categories) {
            System.out.printf("%1$s - %2$s%n\r", ++index, c.name);
        }
        System.out.printf("%s - Go back\r\n", ++index);
        return index;
    }

    private void showTransactionsByCategory() {
        List<CategoryDTO> categories = categoriesController.getAllCategories();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What category do you want to see?");
        int index = 0;
        index = listCategories(categories, index);
        int selectedOption;

        do {
            selectedOption = scanner.nextInt();
            scanner.nextLine();

            if (selectedOption > index || selectedOption < 1) {
                System.out.println("Select a  valid option");
            } else if (selectedOption == index) {
                return;
            }
        } while (selectedOption > index || selectedOption < 1);
        CategoryDTO selectedCategory = categories.get(selectedOption - 1);
        List<TransactionDTO> transactions = this.transactionsController.getTransactionByCategoryID(selectedCategory.id);

        System.out.println("Selected category: "+ selectedCategory.name);
        if (!transactions.isEmpty()) {
            System.out.println("Transactions:");
        } else {
            System.out.println("No transactions with this category");
        }
        transactions.forEach(TransactionDTO::print);

    }


}
