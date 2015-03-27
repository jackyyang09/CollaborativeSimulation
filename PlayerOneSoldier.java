import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerOneSoldier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerOneSoldier extends Soldier
{
    public PlayerOneSoldier(){
        prepare();
        side = true;
    }
    public void act(){
        target(true);
    }
}
