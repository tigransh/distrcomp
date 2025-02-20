package am.eua.distrcomp.lesson2.ex4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    //Server's IP
    public static final String HOST = "127.0.0.1";
    //Server's port
    public static final int PORT = 3333;
    public static final String END_OF_CONNECTION_MARKER = "END!";

    private Socket socket;

    public void initialize(){
        try {
            //open a socket
            socket = new Socket(HOST, PORT);
        }
        //handle exceptions
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void send(String message) {
        try {
            //open a socket
//            Socket socket = new Socket(HOST, PORT);

            //open an output stream to the socket writing
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //open an input stream to the socket for reading
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //write a message to the socket
            out.println(message);
            //force the written buffer to be sent
            out.flush();

            System.out.println("<<Client>>: message sent: " + message);

            //read a line of the response
            String response = in.readLine();
            //print out the response to the console
            System.out.println("<<Client>>: response received: " + response);

        }
        //handle exceptions
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.initialize();
        client.send("HISTORY");
        client.send("1+1");
        client.send("1+2");
        client.send("HISTORY");
        client.send("5-2");
        client.send("136/2");
        client.send("222/2");
        client.send("456*34");
        client.send("HISTORY");
        client.send(END_OF_CONNECTION_MARKER);
    }

}
