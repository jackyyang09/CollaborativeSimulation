import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerTwoMiner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerTwoMiner extends PlayerTwo
{
    public PlayerTwoMiner(GoldMine g1, GoldMine g2, Nexus n, Player p){
        nexus = n;
        player = p;
        gMine1 = g1;
        gMine2 = g2;
        prepareMiner();
        side = false;
    }

    public void act(){
        targetGoldMine(getGoldMine(), false);
        healthBar.setCurrentHp(currentHp);
        if (goldCarry == 0){setImage("goldminer0.png");}
        if (goldCarry == 50){setImage("goldminer1.png");}
        if (goldCarry == 100){setImage("goldminer2.png");}
    }
}
