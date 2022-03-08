package edu.maltepe;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int port = 4999;
    private ServerSocket ss = null;

    public void runServer() throws IOException, ClassNotFoundException {
        ss = new ServerSocket(port);
        System.out.println("Server is ready to accept connections");
        while (true) {
            Socket socket = ss.accept();
            new ServerThread(socket).start();
        }
    }
}
