package edu.maltepe;

import javax.swing.JFrame;
import java.io.IOException;

public class Main {
    protected static BallWorld bw;
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        JFrame frame1 = new JFrame("A World of Balls1");

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setContentPane(bw = new BallWorld(640, 480)); // BallWorld is a JPanel
        frame1.pack();            // Preferred size of BallWorld
        frame1.setVisible(true);  // Show it
        frame1.setLocation(0, 0);
        new Server().runServer();

    }
}
