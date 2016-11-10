import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ScorpionOrange on 2016/11/06.
 * The component that draws the balls.
 */
public class BallComponent extends JPanel {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private java.util.List<Ball> balls = new ArrayList<>();

    /**
     * Add a ball to the component.
     * @param b the ball to add
     */
    public void add(Ball b){
        balls.add(b);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); // erase background
        Graphics2D g2 = (Graphics2D) g;
        for(Ball b : balls){
            g2.fill(b.getShape());
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
