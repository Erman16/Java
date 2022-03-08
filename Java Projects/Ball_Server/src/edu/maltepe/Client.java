package edu.maltepe;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        float Y = Float.parseFloat(args[0]);
        float angle = Float.parseFloat(args[1]);
        Socket socket = new Socket("localhost", 4321);
        System.out.println("Client connected");
        new ClientThread(socket,Y,angle).start();

    }
}
