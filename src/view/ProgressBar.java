package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class ProgressBar extends JPanel {
private int progress = 0;
private int speed;
private String txt;
private int fontSize;

public ProgressBar(int speed, String txt,int fontSize){
        this.speed = speed;
        this.txt = txt;
        this.fontSize = fontSize;
        setOpaque(false);
}

public void paintComponent(Graphics g){
        AffineTransform at = new AffineTransform();
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform save_ct = g2.getTransform();
        AffineTransform current_ctx = new AffineTransform();
        current_ctx.concatenate(at);
        current_ctx.translate(getWidth() / 2, getHeight() / 2);
        current_ctx.rotate(Math.toRadians(270));
        g2.transform(current_ctx);
        g2.setColor(new Color(216,218,218));
        g2.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawArc(-(getHeight() - 40 / 2) / 2, -(getHeight() - 40 / 2) / 2, getHeight() - 20, getHeight() - 20, 0, -(int) (progress * 3.6));

        //text
        AffineTransform current_ctx2 = new AffineTransform();
        current_ctx2.rotate(Math.toRadians(90));
        g2.transform(current_ctx2);
        g.setFont(new Font("Verdana", Font.BOLD, fontSize));
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(txt, g);
        int x = (0 - (int) r.getWidth()) / 2;
        int y = (0 - (int) r.getHeight()) / 2 + fm.getAscent();
        g2.drawString(txt, x, y);

        g2.transform(save_ct);
        g2.dispose();
}
public void renderProgressBar() {
        for (int i = 1; i <= 100; i++) {
                try {
                        Thread.sleep(speed);
                        progress = i;
                        repaint();
                } catch (InterruptedException ex) {
                        ex.printStackTrace();
                }
        }
}
}
