import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Darkness here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


import greenfoot.*;
import java.awt.Color;

public class Darkness extends Actor
{
    private PlayerStage2 player;
    private int radius = 120;
    private int radius1 = 70;

    private int lastX = -1;
    private int lastY = -1;

    public Darkness(PlayerStage2 player)
    {
        this.player = player;
    }

    public void act()
    {
        if (player.getX() != lastX || player.getY() != lastY)
        {
            updateDarkness();
            
            lastX = player.getX();
            lastY = player.getY();
        }
    }

    private void updateDarkness()
    {
        World world = getWorld();

        int width = world.getWidth();
        int height = world.getHeight();

        GreenfootImage image = new GreenfootImage(width, height);

        // Membuat seluruh layar gelap
        image.setColor(new greenfoot.Color(0, 0, 0, 251));
        image.fill();
      
        // Posisi cahaya mengikuti MC
        int centerX = player.getX();
        int centerY = player.getY();

        // Membuat lingkaran transparan (lingkaran luar)
        for (int x = -radius; x <= radius; x++)
        {
            for (int y = -radius; y <= radius; y++)
            {
                if (x * x + y * y <= radius * radius)
                {
                    int px = centerX + x;
                    int py = centerY + y;

                    if (px >= 0 && px < width &&
                        py >= 0 && py < height)
                    {
                        image.setColorAt(px, py,
                            new greenfoot.Color(0, 0, 0, 245));
                    }
                }
            }
        }        
        
        // Posisi cahaya mengikuti MC
        int centerX1 = player.getX();
        int centerY1 = player.getY();

        // Membuat lingkaran transparan ke 2 (lingkaran dalam)
        for (int x = -radius1; x <= radius1; x++)
        {
            for (int y = -radius1; y <= radius1; y++)
            {
                if (x * x + y * y <= radius1 * radius1)
                {
                    int px = centerX1 + x;
                    int py = centerY1 + y;

                    if (px >= 0 && px < width &&
                        py >= 0 && py < height)
                    {
                        image.setColorAt(px, py,
                            new greenfoot.Color(0, 0, 0, 100));
                    }
                }
            }
        }        
        

        

        setImage(image);
    }
}

