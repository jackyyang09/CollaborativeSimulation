import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Point;

/**
 * The Start Menu
 * 
 * @author Jacky Yang 
 * @version Final
 */
public class StartMenu extends World
{
    TextBox gold1, gold2;
    /**
     * Constructor for objects of class Menu.
     */
    public StartMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 640, 1); 
        gold1 = new TextBox(new Point(100, 18), "0");
        gold1.setMaxLength(12);
        addObject(gold1, 143, 218);
        
        gold2 = new TextBox(new Point(100, 18), "0");
        gold2.setMaxLength(12);
        addObject(gold2, 143, 360);
    }
    
    public void act()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            int goldAmt1 = Integer.parseInt(gold1.getText());
            int goldAmt2 = Integer.parseInt(gold2.getText());
            Map map = new Map(goldAmt1, goldAmt2);
            Greenfoot.setWorld(map);
        }
    }
}
