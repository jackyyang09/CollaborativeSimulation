import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Hardcore Killer.
 * 
 * @author (Kajamugesh) 
 * @version (April 2nd)
 */
public class PlayerOneGrimm extends PlayerTwoSoldier
{
   public Grim(){
        speed = 1
        maxHp = 1000;
        currentHp = 1000;
        strength = 100;
    }
    
    public void act(){
        targetUnit(side);
    }
}
