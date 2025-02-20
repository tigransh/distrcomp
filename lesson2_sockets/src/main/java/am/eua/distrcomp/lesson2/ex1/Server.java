package am.eua.distrcomp.lesson2.ex1;

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

    public static void main(String[] args) {
        try {
            //create a server socket to listen on given port
            ServerSocket serverSocket = new ServerSocket(PORT);

            //accept a connection from a client
            final Socket socket = serverSocket.accept();

            //open an output stream to the socket writing
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            //open an input stream to the socket for reading
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //read a line of the received message
            String message = in.readLine();
            while (!message.equals(END_OF_CONNECTION_MARKER)) {
                //print out the received line into the console
                System.out.println("<<Server>> : message received: " + message);

                //write a response to the socket
                out.println(processMessage(message));
                //force the written buffer to be sent
                out.flush();
                message = in.readLine();
            }
            System.out.println("End of connection initiated!");
            //close the socket
            socket.close();
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


