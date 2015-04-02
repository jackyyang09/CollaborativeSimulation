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
     * Chooses which side the barracks belongs to
     * 
     * @param player 1 if player one, 2 if player two
     */ 
    public Barracks(int player)
    {
        graphic = player;
        buildTime = 500;
        health = 500;
    }

    /**
     * Act - do whatever the Barracks wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (graphic == 1){setImage("golding1.png");}
        regen();
    }
}
