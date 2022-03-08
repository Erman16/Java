package edu.maltepe;

import javax.swing.JFrame;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        JFrame frame1 = new JFrame("A World of Balls2");

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setContentPane(new BallWorld(640, 480)); // BallWorld is a JPanel
        frame1.pack();            // Preferred size of BallWorld
        frame1.setVisible(true);  // Show it
        frame1.setLocation(640, 0);

    }
}