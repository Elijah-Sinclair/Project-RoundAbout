import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import java.util.Random;
import java.awt.Image;

public class EnemyBall {
    private JPanel panel;

    private int x, y;
    private int startX, startY;

    private int size = 40;
    private int dx = 4;
    private int dy = 4;

    private boolean marked;

    private Random rand = new Random();

    public EnemyBall(JPanel p, int xPos, int yPos) {
        panel = p;

        x = xPos;
        y = yPos;

        startX = xPos;
        startY = yPos;

        marked = false;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        g.setColor(marked ? Color.ORANGE :Color.RED);
        g.fillOval(x,y,size,size);

    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x,y,size,size);
    }

    // Hit zone for enemies
    public Rectangle2D getHitzone() {
        return  new Rectangle2D.Double(
                x-15,
                y-15,
                size+30,
                size+30);
    }

    public boolean touchesBorder() {
        return x <= 0 ||
                y <= 0 ||
                x >= panel.getWidth() - size ||
                y >= panel.getHeight() - size;
    }

    public void reverseWithRandomOffset() {
        dx = -dx + rand.nextInt(3) - 1;
        dy = -dy + rand.nextInt(3) - 1;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean value) {
        marked = value;
    }

    public void reset() {
        x = startX;
        y = startY;

        marked = false;

        dx = 4;
        dy = 4;
    }
}
