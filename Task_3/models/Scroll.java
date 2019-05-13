package models;

import javax.swing.*;

public class Scroll extends JScrollPane
{
    // FIELDS
    private final boolean isCreated;
    private final JTextArea text;
    private final String path;

    // CONSTRUCTOR
    public Scroll(JTextArea text, boolean isCreated, String path)
    {
        super(text);
        this.text = text;
        this.isCreated = isCreated;
        this.path = path;
    }

    // METHODS
    public String getText(){ return text.getText(); }

    public boolean isCreated(){
        return isCreated;
    }

    public String getPath(){
        return path;
    }
}
