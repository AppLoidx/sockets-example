package client;

import java.io.IOException;
import java.util.List;

/**
 * @author Arthur Kupriyanov
 */
public class ClientSpamer {
    private static Thread createThread(Runnable runnable){
        return new Thread(runnable);
    }

    public static void main(String[] args) {
        List<Client> clients = ClientFactory.getClients(50, "localhost", 666);
        Runnable r = () -> clients.forEach(client -> {
                                try {
                                    System.out.println(client.sendData("s"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
        for (int i = 0; i < 100; i++) {
            createThread(r).start();
        }

    }
}
