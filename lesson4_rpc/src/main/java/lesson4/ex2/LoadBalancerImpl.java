package lesson4.ex2;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadBalancerImpl extends UnicastRemoteObject implements Shop {
    private final Shop[] shops;
    private final AtomicInteger counter;

    public LoadBalancerImpl() throws RemoteException {
        super();
        try {
            shops = new Shop[]{
                    (Shop) Naming.lookup(Constants.SHOP1_URL),
                    (Shop) Naming.lookup(Constants.SHOP2_URL)
            };
            counter = new AtomicInteger(0);
        } catch (Exception e) {
            throw new RemoteException("Failed to initialize LoadBalancer", e);
        }
    }

    private Shop getNextShop() {
        int index = counter.getAndUpdate(i -> (i + 1) % shops.length);
        System.out.println("Will redirect request to shop " + index);
        return shops[index];
    }

    public List<String> listGoods() throws RemoteException {

        return getNextShop().listGoods();
    }

    public String buyGood(String name, int quantity) throws RemoteException {
        return getNextShop().buyGood(name, quantity);
    }
}

