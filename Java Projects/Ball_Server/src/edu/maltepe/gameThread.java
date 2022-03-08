package edu.maltepe;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class gameThread extends Thread implements Runnable{
    BallWorld bw;
    int updaterate;
    ContainerBox box;
    Client client;
    public gameThread(BallWorld bw , int updaterate, ContainerBox box) {
        this.bw = bw;
        this.updaterate = updaterate;
        this.box = box;
    }

    public void run() {
        while (true) {
            // Execute one time-step for the game
            for(Ball ball : BallWorld.generate){
                if (ball == null) {return;}
                try {
                    bw.gameUpdate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Refresh the display
                bw.repaint();

                try {
                    if (ball.x == 0 + ball.radius){
                        System.out.println("hit left wall");
                        String YtoSend = String.valueOf(ball.y);
                        float angle = ball.getMoveAngle();
                        String sendAngle = String.valueOf(angle);
                        String[] arguments = new String[] {YtoSend,sendAngle};
                        ball.deleteBall();
                        bw.repaint();
                        interrupt();
                        client.main(arguments);
                    }
                    Thread.sleep(1000 / updaterate);
                } catch (InterruptedException ex) {

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}