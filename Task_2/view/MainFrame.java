package view;

import java.awt.*;

public class MainFrame extends javax.swing.JFrame
{
    // CONST
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    // CONSTRUCTORS
    public MainFrame()
    {
        // sets title and size
        setTitle("Graph");
        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        // get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // center frame in screen
        setLocation(screenSize.width/2 - WIDTH/2, screenSize.height/2 - HEIGHT/2);

        // add panel to frame
        add(new view.Panel());
    }
}