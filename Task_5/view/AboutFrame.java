package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutFrame extends JFrame
{
    // FIELDS
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 100;

    JLabel creator;

    // CONSTRUCTOR
    public AboutFrame(String message)
    {
        setTitle("Про програму!");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        creator = new JLabel(message);
        add(creator);
    }
}