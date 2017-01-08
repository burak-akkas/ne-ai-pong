package Game;

import Misc.Config;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by burak
 */
public class Ball {
    public double x;
    public double y;

    public double xSpeed;
    public double ySpeed;

    public double radius;

    public Ball() {
        x = Config.width / 2;
        y = Config.height / 2;

        xSpeed = Math.random() * 2 - 1;
        ySpeed = -Game.speed;

        radius = 10.0;
    }

    // TODO: Render ball
    public void render(Graphics g) {
        g.fillOval((int)x, (int)y, (int)radius, (int)radius);
    }

    public void update() {
        this.x += this.xSpeed;
        this.y += this.ySpeed;

        //System.out.println(x + ", " + y);

        double topX = this.x - this.radius;
        double topY = this.y - this.radius;

        double bottomX = this.x + this.radius;
        double bottomY = this.y + this.radius;

        if (this.x - this.radius < 0) {
            this.x = this.radius;
            this.xSpeed = -this.xSpeed;
            this.xSpeed += Math.random() / 10;
        } else if(this.x + this.radius > Config.width) {
            this.x = Config.width - this.radius;
            this.xSpeed = -this.xSpeed;
            this.xSpeed -= Math.random() / 10;
        }

        if(y < 0 || y > Config.height) {
            x =  (new Random().nextInt() % Config.width) + 1;
            y = Config.height / 2;

            xSpeed = Math.random() * 2 + 1;
            ySpeed = -Game.speed;

            return;
        }

        String side = "computer";

        ArrayList<Paddle> paddles = new ArrayList<>();
        paddles.add(Game.computer.paddle);

        if(topY > Config.height / 2) {
            paddles = Game.ai.paddles;
            side = "ne";
        }

        boolean didHit = false;

        for(int i = 0; i < paddles.size(); i++) {
            if(     topY < (paddles.get(i).y + paddles.get(i).height) &&
                    bottomY > paddles.get(i).y &&
                    topX < (paddles.get(i).x + paddles.get(i).width) &&
                    bottomX > paddles.get(i).x) {

                if(!didHit) {
                    this.ySpeed = -this.ySpeed;
                    this.xSpeed = (this.xSpeed + (paddles.get(i).xSpeed / 2)) % Game.speed;
                    this.y += this.ySpeed;

                    didHit = true;
                }

                Game.hits.get(side).set(i, Game.hits.get(side).get(i) + 1);
            }
        }
    }
}
