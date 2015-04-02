import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Does what a little War Factory does
 * 
 * @Jacky Yang
 * @Day X WIP
 */
public class WarFactory extends Buildings
{
    public WarFactory(int player)
    {
        graphic = player;
    }
    
    /**
     * Act - do whatever the WarFactory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (graphic == 1){setImage("golding3.png");}
        regen();
    }    
}
