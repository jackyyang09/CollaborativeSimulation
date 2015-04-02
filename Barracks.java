import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
        health = 5000;
    }

    /**
     * Act - do whatever the Barracks wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
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
