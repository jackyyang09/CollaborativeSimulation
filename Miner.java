import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Soldier is a sub-class of Unit
 * 
 * @author Kris Leung
 * @version Mar 2015
 */
public class Miner extends Unit
{
    private int goldCarry = 0;
    GoldMine gMine = new GoldMine();

    /**
     * Constructor for soldier
     */
    public Miner(){
        currentHp = 100;
        maxHp = 100;
        speed = 3;
        damage = 0;
        range = 5;
        dead = false;
    }

    /**
     * Act - do whatever the Soldier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        pathFinding(gMine.getX(), gMine.getY());
        if (getDead() == true){
            getWorld().removeObject(this);
        }
    }    
}
