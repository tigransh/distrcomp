package lesson4.ex2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Warehouse extends Remote {
    List<String> listGoods() throws RemoteException;
    String buyGood(String name, int quantity) throws RemoteException;
}