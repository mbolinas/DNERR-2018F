package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;


import javax.swing.JLabel;

/**
 * OutLineLabel extends JLabel to provide more readable text on colored backgrounds
 * @author vsargeni
 */

public class OutLineLabel extends JLabel {

    protected double shearFactor = 0.0;

    protected Color outlineColor;

    protected Stroke stroke;

    /**
     * This method sets the outline for a text with a Color
     * @param c
     */
    public void setOutlineColor(Color c) {
        outlineColor = c;
        repaint();
    }


    /**
     * This method draws the text inside the JLabel with the outline around it
     * @param g The graphic to be produced
     */
    public void paintComponent(Graphics g) {
        Dimension d = getSize();
        Insets ins = getInsets();
        int x = ins.left;
        int y = ins.top;
        int w = d.width - ins.left - ins.right;
        int h = d.height - ins.top - ins.bottom;

        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, d.width, d.height);
        }
        paintBorder(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        FontRenderContext frc = g2.getFontRenderContext();
        TextLayout tl = new TextLayout(getText(), getFont(), frc);

        AffineTransform shear = AffineTransform.getShearInstance(shearFactor,
                0.0);
        Shape src = tl.getOutline(shear);
        Rectangle rText = src.getBounds();

        float xText = x - rText.x;
        switch (getHorizontalAlignment()) {
            case CENTER:
                xText = x + (w - rText.width) / 2;
                break;
            case RIGHT:
                xText = x + (w - rText.width);
                break;
        }
        float yText = y + h / 2 + tl.getAscent() / 4;

        AffineTransform shift = AffineTransform.getTranslateInstance(xText,
                yText);
        Shape shp = shift.createTransformedShape(src);

        if (outlineColor != null) {
            g2.setColor(outlineColor);
            if (stroke != null)
                g2.setStroke(stroke);
            g2.draw(shp);
        }
        g2.setColor(getForeground());
        g2.fill(shp);


    }
}
