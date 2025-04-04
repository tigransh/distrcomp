package lesson4.ex2;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class ShopClient {
    public static void main(String[] args) {
        try {
            Shop shop = (Shop) Naming.lookup(Constants.LB_URL);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n== Online Shop Menu ==");
                System.out.println("1. List Goods");
                System.out.println("2. Buy Good");
                System.out.println("3. Exit");
                System.out.print("Choice: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        List<String> items = shop.listGoods();
                        for (String item : items) System.out.println(item);
                        break;
                    case "2":
                        System.out.print("Enter item name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        String result = shop.buyGood(name, qty);
                        System.out.println(result);
                        break;
                    case "3":
                        System.out.println("Exiting.");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

