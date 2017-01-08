package Game;

import Misc.Config;
import java.awt.*;

/**
 * Created by burak
 */
public class Paddle {
    public double x;
    public double y;
    public int width;
    public int height;

    double xSpeed;
    double ySpeed;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;

        width = Config.paddleWidth;
        height = Config.paddleHeight;

        xSpeed = 0;
        ySpeed = 0;
    }


    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int)x, (int)y, width, height);
    }

    public void move(double x, double y) {
        this.x += x;
        this.y += y;
        this.xSpeed = x;
        this.ySpeed = y;

        if(this.x < 0) {
            this.x = 0;
            this.xSpeed = 0;
        } else if(this.x + this.width > Config.width) {
            this.x = Config.width - this.width;
            this.xSpeed = 0;
        }
    }
}
