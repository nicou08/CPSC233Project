/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maintest;

/**
 *
 * @author NicoU
 */
public class location {
    private double xcord;
    private double ycord;
    public double getXcord()
    {
        return xcord;
    }
    public double getYcord()
    {
        return ycord;
    }
    public void setXcord(double x)
    {
        xcord = x;
    }
    public void setYcord(double y)
    {
        ycord = y;
    }
    public double getDistance(location m)
    {
        double xdif, ydif, distance;
        double x1 = m.getXcord();
        double y1 = m.getYcord();
        if(x1>xcord)
        {
            xdif = x1 - xcord;
        }else if(xcord > x1)
        {
            xdif = xcord - x1;
        }else // x1 ==x2
        {
            xdif = 0;
        }
        if(y1>ycord)
        {
            ydif = y1 - ycord;
        }else if(ycord > y1)
        {
            ydif = ycord - y1;
        }else // y1 == y2
        {
            ydif = 0;
        }
        distance = Math.sqrt(xdif*xdif + ydif*ydif);
        return distance;
    }   
}
