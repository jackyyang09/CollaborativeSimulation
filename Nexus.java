import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Nexus, does a bunch of things
 * 
 * @Jacky Yang
 * @Day 1
 */
public class Nexus extends Buildings
{
    private int gold;
    private int goldGen;
    /**
     * Act method
     */
    public void act() 
    {
        if (goldGen == 2){gold++;} //Generates gold every 2 acts
        //barracksGen();
    }    
    
    private void barracksGen()
    {
        if (gold >= 1000)
        {
            getWorld().spawnBuilding();
            gold -= 1000;
        }
    }
}
