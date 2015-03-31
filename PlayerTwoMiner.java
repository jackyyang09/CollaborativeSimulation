import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerTwoMiner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerTwoMiner extends Miner
{
    public PlayerTwoMiner(GoldMine g1, GoldMine g2, Nexus n, Player p){
        nexus = n;
        player = p;
        gMine1 = g1;
        gMine2 = g2;
        prepare();
        side = false;
    }

    public void act(){
        target(getGoldMine(), false);
        if (goldCarry == 0){setImage("goldminer0.png");}
        if (goldCarry == 50){setImage("goldminer1.png");}
        if (goldCarry == 100){setImage("goldminer2.png");}
    }

    public GoldMine getGoldMine(){
        if(wentOnce == true){
            if(Greenfoot.getRandomNumber(2) == 0){
                gMine = gMine1;
            }
            else{
                gMine = gMine2;
            }
            wentOnce = false;
        }
        return gMine;
    }
}
