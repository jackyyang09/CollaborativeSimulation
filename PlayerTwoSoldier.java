import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player Two's Soldier
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class PlayerTwoSoldier extends PlayerTwo
{
    public PlayerTwoSoldier(Nexus n, Player p){
        prepareSoldier();
        side = false;
        nexus = n;
        player = p;
    }
    
    public void act(){
        targetUnit(false);
        if(speed != 0){
            animate();
        }
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
