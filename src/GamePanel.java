import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private GameWindow window;
    Player player;
    EnemyBall ballA, ballB, ballC, ballD;
    SoundManager soundManager;
    private Timer timer;
    private boolean gameRunning;

    public GamePanel(GameWindow w) {
        window = w;
        setBackground(Color.WHITE);
        setFocusable(true);

        addKeyListener(this);
        setFocusable(true);

        timer = new Timer(20, this);
        gameRunning = false;
        soundManager = SoundManager.getInstance();

    }

    public void createGameEntities() {
        player = new Player(this, 500, 300);

        ballA = new EnemyBall(this, 100, 100);
        ballB = new EnemyBall(this, 700, 100);
        ballC = new EnemyBall(this, 100, 500);
        ballD = new EnemyBall(this, 700, 500);

        gameRunning = true;
        timer.start();

        setFocusable(true);
        requestFocus();
        soundManager.playClip("music", true);
    }

    public void stopGame() {
        gameRunning = false;
        timer.stop();
        soundManager.stopClip("music");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameRunning) return;

        player.move();

        updateBall(ballA);
        updateBall(ballB);
        updateBall(ballC);
        updateBall(ballD);

        repaint();
    }
//
    private void updateBall(EnemyBall ball) {

        ball.move();

        // Player touches actual ball, game ends
        if (player.getBounds().intersects(ball.getBounds())) {

            soundManager.playClip("death", false);

            stopGame();
        }

        // Player touches hitzone and ball gets marked
        if (player.getBounds().intersects(ball.getHitzone())) {
            ball.setMarked(true);
        }

        // Border collision
        if (ball.touchesBorder()) {

            if (ball.isMarked()) {
                window.updateScore(1000);
                ball.reset();   // respawn at original position
            } else {
//                soundManager.playClip("bounce", false);
                ball.reverseWithRandomOffset();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (player != null) player.draw(g);
        if (ballA != null) ballA.draw(g);
        if (ballB != null) ballB.draw(g);
        if (ballC != null) ballC.draw(g);
        if (ballD != null) ballD.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameRunning) return;

        if (e.getKeyCode() == KeyEvent.VK_W)
            player.setDY(-6);

        if (e.getKeyCode() == KeyEvent.VK_S)
            player.setDY(6);

        if (e.getKeyCode() == KeyEvent.VK_A)
            player.setDX(-6);

        if (e.getKeyCode() == KeyEvent.VK_D)
            player.setDX(6);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W ||
                e.getKeyCode() == KeyEvent.VK_S)
            player.setDY(0);

        if (e.getKeyCode() == KeyEvent.VK_A ||
                e.getKeyCode() == KeyEvent.VK_D)
            player.setDX(0);
    }
}

