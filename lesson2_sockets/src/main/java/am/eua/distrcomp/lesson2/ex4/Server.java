package am.eua.distrcomp.lesson2.ex4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.BiFunction;

public class Server {
    public static final int PORT = 3333;
    public static final String END_OF_CONNECTION_MARKER = "END!";
    public static final String HISTORY_COMMAND = "HISTORY";

    private static StringBuilder history = new StringBuilder(); // Stores calculation history

    public static void main(String[] args) {
        try {
            //create a server socket to listen on given port
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running...");

            //accept a connection from a client
            final Socket socket = serverSocket.accept();

            //open an output stream to the socket writing
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            //open an input stream to the socket for reading
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //read a line of the received message
            String message;
            while ((message = in.readLine()) != null) {
                if (message.equals(END_OF_CONNECTION_MARKER)) {
                    break;
                }
                if (message.equals(HISTORY_COMMAND)) {
                    out.println("History:" + history.toString());
                    out.println(history.toString());
                    out.flush();
                }
                else {
                    int result = processMessage(message);
                    String log = message + " = " + result;
                    history.append(log).append(";"); // Store in history
                    out.println(result);
                    //force the written buffer to be sent
                    out.flush();

                }

            }
            //close the socket
            socket.close();
            System.out.println("Connection closed.");
        }
        //handle exceptions
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Process the message and return the result by doing a simple calculation
     *
     * @param message - a simple formula of the form “x*y”, where x and y are integers and
     *                * is one of the binary operations: +,-,*,/ .
     * @return the result of the calculation
     */
    private static Integer processMessage(String message) {
        if (message.contains("+")) {
            return calculate(message.split("\\+"), (x, y) -> x + y);
        } else if (message.contains("-")) {
            return calculate(message.split("\\-"), (x, y) -> x - y);
        } else if (message.contains("*")) {
            return calculate(message.split("\\*"), (x, y) -> x * y);
        } else if (message.contains("/")) {
            return calculate(message.split("/"), (x, y) -> x / y);
        }
        return null;

    }

    private static Integer calculate(String[] params, BiFunction<Integer, Integer, Integer> f) {
        return f.apply(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
    }

}


