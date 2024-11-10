package interface_adapter.userprofile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class CircularButton extends JButton {

    public CircularButton(String label) {
        super(label);

        // Remove default button decorations
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        // Add action listener for button click
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Button Clicked!");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Enable antialiasing for smoother circle
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set background color and draw the circular shape
        g2.setColor(Color.CYAN);
        g2.fillOval(0, 0, getWidth(), getHeight());

        // Call the super method to handle button label (text) drawing
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw a circular border
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(3));
        g2.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(80, 80); // Set preferred size (width and height should be equal for a circle)
    }

    @Override
    public boolean contains(int x, int y) {
        // Make button clickable only within the circular area
        Ellipse2D circle = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        return circle.contains(x, y);
    }

}