package edu.maltepe;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Formatter;

public class Ball {

    float x, y;           // Ball's center x and y (package access)
    float speedX, speedY; // Ball's speed per step in x and y (package access)
    float radius;         // Ball's radius (package access)
    public Color color;  // Ball's color

    public Ball(float x, float y, float radius, float speed, float angleInDegree,Color color){
        this.x = x;
        this.y = y;
        // Convert (speed, angle) to (x, y), with y-axis inverted
        this.speedX = (float)(speed * Math.cos(Math.toRadians(angleInDegree)));
        this.speedY = -speed * (float)Math.sin(Math.toRadians(angleInDegree));
        this.radius = radius;
        this.color = color;
    }

    public void moveOneStepWithCollisionDetection(ContainerBox box) {
        // Get the ball's bounds, offset by the radius of the ball
        float ballMinX = box.minX + radius;
        float ballMinY = box.minY + radius;
        float ballMaxX = box.maxX - radius;
        float ballMaxY = box.maxY - radius;

        // Calculate the ball's new position
        x += speedX;
        y += speedY;
        // Check if the ball moves over the bounds. If so, adjust the position and speed.
        if (x < ballMinX) {
            speedX = -speedX; // Reflect along normal
            x = ballMinX;     // Re-position the ball at the edge
        } else if (x > ballMaxX) {
            speedX = -speedX;
            x = ballMaxX;
        }
        // May cross both x and y bounds
        if (y < ballMinY) {
            speedY = -speedY;
            y = ballMinY;
        } else if (y > ballMaxY) {
            speedY = -speedY;
            y = ballMaxY;
        }
    }


    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
    }

    public float getSpeed() {
        return (float)Math.sqrt(speedX * speedX + speedY * speedY);
    }

    public float getMoveAngle() {
        return (float)Math.toDegrees(Math.atan2(-speedY, speedX));
    }

    public float getMass() {
        return radius * radius * radius / 1000f;  // Normalize by a factor
    }

    public float getKineticEnergy() {
        return 0.5f * getMass() * (speedX * speedX + speedY * speedY);
    }

    public String toString() {
        sb.delete(0, sb.length());
        formatter.format("@(%3.0f,%3.0f) r=%3.0f V=(%2.0f,%2.0f) " +
                        "S=%4.1f \u0398=%4.0f KE=%3.0f",
                x, y, radius, speedX, speedY, getSpeed(), getMoveAngle(),
                getKineticEnergy());  // \u0398 is theta
        return sb.toString();
    }

    private final StringBuilder sb = new StringBuilder();
    private final Formatter formatter = new Formatter(sb);

    public void deleteBall(){
        BallWorld.generate.remove(this);
    }

}
