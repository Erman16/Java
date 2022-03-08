package edu.maltepe;

import javax.swing.JFrame;
/**
 * Main Program for running the bouncing ball as a standalone application.
 *
 * @author Hock-Chuan Chua
 * @version October 2010
 * modified by e gul
 */
public class Main {

    public static void main(String[] args) {
        // Run UI in the Event Dispatcher Thread (EDT), instead of Main thread
        //javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //   public void run() {
        JFrame frame = new JFrame("A World of Balls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new BallWorld(640, 480,1)); // BallWorld is a JPanel
        frame.pack();            // Preferred size of BallWorld
        frame.setVisible(true);  // Show it
        //}
        //});

        JFrame frame2 = new JFrame("A World of Balls");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setContentPane(new BallWorld(640, 480,2)); // BallWorld is a JPanel
        frame2.pack();            // Preferred size of BallWorld
        frame2.setVisible(true);  // Show it
        frame2.setLocation(640,0);
    }
}
