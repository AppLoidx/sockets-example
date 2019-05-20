package client;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * @author Arthur Kupriyanov
 */
public class Client {
    private final String HOST;
    private final int PORT;


    public Client(String host, int port) {
        HOST = host;
        PORT = port;
    }

    public String sendData(String data) throws IOException {
        try (Socket clientSocket = new Socket(HOST, PORT);
             BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
             BufferedReader socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            writeData(data, socketWriter);
            return socketReader.readLine();

        }
    }

    private void writeData(String data, final BufferedWriter socketWriter) throws IOException {
            socketWriter.write(data.toCharArray());
            socketWriter.write("\n");
            socketWriter.flush();
    }

}
