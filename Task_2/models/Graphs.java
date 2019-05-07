package models;

public class Graphs
{
    // FIELDS
    float a;
    float A;

    // CONSTRUCTORS
    public Graphs()
    {
        a = 20;
        A = 40;
    }

    // METHODS
    public void setSmallA(float a)
    {
        this.a = a;
    }

    public void setBigA(float A)
    {
        this.A = A;
    }

    // GRAPHICS
    public java.awt.geom.Point2D Epicycloid(double t)
    {
        double x = (A + a)*Math.cos(t)-a*Math.cos((A/a+1)*t);
        double y = (A + a)*Math.sin(t)-a*Math.sin((A/a+1)*t);

        // calc (x, y)
        return new java.awt.geom.Point2D.Double(x,y);
    }
}