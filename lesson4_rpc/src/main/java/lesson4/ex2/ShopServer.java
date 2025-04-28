package lesson4.ex2;

import java.rmi.Naming;

public class ShopServer {
    public static void main(String[] args) {
        try {
//            LocateRegistry.createRegistry(1100); // separate registry for shop
            ShopImpl shop1 = new ShopImpl("shop1");
            ShopImpl shop2 = new ShopImpl("shop2");
            Naming.rebind(Constants.SHOP1_URL, shop1);
            Naming.rebind(Constants.SHOP2_URL, shop2);
            LoadBalancerImpl loadBalancer = new LoadBalancerImpl();
            Naming.rebind(Constants.LB_URL, loadBalancer);
            System.out.println("Shop servers are ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}