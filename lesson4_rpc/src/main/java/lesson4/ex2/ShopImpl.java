package lesson4.ex2;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ShopImpl extends UnicastRemoteObject implements Shop {
    private String serviceName;
    private final Warehouse warehouse;

    public ShopImpl(String name) throws RemoteException {
        super();
        this.serviceName = name;
        try {
            warehouse = (Warehouse) Naming.lookup(Constants.WAREHOUSE_URL);
        } catch (Exception e) {
            throw new RemoteException("Failed to connect to Warehouse", e);
        }
    }

    public List<String> listGoods() throws RemoteException {
        System.out.println("*** "+ serviceName +".listGoods called");
        return warehouse.listGoods();
    }

    public String buyGood(String name, int quantity) throws RemoteException {
        System.out.println("*** "+ serviceName +".buyGoods called");
        return warehouse.buyGood(name, quantity);
    }
}
