
import greenfoot.*;

public class Player extends Actor
{
    private boolean attacking = false;
    private int attackTimer = 0;

    private int startX;
    private int startY;

    public Player()
    {
        GreenfootImage img =
            new GreenfootImage("playeridle (1) (1).png");

        img.scale(300, 300);
        setImage(img);
    }

    public void attack()
    {
        if (!attacking)
        {
            attacking = true;
            attackTimer = 0;

            startX = getX();
            startY = getY();
        }
    }

    public void act()
    {
        if (attacking)
        {
            attackTimer++;

            // Ari maju
            if (attackTimer <= 10)
            {
                setLocation(startX + attackTimer * 5, startY);
            }

            // Ari kembali ke posisi awal
            else if (attackTimer <= 20)
            {
                setLocation(
                    startX + (20 - attackTimer) * 5,
                    startY
                );
            }
            else
            {
                setLocation(startX, startY);
                attacking = false;
            }
        }
    }

    public boolean isAttacking()
    {
        return attacking;
    }
    
public void shootLaser()
{
    if (getWorld() != null)
    {
        Laser laser = new Laser();

        getWorld().addObject(
            laser,
            getX() + 60,
            getY()
        );
    }

}
}