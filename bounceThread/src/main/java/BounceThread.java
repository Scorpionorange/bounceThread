import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by ScorpionOrange on 2016/11/09.
 */
public class BounceThread {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            JFrame frame = new BounceFrame();
            frame.setTitle("BounceThread");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * A runnable that animates a bouncing ball.
 */
class BallRunnable implements Runnable{
    private Ball ball;
    private Component component;
    public static final int STEPS = 1000;
    public static final int DELAY = 5;

    /**
     * Constructs the runnable.
     * @param aBall the ball to bounce
     * @param aComponent the component in which the ball bounces
     */
    public BallRunnable(Ball aBall, Component aComponent){
        ball = aBall;
        component = aComponent;
    }
    public void run(){
        try{
            for(int i = 1; i <= STEPS; i++){
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DELAY);
            }
        }
        catch (InterruptedException e){}
    }
}

/**
 * The frame with panel and buttons.
 */
class BounceFrame extends JFrame{
    private BallComponent component;

    /**
     * Constructs the frame with the component for showing the bouncing ball and Start and Close buttons
     */
    public BounceFrame(){
        component = new BallComponent();
        add(component, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event -> addBall());
        addButton(buttonPanel, "Close", event -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Adds a button to a container.
     * @param container the container
     * @param title the button title
     * @param listener the action listener for the button
     */
    public void addButton(Container container, String title, ActionListener listener){
        JButton button = new JButton(title);
        container.add(button);
        button.addActionListener(listener);
    }

    /**
     * Adds a bouncing ball to the canvas and starts a thread to make it bounce.
     */
    public void addBall(){
        Ball ball = new Ball();
        component.add(ball);
        Runnable runnable = new BallRunnable(ball, component);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
