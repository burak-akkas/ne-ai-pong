package Game.AI;

import Game.Game;
import Game.Paddle;
import Misc.Config;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by burak
 */
public class Computer {
    public Paddle paddle;

    double speedMultiplier;

    public Computer() {

        paddle = new Paddle((Config.width - Config.paddleWidth) / 2, Config.paddleHeight);

        ArrayList<Double> algoHits = new ArrayList<>();
        algoHits.add(.0);
        Game.hits.put("computer", algoHits);

        speedMultiplier = 1;
    }

    public void render(Graphics g) {
        paddle.render(g);
    }

    public void update() {
        double diff = -((this.paddle.x + (this.paddle.width / 2)) - Game.ball.x);
        if(diff < -Game.speed) {
            diff = -Game.speed * this.speedMultiplier;
        } else if(diff > Game.speed) {
            diff = Game.speed * this.speedMultiplier;
        }
        this.paddle.move(diff, 0);
    }
}
