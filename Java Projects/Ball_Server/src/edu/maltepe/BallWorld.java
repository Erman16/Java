package edu.maltepe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BallWorld extends JPanel{

    public static ArrayList<Ball> generate = new ArrayList<>();  //Generate Arraylist  //liste oluşturur...
    int rectanglecount=0,ellipsecount=0,ballcount=0,arccount=0,totalcount=0;

    private static final int UPDATE_RATE = 30;
    private final ContainerBox box;
    private int canvasWidth;
    private int canvasHeight;

    public BallWorld(int width, int height){
        canvasWidth = width;
        canvasHeight = height;

        Random rand = new Random();
        int radius = 20;
        int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
        int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
        int speed = 5;
        int angleInDegree = rand.nextInt(360);
        // Init the ball at a random location (inside the box) and moveAngle

        Random rand1 = new Random();                  //Random kütüphanesi olmadan çalışmaz.
        int size = rand1.nextInt(1,5);  //Girilen değerler arasında rastgele bir sayı verir.

        for(int i = 0; i <= size; i++){              //yukarıda oluşturulan rastgele sayıya kadar döngü

            Random rand2 = new Random();                   //yeni bir rastgele sayı için
            int size2 = 2; //rand1.nextInt(0,4);

            int random_speed = rand2.nextInt(1,10);
            int random_angle = rand2.nextInt(1,361);
            int randomX = rand2.nextInt(0,200);
            int randomY = rand2.nextInt(0,200);
/*
            if(size2 == 0){
                //generate Rectangle
                generate.add(new Rectangle(x+randomX,y+randomY,radius,speed+random_speed,angleInDegree+random_angle,Color.BLUE));  // rectangle üretir ve listeye ekler...
                rectanglecount++;
            }
            else if (size2 == 1){
                //generate Ellipse
                generate.add(new Ellipse(x+randomX,y+randomY,radius,speed+random_speed,angleInDegree+random_angle,Color.YELLOW));  // Ellipse üretir ve listeye ekler...
                ellipsecount++;
            }*/
            /*else*/ if(size2 == 2){
                //generate Ball
                generate.add(new Ball(x+randomX,y+randomY,radius,speed+random_speed,angleInDegree+random_angle, Color.red));  // Ball üretir ve listeye ekler...
                ballcount++;
            }/*
            else if(size2 == 3){
                //generate Arc
                generate.add(new Arc(x+randomX,y+randomY,radius,speed+random_speed,angleInDegree+random_angle,Color.RED));  // Arc üretir ve listeye ekler...
                arccount++;
            }*/
            totalcount++;
        }
        System.out.println("TOTAL COUNT: " + totalcount);
        System.out.println("Ball count: " + ballcount);
        System.out.println("Rectangle count: " + rectanglecount);
        System.out.println("Ellipse count: " + ellipsecount);
        System.out.println("Arc count: " + arccount);
        System.out.println("--------------------------");

        box = new ContainerBox(0, 0, canvasWidth, canvasHeight, Color.DARK_GRAY, Color.BLACK);

        DrawCanvas canvas = new DrawCanvas();
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component c = (Component)e.getSource();
                Dimension dim = c.getSize();
                canvasWidth = dim.width;
                canvasHeight = dim.height;
                // Adjust the bounds of the container to fill the window
                box.set(0, 0, canvasWidth, canvasHeight);
            }
        });

        gameStart();


    }

    public void gameStart(){
        gameThread gmthr = new gameThread(this,UPDATE_RATE,box);
        gmthr.start();
    }

    public void gameUpdate() throws IOException {
        for (int i=0;i<generate.size();i++) {  //listemizin boyutuna kadar döngüyü dndürüyoruz bylelikle tüm elemanları tek tek çağırıyoruz
            generate.get(i).moveOneStepWithCollisionDetection(box);  // her bir çağırılan eleman için move metodunu aktifleştirerek tüm elemanlarımız hareketini sağıyoruz
        }
    }
    class DrawCanvas extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);    // Paint background
            // Draw the box and the ball
            box.draw(g);
            for (Ball ball : generate) { //listemizin boyutuna kadar döngüyü dndürüyoruz bylelikle tüm elemanları tek tek çağırıyoruz
                ball.draw(g); // her bir çağırılan eleman için draw metodunu aktifleştirerek tüm elemanlarımız çizilmesini sağıyoruz
            }
        }

        public Dimension getPreferredSize() {
            return (new Dimension(canvasWidth, canvasHeight));
        }
    }

}
