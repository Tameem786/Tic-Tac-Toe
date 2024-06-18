# Tic Tac Toe (JAVA)
This game is developed using Java Swing UI. Some logic has been added to check if Player 1 wins or Player 2 wins.

![Game-1](https://github.com/Tameem786/Tic-Tac-Toe/assets/47950077/acbbc314-49a8-417a-8ca2-aeca6cd31fbf)
![Game-2](https://github.com/Tameem786/Tic-Tac-Toe/assets/47950077/2e39e971-1edd-46c9-8d6b-ccc497dd54aa)
![Game-3](https://github.com/Tameem786/Tic-Tac-Toe/assets/47950077/43c14aef-3e06-415e-b751-5ae7a07a06b6)

## Game Loop Function
```java
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
