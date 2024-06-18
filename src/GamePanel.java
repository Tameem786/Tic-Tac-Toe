import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GamePanel extends JPanel implements Runnable {
    ArrayList<Grid> grids;
    HashMap<Integer, Integer> gridValues;
    static boolean Player1;
    Thread gameThread;
    JPanel game;
    JPanel resultPanel;
    JLabel result;
    CardLayout cardLayout;
    String winner;
    GamePanel() {
        grids = new ArrayList<>();
        gridValues = new HashMap<>();
        game = new JPanel();
        resultPanel = new JPanel();
        result = new JLabel();
        cardLayout = new CardLayout();
        Player1 = true;

        game.setLayout(new GridLayout(3,3));
        game.setPreferredSize(new Dimension(300,300));
        for(int i=0; i<9; i++) {
            grids.add(new Grid());
            gridValues.put(i, 9);
            game.add(grids.get(i));
        }

        resultPanel.setLayout(new GridBagLayout());
        resultPanel.add(result);

        this.setLayout(cardLayout);
        this.add("gamePane", game);
        this.add("resultPane", resultPanel);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public boolean someoneWin() {
        /*
        (0,-), (1,-), (2,-)
        (3,-), (4,-), (5,-)
        (6,-), (7,-), (8,-)
         */
        if((gridValues.get(0).equals(gridValues.get(1)) &&
                        gridValues.get(1).equals(gridValues.get(2)) &&
                        gridValues.get(2).equals(0)) ||
                (gridValues.get(3).equals(gridValues.get(4)) &&
                        gridValues.get(4).equals(gridValues.get(5)) &&
                        gridValues.get(5).equals(0)) ||
                (gridValues.get(6).equals(gridValues.get(7)) &&
                        gridValues.get(7).equals(gridValues.get(8)) &&
                        gridValues.get(8).equals(0)) ||
                (gridValues.get(0).equals(gridValues.get(3)) &&
                        gridValues.get(3).equals(gridValues.get(6)) &&
                        gridValues.get(6).equals(0)) ||
                (gridValues.get(1).equals(gridValues.get(4)) &&
                        gridValues.get(4).equals(gridValues.get(7)) &&
                        gridValues.get(7).equals(0)) ||
                (gridValues.get(2).equals(gridValues.get(5)) &&
                        gridValues.get(5).equals(gridValues.get(8)) &&
                        gridValues.get(8).equals(0)) ||
                (gridValues.get(0).equals(gridValues.get(4)) &&
                        gridValues.get(4).equals(gridValues.get(8)) &&
                        gridValues.get(8).equals(0)) ||
                (gridValues.get(2).equals(gridValues.get(4)) &&
                        gridValues.get(4).equals(gridValues.get(6)) &&
                        gridValues.get(6).equals(0))) {
            winner = "Player 1 Wins!!!";
            return true;
        } else if ((gridValues.get(0).equals(gridValues.get(1)) &&
                gridValues.get(1).equals(gridValues.get(2)) &&
                gridValues.get(2).equals(1)) ||
                (gridValues.get(3).equals(gridValues.get(4)) &&
                        gridValues.get(4).equals(gridValues.get(5)) &&
                        gridValues.get(5).equals(1)) ||
                (gridValues.get(6).equals(gridValues.get(7)) &&
                        gridValues.get(7).equals(gridValues.get(8)) &&
                        gridValues.get(8).equals(1)) ||
                (gridValues.get(0).equals(gridValues.get(3)) &&
                        gridValues.get(3).equals(gridValues.get(6)) &&
                        gridValues.get(6).equals(1)) ||
                (gridValues.get(1).equals(gridValues.get(4)) &&
                        gridValues.get(4).equals(gridValues.get(7)) &&
                        gridValues.get(7).equals(1)) ||
                (gridValues.get(2).equals(gridValues.get(5)) &&
                        gridValues.get(5).equals(gridValues.get(8)) &&
                        gridValues.get(8).equals(1)) ||
                (gridValues.get(0).equals(gridValues.get(4)) &&
                        gridValues.get(4).equals(gridValues.get(8)) &&
                        gridValues.get(8).equals(1)) ||
                (gridValues.get(2).equals(gridValues.get(4)) &&
                        gridValues.get(4).equals(gridValues.get(6)) &&
                        gridValues.get(6).equals(1))) {
            winner = "Player 2 Wins!!!";
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        boolean status = true;
        while(status) {
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta >= 1) {
                status = !someoneWin();
                delta--;
            }
        }
        result.setText(winner);
        cardLayout.show(this, "resultPane");
    }

    class Grid extends JPanel {
        JLabel label;
        Grid() {
            label = new JLabel("-");
            label.setFont(new Font("Roboto", Font.BOLD, 20));

            this.setLayout(new GridBagLayout());
            this.add(label);
            this.addMouseListener(new ML());
            this.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setLabel(String newLabel) {
            label.setText(newLabel);
        }
        public void setLabelColor(Color c) {
            label.setForeground(c);
        }
    }

    class ML implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            for(int i=0; i<grids.size(); i++) {
                if(e.getSource() == grids.get(i)) {
                    if(Player1) {
                        grids.get(i).setLabel("0");
                        grids.get(i).setLabelColor(Color.WHITE);
                        grids.get(i).setBackground(Color.BLUE);
                        gridValues.put(i, 0);
                    }
                    else {
                        grids.get(i).setLabel("X");
                        grids.get(i).setLabelColor(Color.WHITE);
                        grids.get(i).setBackground(Color.RED);
                        gridValues.put(i, 1);
                    }
                    Player1 = !Player1;
                    grids.get(i).removeMouseListener(this);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}
