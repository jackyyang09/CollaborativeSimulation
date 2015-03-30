import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerOneSoldier here.
 * 
 * @author Jacky Yang
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
    
    public void act(){
        target(true);
        animTimer++;
        if (animTimer == 0){setImage("goldier0.png");}
        if (animTimer == 15){setImage("goldier1.png");}
        if (animTimer == 30){setImage("goldier2.png");}
        if (animTimer == 45){setImage("goldier1.png");}
        if (animTimer == 59){animTimer = -1;}
    }
}
