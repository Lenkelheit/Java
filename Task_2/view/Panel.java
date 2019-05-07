package view;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.Random;

public class Panel extends JPanel
{
    // FIELDS
    models.Graphs graphs;

    Random random;
    Paint paint;
    Stroke stroke;

    // CONSTRUCTORS
    public Panel()
    {
        graphs = new models.Graphs();

        random = new Random();
        paint = Color.green;
        stroke = new BasicStroke(1);

        // change stroke randomly on click
        addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent event)
            {
                // random color
                paint = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());

                // random stroke
                int width = random.nextInt(15);
                int cap = random.nextInt(3);
                int join = random.nextInt(3);
                stroke = new BasicStroke(width, cap, join);

                repaint();
            }
        });
    }

    // METHODS
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // get Graphics2D
        Graphics2D g2d = (Graphics2D)g;

        // drawing
        // draw Name
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Serif", Font.BOLD, 20));
        g2d.drawString("Nazariy Tymtsiv, variant - 1", 10, 30);

        // draw Graph
        // sets a and A, to scale graph
        graphs.setSmallA(getWidth()/12);
        graphs.setBigA(getWidth()/6);

        // transform to center to correct math axes
        AffineTransform oldTransform = g2d.getTransform();
        g2d.translate(getWidth()/2, getHeight()/2);

        // sets random color and stroke
        g2d.setPaint(paint);
        g2d.setStroke(stroke);

        // drawing graphs
        GeneralPath graphPath = new GeneralPath();
        Point2D firstPoint = graphs.Epicycloid(-Math.PI);
        graphPath.moveTo( firstPoint.getX(),  firstPoint.getY() );
        for (double t = -Math.PI; t <= Math.PI; t += 0.01D)
        {
            Point2D point = graphs.Epicycloid(t);
            graphPath.lineTo(point.getX(), point.getY());
        }
        graphPath.closePath();
        g2d.draw(graphPath);

        // reset transform
        g2d.setTransform(oldTransform);
    }
}