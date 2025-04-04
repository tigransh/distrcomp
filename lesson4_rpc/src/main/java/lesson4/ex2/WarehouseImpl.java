package lesson4.ex2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class WarehouseImpl extends UnicastRemoteObject implements Warehouse {
    private final Map<String, Good> goods;

    public WarehouseImpl() throws RemoteException {
        super();
        goods = new HashMap<>();
        goods.put("Book", new Good("Book", 10.0, 20));
        goods.put("Laptop", new Good("Laptop", 800.0, 5));
        goods.put("Pen", new Good("Pen", 1.5, 100));
    }

    public synchronized List<String> listGoods() throws RemoteException {
        List<String> list = new ArrayList<>();
        for (Good g : goods.values()) {
            list.add(g.toString());
        }
        return list;
    }

    public synchronized String buyGood(String name, int quantity) throws RemoteException {
        Good g = goods.get(name);
        if (g == null) return "Item not found.";
        if (g.getQuantity() < quantity) return "Not enough in stock.";
        g.reduceQuantity(quantity);
        return "Purchase successful: " + quantity + " x " + name + " ($" + (g.getPrice() * quantity) + ")";
    }
}
