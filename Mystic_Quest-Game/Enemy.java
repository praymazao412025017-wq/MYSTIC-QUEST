
import greenfoot.*;

public class Enemy extends Actor
{
    private GreenfootImage normalImage;

    private int hitTimer = 0;
    private boolean hit = false;

    private int startX;
    private int startY;

    public Enemy()
    {
        normalImage =
            new GreenfootImage("bat.png");

        normalImage.scale(185, 185);

        setImage(new GreenfootImage(normalImage));
    }

    public void takeDamage()
    {
        if (!hit)
        {
            hit = true;
            hitTimer = 0;

            startX = getX();
            startY = getY();
        }
    }

    public void act()
    {
        if (hit)
        {
            hitTimer++;

            // Monster mundur
            if (hitTimer <= 5)
            {
                setLocation(startX + 4, startY);
            }

            // Monster kembali
            else if (hitTimer <= 10)
            {
                setLocation(startX, startY);
            }

            // Efek berkedip
            if (hitTimer % 3 == 0)
            {
                GreenfootImage flash =
                    new GreenfootImage(normalImage);

                flash.setColor(new Color(255, 50, 50, 150));
                flash.fill();

                setImage(flash);
            }
            else
            {
                setImage(new GreenfootImage(normalImage));
            }

            if (hitTimer >= 15)
            {
                hit = false;
                setLocation(startX, startY);
                setImage(new GreenfootImage(normalImage));
            }
        }
    }
    
public void shootPoison()
{
    if (getWorld() != null)
    {
        Poison poison = new Poison();

        getWorld().addObject(
            poison,
            getX() - 60,
            getY()
        );
    }
}
}