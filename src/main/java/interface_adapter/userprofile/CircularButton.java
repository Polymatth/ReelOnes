package interface_adapter.userprofile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class CircularButton extends JButton {

    public CircularButton(String label) {
        super(label);
        setFont(new Font("Arial", Font.BOLD, 14));
        setForeground(Color.WHITE);
        setBackground(new Color(70, 130, 180));  // Steel Blue Color
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        setPreferredSize(new Dimension(80, 80));  // Larger size for better visibility
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(100, 149, 237));  // Cornflower Blue
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(70, 130, 180));  // Reset to Steel Blue
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background circle
        g2.setColor(getBackground());
        g2.fillOval(0, 0, getWidth(), getHeight());

        // Draw label text
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 3;
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(80, 80);  // Ensure the button stays circular
    }

    @Override
    public boolean contains(int x, int y) {
        // Make button clickable only within the circular area
        Ellipse2D circle = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        return circle.contains(x, y);
    }

}