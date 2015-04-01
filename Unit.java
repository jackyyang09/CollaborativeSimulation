import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Unit is a unit.
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
    protected int damage; //damage unit can deal
    protected int range; //the range where the unit can attack other units
    protected boolean dead; //sees if unit is dead
    protected boolean side; //true if playerOne side and false if playerTwo side
    protected int counter;
    protected int animTimer;
    protected Player player;
    protected GoldMine goldMine, goldMine2;
    protected Buildings building, building2;
    protected Buildings targetBuildings;
    protected ArrayList<Buildings> buildings; 
    protected PlayerOne targetP1Unit;
    protected ArrayList<PlayerOne> p1Unit;
    protected PlayerTwo targetP2Unit;
    protected ArrayList<PlayerTwo> p2Unit;

    protected HealthBar healthBar = new HealthBar(500, 500, 100, 30, 5, this);
    public void addedToWorld (World w)
    {
        getWorld().addObject (healthBar, getX(), getY());
    }

    /**
     * Private method, called by act(), that constantly checks for closer targets
     */
    protected void targetClosestP1Unit ()
    {
        double closestTargetDistance = 0;
        double distanceToActor;
        //int numSoldiers;
        // Get a list of all Flowers in the World, cast it to ArrayList
        // for easy management
        p1Unit = (ArrayList)getWorld().getObjects(PlayerOne.class);
        if (p1Unit.size() > 0)
        {
            // set the first one as my target
            targetP1Unit = p1Unit.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = Map.getDistance (this, targetP1Unit);

            // Loop through the objects in the ArrayList to find the closest target
            for (PlayerOne o : p1Unit)
            {
                // Cast for use in generic method
                Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = Map.getDistance(this, a);
                // If I find a Flower closer than my current target, I will change
                // targets
                if (distanceToActor < closestTargetDistance)
                {
                    targetP1Unit = o;
                    closestTargetDistance = distanceToActor;
                }
            }
        }
    }

    protected void targetClosestP2Unit(){
        double closestTargetDistance = 0;
        double distanceToActor;
        p2Unit = (ArrayList)getWorld().getObjects(PlayerTwo.class);
        if (p2Unit.size() > 0)
        {
            // set the first one as my target
            targetP2Unit = p2Unit.get(0);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = Map.getDistance (this, targetP2Unit);

            // Loop through the objects in the ArrayList to find the closest target
            for (PlayerTwo o : p2Unit)
            {
                // Cast for use in generic method
                Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = Map.getDistance(this, a);
                // If I find a Flower closer than my current target, I will change
                // targets
                if (distanceToActor < closestTargetDistance)
                {
                    targetP2Unit = o;
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
        turnTowards(targetP1Unit.getX(), targetP1Unit.getY());

        if (this.getNeighbours (range, true, PlayerOne.class).size() > 0)
        {
            speed = 0;
            //getWorld().addObject(new Bullet(targetP1Soldier, false), getX(), getY());
            targetP1Unit.dealDamage(getDamage());
        }
        else
        {
            moveForward();
        }
    }

    /**
     * Private method, calle by act(), that moves toward the target,
     * or eats it if within range.
     */
    protected void moveTowardOrAttackP2 ()
    {
        turnTowards(targetP2Unit.getX(), targetP2Unit.getY());

        if (this.getNeighbours (range, true, PlayerTwo.class).size() > 0)
        {
            speed = 0;
            //getWorld().addObject(new Bullet(targetP2Soldier, true), getX(), getY());
            targetP2Unit.dealDamage(getDamage());
        }
        else
        {
            moveForward();
        }
    }

    protected void moveForward()
    {
        speed = startSpeed;
        move (speed);
    }

//     protected void targetP1Building (){
//         double closestTargetDistance = 0;
//         double distanceToActor;
//         //int numSoldiers;
//         // Get a list of all Flowers in the World, cast it to ArrayList
//         // for easy management
//         buildings = (ArrayList)getWorld().getObjects(Buildings.class);
//         if (buildings.size() > 0)
//         {
//             // set the first one as my target
//             targetP1Building = p1Building.get(0);
//             // Use method to get distance to target. This will be used
//             // to check if any other targets are closer
//             closestTargetDistance = Map.getDistance (this, targetP1Building);
// 
//             // Loop through the objects in the ArrayList to find the closest target
//             for (Buildings o : p1Building)
//             {
//                 // Cast for use in generic method
//                 Actor a = (Actor) o;
//                 // Measure distance from me
//                 distanceToActor = Map.getDistance(this, a);
//                 // If I find a Flower closer than my current target, I will change
//                 // targets
//                 if (distanceToActor < closestTargetDistance)
//                 {
//                     targetP1Building = o;
//                     closestTargetDistance = distanceToActor;
//                 }
//             }
//         }
//     }

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
        building = (Buildings)getOneObjectAtOffset(20, 0, Buildings.class);
        building2 = (Buildings)getOneObjectAtOffset(-20, 0, Buildings.class);
        if (building != null || building2 != null){
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
    
    public int getDamage (){
        return Greenfoot.getRandomNumber(3)+1;
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
    
    protected int goldCarry = 0;
    protected int maxCarry = 100;
    protected GoldMine gMine;
    protected GoldMine gMine1;
    protected GoldMine gMine2;
    protected Nexus nexus;
    protected boolean wentOnce = true;
    
    /**
     * Prepare the Miner
     */
    public void prepareMiner(){
        currentHp = 500;
        maxHp = 500;
        speed = 2;
        startSpeed = 2;
        damage = 0;
        range = 5;
        dead = false;
    }

    /**
     * Act - do whatever the Soldier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void targetGoldMine(GoldMine g, boolean whichSide){
        if (currentHp > 0)
        {
            if (goldCarry == maxCarry){
                speed = startSpeed;
                pathFinding(nexus.getX(), nexus.getY());
            }
            else{
                if(goldMineTouching() == true){
                    speed = 0;
                    if(gMine.subAmount(1)){
                        goldCarry += 1;
                    }
                    wentOnce = true;
                }
                else{
                    speed = startSpeed;
                    pathFinding(gMine.getX(), gMine.getY());
                }
            }
            if (this.isTouching(Nexus.class)){
                player.addGold(goldCarry);
                goldCarry = 0;
            }
        }
        // Death:
        else
        {
            getWorld().removeObject(this);
        }
    }    
    
    /**
     * Prepare method for Soldier
     */
    public void prepareSoldier(){
        currentHp = 500;
        maxHp = 500;
        speed = 1;
        startSpeed = 1;
        range = 100;
        dead = false;
    }

    public void targetUnit(boolean whichSide){
        if (currentHp > 0)
        {
            if (whichSide == false) // Only run every 4 acts to avoid lag
            {
                targetClosestP1Unit();
                if (targetP1Unit != null && targetP1Unit.getWorld() != null)
                {
                    moveTowardOrAttackP1();
                }
                else if (buildingTouching()){
                    speed = 0;
                }
                else
                {
                    //moveForward();
                    if (nexus != null && nexus.getWorld() != null){
                        moveTowardOrAttackNexus();
                    }
                }
                healthBar.setCurrentHp(currentHp);
            }
            else if(whichSide == true){
                targetClosestP2Unit();
                if (targetP2Unit != null && targetP2Unit.getWorld() != null)
                {
                    moveTowardOrAttackP2();
                }
                else if (buildingTouching()){
                    speed = 0;
                }
                else
                {
                    //moveForward();
                    if (nexus != null && nexus.getWorld() != null){
                        moveTowardOrAttackNexus();
                    }
                }
                healthBar.setCurrentHp(currentHp);
            }
        }
        // Death:
        else
        {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Private method, calle by act(), that moves toward the target,
     * or eats it if within range.
     */
    protected void moveTowardOrAttackNexus ()
    {
        turnTowards(nexus.getX(), nexus.getY());

        if (this.getNeighbours (150, true, Nexus.class).size() > 0)
        {
            speed = 0;
            //getWorld().addObject(new Bullet(targetP2Soldier, true), getX(), getY());
            nexus.dealDamage(damage);
        }
        else
        {
            moveForward();
        }
    }
    
    public void maxHP (int raise)
    {
        maxHp = raise;
    }

    public void heal (int raise)
    {
        currentHp += raise;
        if (currentHp > maxHp)
        {
            currentHp = maxHp;  
        }
    }

    public void betrayal (boolean choice)
    {
        side = choice;
    }

    public  void fast (int velocity)
    {
        speed = velocity;
    }
}
