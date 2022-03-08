package edu.maltepe;

import java.io.IOException;

public class gameThread extends Thread implements Runnable{
    BallWorld bw;
    int updaterate;

    public gameThread(BallWorld bw , int updaterate) {
        this.bw = bw;
        this.updaterate =updaterate;
    }

    public void run() {
        while (true) {
            // Execute one time-step for the game
            try {
                bw.gameUpdate();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Refresh the display
            bw.repaint();
            // Delay and give other thread a chance
            try {
                Thread.sleep(1000 / updaterate);
            } catch (InterruptedException ex) {
            }
        }
    }
}
