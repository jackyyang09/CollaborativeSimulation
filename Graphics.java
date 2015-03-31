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
    private int animTimer;
    private boolean prepare;
    private int animType;

    /**
     * Operates static images and miscellaneous animation on screen
     * 
     * @param val 1 for construction animation
     */
    public Graphics(int val)
    {
        type = val;
        animTimer = 0;
        prepare = false;
    }

    /**
     * Act - do whatever the Graphics wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (prepare == false)
        {
            if (type == 1){setImage("hammer1.png");}
            prepare = true;
        }
        if (type == 1){construction();}
        animTimer++;
    }    

    /**
     * Rotates the hammer consistently
     */
    private void construction()
    {
        if (getRotation()== 0){animType = 1;}
        if (getRotation() == 270){animType = 2;}
        if (animType == 1){setRotation(getRotation() - 2);}
        if (animType == 2){setRotation(getRotation() + 2);}
    }
}
