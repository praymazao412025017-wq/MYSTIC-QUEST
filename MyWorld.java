import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        camel camel = new camel();
        addObject(camel,296,343);
        bee bee = new bee();
        addObject(bee,347,203);
        bee bee2 = new bee();
        addObject(bee2,217,132);
        camel camel2 = new camel();
        addObject(camel2,165,323);
        camel.setLocation(259,344);
        camel.setLocation(66,365);
        bee2.setLocation(97,86);
        bee.setLocation(134,171);
        bee bee3 = new bee();
        addObject(bee3,190,79);
        bee3.setLocation(190,79);
    }

}
