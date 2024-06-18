import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel gamePanel;
    GameFrame() {
        gamePanel = new GamePanel();

        this.setTitle("Tic-Tac-Toe");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.pack();
        this.setVisible(true);
    }
}
