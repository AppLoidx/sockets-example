package main.java.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Arthur Kupriyanov
 */
public class ServerLauncher {
    private static void launch(Handler handler){
        try {
            new Thread(new Server(new ServerSocket(666)).setHandler(handler)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        // Для получения синхронизированного сервера,
        // нужно раскомментировать synchronized у метода listen(Handler handler)

        launch(new BasicHandler());


    }
}
