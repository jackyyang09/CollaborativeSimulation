import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerTwoSoldier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerTwoSoldier extends Soldier
{
    public PlayerTwoSoldier(){
        prepare();
        side = false;
    }
    
    public void act(){
        target(false);
    }
}
