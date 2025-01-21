import java.util.*;
import java.io.*;

class Expense {
    String category;
    double amount;
    String date;
    String description;

    public Expense(String category, double amount, String date, String description) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nDate: " + date + ", \nCategory: " + category + ", \nAmount: " + amount + ", \nDescription: " + description;
    }
}

class ExpenseManager {
    List<Expense> expenses = new ArrayList<>();

    public void addExpense(String category, double amount, String date, String description) {
        expenses.add(new Expense(category, amount, date, description));
    }

    public void showAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    public void filterByCategory(String category) {
        boolean found = false;
        for (Expense e : expenses) {
            if (e.category.equalsIgnoreCase(category)) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found in category: " + category);
        }
    }

    public void exportDataToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Expense e : expenses) {
            writer.write(e.toString());
            writer.newLine();
        }
        writer.close();
        System.out.println("Expenses exported to " + fileName);
    }
}


public class ExpenseTracker {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        while (true) {
            System.out.println("1. Add Expense\n2. Show All Expenses\n3. Filter by Category\n4. Export Data\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine(); 
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    System.out.print("Enter Description: ");
                    String description = sc.nextLine();
                    manager.addExpense(category, amount, date, description);
                    break;
                case 2:
                    manager.showAllExpenses();
                    break;
                case 3:
                    System.out.print("Enter Category to Filter: ");
                    String filterCategory = sc.nextLine();
                    manager.filterByCategory(filterCategory);
                    break;
                case 4:
                    System.out.print("Enter File Name to Export: ");
                    String fileName = sc.nextLine();
                    manager.exportDataToFile(fileName);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
