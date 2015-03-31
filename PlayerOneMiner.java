import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerOneMiner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerOneMiner extends Miner
{
    public PlayerOneMiner(GoldMine g1, GoldMine g2, Nexus n, Player p){
        nexus = n;
        player = p;
        gMine1 = g1;
        gMine2 = g2;
        prepare();
        side = true;
    }

    public void act(){
        target(getGoldMine(), true);
        if (goldCarry == 0){setImage("miner0.png");}
        if (goldCarry == 50){setImage("miner1.png");}
        if (goldCarry == 100){setImage("miner2.png");}
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
