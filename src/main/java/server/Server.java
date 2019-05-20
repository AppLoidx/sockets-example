package main.java.server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Arthur Kupriyanov
 */
public class Server implements Runnable{
    private final ServerSocket socket;
    private Handler handler = new BasicHandler();
    private boolean isEnabled = false;

    public Server(final ServerSocket sc){
        socket = sc;
    }
    Server setHandler(Handler handler){
        if (handler!=null) this.handler = handler;

        return this;
    }
    public void setEnabled(boolean isEnabled){
        this.isEnabled = isEnabled;
    }

    private synchronized void listen(final Handler handler) throws IOException {
        try(Socket ioSocket = socket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(ioSocket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ioSocket.getOutputStream()))){
            writeData(bw, handler.getResponse(br.readLine()));
        }
    }
    private synchronized void listen() throws IOException {
        listen(this.handler);
    }

    private void writeData(BufferedWriter bw, String data) throws IOException {
            bw.write(data.toCharArray());
            bw.flush();
    }

    @Override
    public void run() {
        isEnabled = true;
        while(isEnabled){
            try {
                this.listen();
            } catch (IOException e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
