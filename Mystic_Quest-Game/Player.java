import greenfoot.*;

public class Player extends Actor
{
    private boolean attacking = false;
    private int attackTimer = 0;

    private int startX;
    private int startY;

    // Gambar karakter
    private GreenfootImage idleImage;
    private GreenfootImage[] walkImages = new GreenfootImage[3];

    private int walkFrame = 0;
    private int animationTimer = 0;

    public Player()
    {
        idleImage = new GreenfootImage(
            "playeridle (1) (1).png"
        );

        idleImage.scale(300, 300);
        setImage(idleImage);

        walkImages[0] =
            new GreenfootImage("playeridlemove1.png");

        walkImages[1] =
            new GreenfootImage("playeridlemove2.png");

        walkImages[2] =
            new GreenfootImage("playeridlemove3.png");

        for (int i = 0; i < walkImages.length; i++)
        {
            walkImages[i].scale(300, 300);
        }
    }

    public void attack()
    {
        if (!attacking)
        {
            attacking = true;
            attackTimer = 0;

            startX = getX();
            startY = getY();

            setImage(idleImage);
        }
    }

    public void act()
    {
        // Jika Monster sudah kalah,
        // Ari berjalan otomatis ke kiri.
        if (getWorld() instanceof Stage1World)
        {
            Stage1World stage =
                (Stage1World)getWorld();

            
        }

        // Saat menyerang, kaki tetap diam.
        if (attacking)
        {
            setImage(idleImage);

            attackTimer++;

            if (attackTimer <= 10)
            {
                setLocation(
                    startX + attackTimer * 5,
                    startY
                );
            }
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

            return;
        }

        // Selama pertarungan, Ari diam.
        setImage(idleImage);
    }

    private void animateWalk()
    {
        animationTimer++;

        if (animationTimer >= 8)
        {
            animationTimer = 0;

            walkFrame++;

            if (walkFrame >= walkImages.length)
            {
                walkFrame = 0;
            }

            setImage(walkImages[walkFrame]);
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