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
    private int maxCarry = 100;
    GoldMine gMine;
    Nexus nexus;
    
    /**
     * Constructor for soldier
     */
    public Miner(GoldMine g, Nexus n, Player p, boolean whichSide){
        currentHp = 100;
        maxHp = 100;
        speed = 2;
        startSpeed = 2;
        damage = 0;
        range = 5;
        dead = false;
        gMine = g;
        nexus = n;
        player = p;
        side = whichSide;
    }

    /**
     * Act - do whatever the Soldier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        if (goldCarry == maxCarry){
            pathFinding(nexus.getX(), nexus.getY());
            speed = startSpeed;
        }
        else{
            pathFinding(gMine.getX(), gMine.getY());
            if(goldMineTouching() == true){
                speed = 0;
                gMine.subAmount(1);
                goldCarry += 1;
            }
        }
        if (this.isTouching(Nexus.class)){
            player.addGold(goldCarry);
            goldCarry = 0;
        }
    }    
}
