package lesson4.ex1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.util.List;

public class ShopImpl extends UnicastRemoteObject implements Shop {
    private final Warehouse warehouse;

    public ShopImpl() throws RemoteException {
        super();
        try {
            warehouse = (Warehouse) Naming.lookup(Constants.WAREHOUSE_URL);
        } catch (Exception e) {
            throw new RemoteException("Failed to connect to Warehouse", e);
        }
    }

    public List<String> listGoods() throws RemoteException {
        return warehouse.listGoods();
    }

    public String buyGood(String name, int quantity) throws RemoteException {
        return warehouse.buyGood(name, quantity);
    }
}
