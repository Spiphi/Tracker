/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.spiph.tracker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bennett.DenBleyker
 */
public class Application {

    static int serverListenerSocket = 4198;
    static final Map<Long, String> IPMAP = new HashMap();
    static final Map<Long, Long> PKEYMAP = new HashMap();

    static {
        IPMAP.put(1234L, "1.2.3.4");
        IPMAP.put(2222L, "2.2.2.2");
        IPMAP.put(666L, "6.6.6.6");
        PKEYMAP.put(1234L, 12L);
        PKEYMAP.put(2222L, 22L);
        PKEYMAP.put(666L, 66L);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(serverListenerSocket);
        } catch (IOException ex) {
            System.out.println("Could not bind to port: " + serverListenerSocket);
        }
        System.out.println("Starting Server");
        long total = 0;
        while (true) {
            try {
                Socket socket = listener.accept();
                System.out.println("Connecting to client (Total: " + ++total + ")");
                TrackerThread tracker = new TrackerThread(socket);
                tracker.run();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
