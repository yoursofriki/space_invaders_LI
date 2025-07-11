package spaceinvaders.core;

import javax.swing.JFrame;

public class Main extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders con Patrones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Añade el panel del juego (Board) a la ventana
        frame.add(new Board());

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
