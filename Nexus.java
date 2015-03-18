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
        goldGen();
    }

    public void goldGen() 
    {
        if (goldGen == 2)
        {
            gold++;
            goldGen = 0;
        } //Generates gold every 2 acts
        goldGen++;
    }

    public int getGold()
    {
        return gold;
    }
    
    public boolean setGold(int gold){
        if(gold>=0){
            amountGold += gold;
            return true;
        }
        else{
            return false;
        }
    }
}
