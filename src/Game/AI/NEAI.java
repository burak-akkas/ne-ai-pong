package Game.AI;

import Game.Game;
import Game.Paddle;
import Misc.Config;
import NE.Evolution;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by burak
 */
public class NEAI {

    public Evolution evolution;
    public ArrayList<Paddle> paddles;
    public boolean trained;

    public NEAI() {
        evolution = new Evolution();
        paddles = new ArrayList<>();
        trained = false;

        Game.hits.put("ne", new ArrayList<>());

        for(int i = 0; i < Config.population; i++) {
            paddles.add(new Paddle((Config.width - Config.paddleWidth) / 2, Config.height - Config.paddleHeight * 2));

            Game.hits.get("ne").add(.0);
        }
    }

    public void render(Graphics g) {

        if(trained) {
            paddles.get(0).render(g);
        } else {
            for(int i = 0; i < paddles.size(); i++) {

                paddles.get(i).render(g);
            }

        }
    }

    public void update() {
        for(int i = 0; i < paddles.size(); i++) {

            ArrayList<Double> input = new ArrayList<>();

            // generate inputs
            input.add((paddles.get(i).x + paddles.get(i).width / 2) / Config.width);
            input.add(Game.ball.x / Config.width);

            // new inputs
            //input.add(Game.ball.y / Config.height);
            input.add(paddles.get(i).x / Game.ball.x);
            //input.add(Game.ball.xSpeed / Config.width);

            double action = evolution.generation.genomes.get(i).action(input).get(0);
            double diff = 0;
            if(action < 0.5) {
                diff = -Game.speed;
            } else {
                diff = Game.speed;
            }

            paddles.get(i).move(diff, 0);
        }
    }

    public boolean newEpoch() {
        if(trained) {
            return trained;
        }

        this.evolution.generation.updateScores(Game.hits.get("ne"));

        for(int i = 0; i < paddles.size(); i++) {
            if(Game.hits.get("ne").get(i) >= Game.hits.get("computer").get(0)) {
                ArrayList<Paddle> newPaddles = new ArrayList<Paddle>();
                newPaddles.add(paddles.get(i));
                this.paddles = newPaddles;
                trained = true;
            }

            Game.hits.get("ne").set(i, .0);
        }

        if(!trained) {
            newGeneration();
        }

        return trained;
    }

    public void newGeneration() {
        evolution.newGeneration();
    }
}


