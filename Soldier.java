import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Soldier is a sub-class of Unit
 * 
 * @author Kris Leung
 * @version Mar 2015
 */
public class Soldier extends Unit
{

    GoldMine gMine = new GoldMine();

    /**
     * Constructor for soldier
     */
    public Soldier(){
        currentHp = 100;
        maxHp = 100;
        speed = 3;
        damage = 5;
        range = 5;
        dead = false;
    }

    public void target(boolean whichSide){
        if (hp > 0)
        {
            if (whichSide == false) // Only run every 4 acts to avoid lag
            {
                targetClosestP1Soldier();
                if (targetP1Soldier != null && targetP1Soldier.getWorld() != null)
                {
                    moveTowardOrAttackP1();
                }
                else
                {
                    moveRandomly();
                }
                //hpBar.update(hp);
            }
            else if(whichSide == true){
                targetClosestP2Soldier();
                if (targetP2Soldier != null && targetP2Soldier.getWorld() != null)
                {
                    moveTowardOrAttackP2();
                }
                else
                {
                    moveRandomly();
                }
            }
        }
        // Death:
        else
        {
            getWorld().removeObject(this);
        }
    }
