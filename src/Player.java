import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import java.awt.Image;

public class Player{

    private JPanel panel;
    private int x,y;
    private int width = 50;
    private int height = 50;

    private int dx = 0;
    private int dy = 0;

    private Image playerImage;

    public Player (JPanel p, int xPos, int yPos) {
        panel = p;


        //backgroundColour = panel.getBackground();
        x = xPos;
        y = yPos;

        playerImage = ImageManager.loadImage("/Player.png");
    }

    public void setDX(int value) {
        dx = value;
    }

    public void setDY(int value) {
        dy = value;
    }

    public void move() {
        x += dx;
        y += dy;

        //border restrictions
        if(x<0)
            x = 0;
        if(y<0)
            y = 0;
        if (x > panel.getWidth() - width)
            x = panel.getWidth() - width;
        if (y > panel.getHeight() - height)
            y = panel.getHeight() - height;
    }

    public void draw(Graphics g) {
        g.drawImage(playerImage, x, y, width, height, null);
    }

    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x, y, width, height);
    }
}

