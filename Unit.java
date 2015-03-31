import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Unit is a unit. Test
 * 
 * @author Kris Leung, (some code cited from Mr.Cohen's bug example)
 * @version Mar 2015
 */
public abstract class Unit extends Actor
{
    protected int currentHp; //current hp of the units
    protected int maxHp; //max hp of the unit
    protected int speed; //speed of which unit moves
    protected int startSpeed;
    protected int damage = Greenfoot.getRandomNumber(5)+1; //damage unit can deal
    protected int range; //the range where the unit can attack other units
    protected boolean dead; //sees if unit is dead
    protected boolean side; //true if playerOne side and false if playerTwo side
    protected int counter;
    protected Player player;
    GoldMine goldMine;
    GoldMine goldMine2;
    Buildings building;
    Buildings building2;
    protected Buildings targetBuildings;
    protected ArrayList<Buildings> buildings; 
    protected PlayerOneSoldier targetP1Soldier;
    protected ArrayList<PlayerOneSoldier> p1Soldier;
    protected PlayerTwoSoldier targetP2Soldier;
    protected ArrayList<PlayerTwoSoldier> p2Soldier;
    
    protected HealthBar healthBar = new HealthBar(500, 500, 30, 5, this);
    public void addedToWorld (World w)
    {
        getWorld().addObject (healthBar, getX(), getY());
    }
    
    /**
     * Private method, called by act(), that constantly checks for closer targets
     */
    protected void targetClosestP1Soldier ()
    {
        double closestTargetDistance = 0;
        double distanceToActor;
        //int numSoldiers;
        // Get a list of all Flowers in the World, cast it to ArrayList
        // for easy management
        p1Soldier = (ArrayList)getWorld().getObjects(PlayerOneSoldier.class);
        if (p1Soldier.size() > 0)
        {
            // set the first one as my target
            targetP1Soldier = p1Soldier.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = Map.getDistance (this, targetP1Soldier);

            // Loop through the objects in the ArrayList to find the closest target
            for (PlayerOneSoldier o : p1Soldier)
            {
                // Cast for use in generic method
                Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = Map.getDistance(this, a);
                // If I find a Flower closer than my current target, I will change
                // targets
                if (distanceToActor < closestTargetDistance)
                {
                    targetP1Soldier = o;
                    closestTargetDistance = distanceToActor;
                }
            }
        }
    }

    protected void targetClosestP2Soldier(){
        double closestTargetDistance = 0;
        double distanceToActor;
        p2Soldier = (ArrayList)getWorld().getObjects(PlayerTwoSoldier.class);
        if (p2Soldier.size() > 0)
        {
            // set the first one as my target
            targetP2Soldier = p2Soldier.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = Map.getDistance (this, targetP2Soldier);

            // Loop through the objects in the ArrayList to find the closest target
            for (PlayerTwoSoldier o : p2Soldier)
            {
                // Cast for use in generic method
                Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = Map.getDistance(this, a);
                // If I find a Flower closer than my current target, I will change
                // targets
                if (distanceToActor < closestTargetDistance)
                {
                    targetP2Soldier = o;
                    closestTargetDistance = distanceToActor;
                }
            }
        }
    }

    /**
     * Private method, calle by act(), that moves toward the target,
     * or eats it if within range.
     */
    protected void moveTowardOrAttackP1 ()
    {
        turnTowards(targetP1Soldier.getX(), targetP1Soldier.getY());

        if (this.getNeighbours (50, true, PlayerOneSoldier.class).size() > 0)
        {
            speed = 0;
            targetP1Soldier.dealDamage(damage);
        }
        else
        {
            move (startSpeed);
        }
    }

    /**
     * Private method, calle by act(), that moves toward the target,
     * or eats it if within range.
     */
    protected void moveTowardOrAttackP2 ()
    {
        turnTowards(targetP2Soldier.getX(), targetP2Soldier.getY());

        if (this.getNeighbours (50, true, PlayerTwoSoldier.class).size() > 0)
        {
            speed = 0;
            targetP2Soldier.dealDamage(damage);
        }
        else
        {
            move (startSpeed);
        }
    }

    protected void moveForward ()
    {
            speed = startSpeed;
            move (speed);
    }

    protected void targetBuilding (boolean side){

    }

    public void pathFinding(int x, int y){
        turnTowards(x, y);
        move(speed);
    }

    public boolean goldMineTouching(){
        goldMine = (GoldMine)getOneObjectAtOffset(10, 0, GoldMine.class);
        goldMine2 = (GoldMine)getOneObjectAtOffset(-10, 0, GoldMine.class);
        if (goldMine != null || goldMine2 != null){
            return true;
        }
        else{
            return false;   
        }
    }
    
    public boolean buildingTouching(){
        building = (Buildings)getOneObjectAtOffset(10, 0, Buildings.class);
        building2 = (Buildings)getOneObjectAtOffset(-10, 0, Buildings.class);
        if (building != null || building2 != null){
            speed = 0;
            return true;
        }
        else{
            return false;   
        }
    }

    /**
     * Deal amount of damage to a unit
     * @param dmg      Damage dealt to the unit
     * @return boolean   Returns true if value is changed and false otherwise
     */
    public boolean dealDamage (int dmg){
        if (dmg >= 0){
            currentHp -= dmg;
            return true;
        }
        else{
            return false;
        }
    }
}
