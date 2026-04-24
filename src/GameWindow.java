import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame
        implements ActionListener,
            KeyListener,
            MouseListener
{
    private JLabel scoreBarL;

    private JTextField scoreBarTF;

    private JButton startB;
    private JButton exitB;

    private Container c;

    private JPanel mainPanel;
    private GamePanel gamePanel;

    private int score = 0;
    public GameWindow() {
        setTitle("RoundAbout Alpha-release");
        setSize(1000, 800);

        //

        //Labels
        scoreBarL = new JLabel("Score: ");

        scoreBarTF = new JTextField(15);

        scoreBarTF.setEditable(false);
        scoreBarTF.setBackground(Color.cyan);
        scoreBarTF.setText("0");

        //buttons

        startB = new JButton("Start Game");
        exitB = new JButton("Exit");

        //listeners

        startB.addActionListener(this);
        exitB.addActionListener(this);

        //mainPanel
        mainPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        mainPanel.setLayout(flowLayout);

        GridLayout gridLayout;

        //Game Panel
        gamePanel = new GamePanel(this);
            gamePanel.setPreferredSize(new Dimension(800, 600));


        //create scorePanel
        JPanel scorePanel = new JPanel();
        gridLayout = new GridLayout(1, 2);
        scorePanel.setLayout(gridLayout);
        scorePanel.setBackground(Color.getHSBColor(0.44F,0.25F,0.6F));

        //scorePanel objects
        scorePanel.add(scoreBarL);
        scorePanel.add(scoreBarTF);

        //buttonPanel
        JPanel buttonPanel = new JPanel();
        gridLayout = new GridLayout(2, 1);
        buttonPanel.setLayout(gridLayout);

        //adding Buttons
        buttonPanel.add(startB);
        buttonPanel.add(exitB);

        //add subp-panels to main panel
        mainPanel.add(scorePanel);
        mainPanel.add(gamePanel);
        mainPanel.add(buttonPanel);
        mainPanel.setBackground(Color.getHSBColor(0.75F, 0.65F,0.6F));

        c = getContentPane();
        c.add(mainPanel);

        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public void updateScore(int points) {
        score += points;
        scoreBarTF.setText(String.valueOf(score));
    }

    public void resetScore() {
        score = 0;
        scoreBarTF.setText("0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if (command.equals("Start Game")) {
            resetScore();
            gamePanel.createGameEntities();
        }

        if (command.equals("Exit")) {
            System.exit(0);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

