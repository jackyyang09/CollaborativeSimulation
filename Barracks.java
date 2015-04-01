I import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Does what a Barracks does
 * 
 * @author Jacky Yang
 * @version Final
 */
public class Barracks extends Buildings
{
    
   /**
 * Does what a Barracks does
 * Chooses which side 
 * 
 * @param player 1 if player one, 2 if player two
 */ 
    public Barracks(int player)
    {
        graphic = player;
        buildTime = 500;
        health = 10000;
        maxHealth = 10000;
    }
    
    /**
     * Act - do whatever the Barracks wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (buildTime == 500)
        {
            if (graphic == 1){setImage("golding1.png");}
            getWorld().addObject(new Graphics(1), getX(), getY());
        }
        if (buildTime > 0){buildTime--;}
        if (buildTime == 0){removeTouching(Graphics.class);}
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
