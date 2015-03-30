import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Soldier is a sub-class of Unit
 * 
 * @author Kris Leung
 * @version Mar 2015
 */
public abstract class Soldier extends Unit
{
    protected int animTimer;
    /**
     * Prepare method for Soldier
     */
    public void prepare(){
        currentHp = 100;
        maxHp = 100;
        speed = 1;
        startSpeed = 1;
        range = 50;
        dead = false;
    }

    public void target(boolean whichSide){
        if (currentHp > 0)
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
                    moveForward();
                }
                healthBar.setCurrentHp(currentHp);
            }
            else if(whichSide == true){
                targetClosestP2Soldier();
                if (targetP2Soldier != null && targetP2Soldier.getWorld() != null)
                {
                    moveTowardOrAttackP2();
                }
                else
                {
                    moveForward();
                }
                healthBar.setCurrentHp(currentHp);
            }
        }
        // Death:
        else
        {
            if(whichSide == true){
                ((Map)getWorld()).playerTwo.addSoldiers(-1);
            }
            else if(whichSide == false){
                ((Map)getWorld()).playerOne.addSoldiers(-1);
            }
            getWorld().removeObject(this);
        }
        }
    }
