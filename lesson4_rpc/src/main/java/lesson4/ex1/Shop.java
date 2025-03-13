package lesson4.ex1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Shop extends Remote {
    List<String> listGoods() throws RemoteException;
    String buyGood(String name, int quantity) throws RemoteException;
}
