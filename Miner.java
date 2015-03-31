import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Soldier is a sub-class of Unit
 * 
 * @author Kris Leung
 * @version Mar 2015
 */
public class Miner extends Unit
{
    protected int goldCarry = 0;
    protected int maxCarry = 100;
    protected GoldMine gMine;
    protected GoldMine gMine1;
    protected GoldMine gMine2;
    protected Nexus nexus;
    protected boolean wentOnce = true;

    /**
     * Preparation for Miner
     */
    public void prepare (){
        currentHp = 100;
        maxHp = 100;
        speed = 2;
        startSpeed = 2;
        damage = 0;
        range = 5;
        dead = false;
    }

    /**
     * Act - do whatever the Soldier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * @param g which goldmine, miner will target
     * @param whichSide choose the side that the miner is on, false player one, true player two
     */
    public void target(GoldMine g, boolean whichSide){
        if (currentHp > 0)
        {
            if (goldCarry == maxCarry){
                pathFinding(nexus.getX(), nexus.getY());
                speed = startSpeed;
            }
            else{
                if(goldMineTouching() == true){
                    speed = 0;
                    gMine.subAmount(1);
                    goldCarry += 1;
                    wentOnce = true;
                } else {
                    pathFinding(gMine.getX(), gMine.getY());
                }
            }
        }
        if (this.isTouching(Nexus.class)){
            player.addGold(goldCarry);
            goldCarry = 0;
        }
        // Death
        else
        {
            getWorld().removeObject(this);
        }    
    }
}
