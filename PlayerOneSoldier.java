import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player One's soldier
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class PlayerOneSoldier extends PlayerOne
{
    public PlayerOneSoldier(Nexus n, Player p){
        prepareSoldier();
        side = true;
        nexus = n;
        player = p;
    }
    public void act(){
        targetUnit(true);
        if(speed != 0){
            animate();
        }
    }
    
    public void animate(){
        animTimer++;
        if (animTimer == 0){setImage("goldier0.png");}
        if (animTimer == 15){setImage("goldier1.png");}
        if (animTimer == 30){setImage("goldier2.png");}
        if (animTimer == 45){setImage("goldier1.png");}
        if (animTimer == 59){animTimer = -1;}
    }
}
