import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Operates static images and miscellaneous animation on screen
 * 
 * @author Jacky Yang
 * @version Final
 */
public class Graphics extends Actor
{
    private int type;
    /**
     * Operates static images and miscellaneous animation on screen
     * 
     * @param val 1 for construction animation
     */
    public Graphics(int val)
    {
        type = val;
    }

    /**
     * Act - do whatever the Graphics wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (type == 1){setImage("goldwin.png");}
        if (type == 2){setImage("silverwin.png");}
        Greenfoot.stop();
    }    
}
