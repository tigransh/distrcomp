package lesson4.ex2;

import java.rmi.Naming;

public class WarehouseServer {
    public static void main(String[] args) {
        try {
            WarehouseImpl warehouse = new WarehouseImpl();
            Naming.rebind(Constants.WAREHOUSE_URL, warehouse);
            System.out.println("Warehouse server ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}