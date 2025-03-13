package lesson4.ex1;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ShopServer {
    public static void main(String[] args) {
        try {
//            LocateRegistry.createRegistry(1100); // separate registry for shop
            ShopImpl shop = new ShopImpl();
            Naming.rebind(Constants.SHOP_URL, shop);
            System.out.println("Shop server ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}