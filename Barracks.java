import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Does what a Barracks does
 * 
 * @author Jacky Yang
 * @version Day X WIP
 */
public class Barracks extends Buildings
{
    public Barracks()
    {
        buildTime = 500;
        health = 500;
    }
    
    /**
     * Act - do whatever the Barracks wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (buildTime == 750){getWorld().addObject(new Graphics(1), getX(), getY());}
        if (buildTime > 0){buildTime--;}
        if (buildTime == 0){setImage("building1.png");}
        regen();
    }
    
    /**
     * Method that is called when Barracks is destroyed
     * Will likely decrease a value within the player class
     */
    public void destroy()
    {
        //Code goes here
    }
}
