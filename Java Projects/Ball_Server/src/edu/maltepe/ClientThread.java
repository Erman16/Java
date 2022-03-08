package edu.maltepe;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread extends Thread{
    Socket socket;
    float ytosend;
    float sendAngle;
    ClientThread(Socket socket, float ytosend, float sendAngle) {
        this.socket = socket;
        this.ytosend = ytosend;
        this.sendAngle = sendAngle;
    }

    public void run(){
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            SerializableBall sball = new SerializableBall();
            sball.y = ytosend;
            sball.moveAngle = sendAngle;
            outputStream.writeObject(sball);
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
