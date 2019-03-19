package main;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas {

    public Window(int width, int height, String title, Game game) {

        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(game.r.rx(width), game.r.ry(height)));
        frame.setMaximumSize(new Dimension(game.r.rx(width), game.r.ry(height)));
        frame.setMinimumSize(new Dimension(game.r.rx(width), game.r.ry(height)));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                game.net.closeConnection();
                System.exit(0);
            }
        });
        game.start();

    }
}
