package Game;

import Misc.Config;
import javax.swing.JFrame;
import java.awt.*;

public class Main{

    public static void main(String[] args) {

        JFrame frame = new JFrame("Learning (NE) - Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load running configurations
        Config.loadConfig();

        Game pong = new Game();
        frame.add(pong);

        frame.setSize(Config.width + 35, Config.height + 35);
        frame.setMaximumSize(new Dimension(Config.width + 35, Config.height + 35));
        frame.setResizable(false);
        frame.setVisible(true);

    }
}