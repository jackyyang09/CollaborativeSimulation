import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Required for miners and tanks to spawn
 * 
 * @author Jacky Yang
 * @version Final
 */
public class WarFactory extends Buildings
{
    public WarFactory(int player)
    {
        graphic = player;
        buildTime = 750;
        health = 10000;
        maxHealth = 10000;
    }
    
    /**
     * Act - do whatever the WarFactory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (buildTime == 750)
        {
            if (graphic == 1){setImage("golding3.png");}
            getWorld().addObject(new Graphics(1), getX(), getY());
        }
        if (buildTime > 0){buildTime--;}
        if (buildTime == 0){removeTouching(Graphics.class);}
        regen();
    }    
}
