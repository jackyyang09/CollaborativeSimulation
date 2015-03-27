import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Does what a little War Factory does
 * 
 * @Jacky Yang
 * @Day X WIP
 */
public class WarFactory extends Buildings
{
    public WarFactory()
    {
        buildTime = 750;
    }
    
    /**
     * Act - do whatever the WarFactory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (buildTime > 0)
        {
            setImage("construction2.png");
            buildTime--;
        }
        if (buildTime == 0){setImage("building3.png");}
        regen();
    }    
}
