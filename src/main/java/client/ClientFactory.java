package main.java.client;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arthur Kupriyanov
 */
public class ClientFactory {
    static List<Client> getClients(int count, String host, int port){
        List<Client> list = new ArrayList<>();
        while(count-->0){
            list.add(new Client(host, port));
        }
        return list;
    }
}
