import java.util.*;

public class Task2_StockTradingPlatform {
    static class Stock {
        String name;
        int quantity;
        double price;

        Stock(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        double getValue() {
            return quantity * price;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Stock> portfolio = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. Buy Stock");
            System.out.println("2. Sell Stock");
            System.out.println("3. View Portfolio");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter stock name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = scanner.nextInt();
                    System.out.print("Enter price per stock: ");
                    double price = scanner.nextDouble();
                    portfolio.add(new Stock(name, qty, price));
                    System.out.println("Stock bought successfully.");
                    break;

                case 2:
                    System.out.print("Enter stock name to sell: ");
                    String sellName = scanner.nextLine();
                    boolean removed = portfolio.removeIf(stock -> stock.name.equalsIgnoreCase(sellName));
                    if (removed) {
                        System.out.println("Stock sold successfully.");
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;

                case 3:
                    double totalValue = 0;
                    System.out.println("\n--- Portfolio ---");
                    for (Stock s : portfolio) {
                        System.out.println(s.name + " - Qty: " + s.quantity + ", Price: " + s.price + ", Value: " + s.getValue());
                        totalValue += s.getValue();
                    }
                    System.out.println("Total Portfolio Value: $" + totalValue);
                    break;

                case 4:
                    System.out.println("Exiting platform. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
                    scanner.close();
            }
        }
        
    }
}
