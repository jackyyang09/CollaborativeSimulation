import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerTwoSoldier here.
 * 
 * @author Jacky Yang
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
    
    public void animate()
    {
        animTimer++;
        if (animTimer == 0){setImage("sildier0.png");}
        if (animTimer == 15){setImage("sildier1.png");}
        if (animTimer == 30){setImage("sildier2.png");}
        if (animTimer == 45){setImage("sildier1.png");}
        if (animTimer == 59){animTimer = -1;}
    }
}
