package Game;

import Game.AI.Computer;
import Game.AI.NEAI;
import Misc.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by burak
 */
public class Game extends JPanel implements ActionListener {

    public static double speed;

    public static Map<String, ArrayList<Double>> hits;
    public static int epoch;

    public static NEAI ai;
    public static Computer computer;
    public static Ball ball;
    double topAIHits;

    public Game() {
        speed = (Config.maxSpeed + Config.minSpeed) / 2;
        hits = new HashMap<>();
        epoch = 1;

        ai = new NEAI();
        computer = new Computer();
        ball = new Ball();

        //call step() 60 fps
        Timer timer = new Timer(1000/60, this);
        timer.start();

        setBackground(Color.black);
    }

    public void step() {
        update();
        repaint();

        if(hits.get("computer").get(0) == Config.epochHits && !ai.trained) {
             topAIHits = Collections.max(hits.get("ne")) / Config.epochHits * 100;

            boolean trained = ai.newEpoch();

            ArrayList<Double> newHits = new ArrayList<>();
            newHits.add(.0);
            hits.put("computer", newHits);

            if(!trained) {
                ++epoch;
            }
        }
    }
    public void update() {
        ai.update();
        computer.update();
        ball.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.white);

        g.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        g.drawString("AI", 30, 45);
        g.drawString("NE AI", 30, Config.height - 45);

        for (int lineX = 0; lineX < getWidth(); lineX += 25) {
            g.drawLine(lineX, Config.height / 2, lineX + 25, Config.height / 2);
        }

        g.drawString("Population: " + Config.population, 25, Config.height / 2 - 15);
        g.drawString("Epoch: " + epoch, Config.width - 65, Config.height / 2 - 15);
        g.drawString("Total: " + topAIHits + "%", Config.width - 65, Config.height / 2 + 25);

        ai.render(g);
        computer.render(g);
        ball.render(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Config.width, Config.height);
    }

    public void actionPerformed(ActionEvent e){
        step();
    }
}
