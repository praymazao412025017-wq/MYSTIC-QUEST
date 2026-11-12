
import greenfoot.*;

public class PlayerStage2 extends Actor
{
    private int speed = 1;

    private GreenfootImage[] walkDown;
    private GreenfootImage[] walkLeft;
    private GreenfootImage[] walkRight;
    private GreenfootImage[] walkUp;

    private int animIndex = 0;
    private int animDelay = 0;

    private String currentDirection = "down";

    public PlayerStage2()
    {
        walkDown = new GreenfootImage[4];
        walkDown[0] = new GreenfootImage("walk_down.png");
        walkDown[1] = new GreenfootImage("walk_down (1).png");
        walkDown[2] = new GreenfootImage("walk_down (2).png");
        walkDown[3] = new GreenfootImage("walk_down (3).png");

        walkLeft = new GreenfootImage[4];
        walkLeft[0] = new GreenfootImage("walk_left.png");
        walkLeft[1] = new GreenfootImage("walk_left (1).png");
        walkLeft[2] = new GreenfootImage("walk_left (2).png");
        walkLeft[3] = new GreenfootImage("walk_left(3).png");

        walkRight = new GreenfootImage[3];
        walkRight[0] = new GreenfootImage("walk_right.png");
        walkRight[1] = new GreenfootImage("walk_right (1).png");
        walkRight[2] = new GreenfootImage("walk_right (2).png");

        walkUp = new GreenfootImage[4];
        walkUp[0] = new GreenfootImage("walk_up.png");
        walkUp[1] = new GreenfootImage("walk_up (1).png");
        walkUp[2] = new GreenfootImage("walk_up (2).png");
        walkUp[3] = new GreenfootImage("walk_up (3).png");

        scaleAllImages(40, 40);

        setImage(walkDown[0]);
    }

    private void scaleAllImages(int width, int height)
    {
        scaleArray(walkDown, width, height);
        scaleArray(walkLeft, width, height);
        scaleArray(walkRight, width, height);
        scaleArray(walkUp, width, height);
    }

    private void scaleArray(
        GreenfootImage[] images, int w, int h)
    {
        for (int i = 0; i < images.length; i++)
        {
            images[i].scale(w, h);
        }
    }

//collision check

/*
public void moveIfPossible(int dx, int dy)
{
    // Periksa gerakan horizontal
    if (dx != 0 && canMoveTo(getX() + dx, getY()))
    {
        setLocation(getX() + dx, getY());
    }

    // Periksa gerakan vertikal
    if (dy != 0 && canMoveTo(getX(), getY() + dy))
    {
        setLocation(getX(), getY() + dy);
    }
}
*/

public void checkCollision()
{
    // Cek apakah player sedang menyentuh objek Stone
    if (isTouching(stone1.class))
    {
        // Kalau menabrak, dorong balik ke kiri (atau undo gerakan terakhir)
        setLocation(getX() - 2, getY()); 
    }

}
    public void act()
    {
        movePlayer();
        checkCollision();
    }

    private void movePlayer()
    {
        int dx = 0;
        int dy = 0;

        boolean isMoving = false;

        if (Greenfoot.isKeyDown("a") ||
            Greenfoot.isKeyDown("left"))
        {
            dx = -speed;
            currentDirection = "left";
            isMoving = true;
        }
        else if (Greenfoot.isKeyDown("d") ||
                 Greenfoot.isKeyDown("right"))
        {
            dx = speed;
            currentDirection = "right";
            isMoving = true;
        }
        else if (Greenfoot.isKeyDown("w") ||
                 Greenfoot.isKeyDown("up"))
        {
            dy = -speed;
            currentDirection = "up";
            isMoving = true;
        }
        else if (Greenfoot.isKeyDown("s") ||
                 Greenfoot.isKeyDown("down"))
        {
            dy = speed;
            currentDirection = "down";
            isMoving = true;
        }

        if (dx != 0 || dy != 0)
        {
            
            // Periksa gerakan horizontal
            if (canMoveTo(getX() + dx, getY()))
            {
                setLocation(getX() + dx, getY());
            }

            // Periksa gerakan vertikal
            if (canMoveTo(getX(), getY() + dy))
            {
                setLocation(getX(), getY() + dy);
            }
        }

        if (isMoving)
        {
            animateWalk();
        }
        else
        {
            resetIdleImage();
        }
    }

    // Memeriksa posisi sebelum bergerak
    private boolean canMoveTo(int x, int y)
    {
        World world = getWorld();

        if (world == null)
        {
            return false;
        }

        GreenfootImage bg = world.getBackground();

        int radius = 20;

        return isWalkable(bg, x - radius, y - radius)
            && isWalkable(bg, x + radius, y - radius)
            && isWalkable(bg, x - radius, y + radius)
            && isWalkable(bg, x + radius, y + radius);
    }

    // Memeriksa warna area jalan
    private boolean isWalkable(
        GreenfootImage bg, int x, int y)
    {
        if (x < 0 || y < 0 ||
            x >= bg.getWidth() ||
            y >= bg.getHeight())
        {
            return false;
        }

        Color c = bg.getColorAt(x, y);

        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        // Warna tanah
        boolean brownGround =
            r > g + 4 &&
            g > b + 2 &&
            (r + g + b) / 3 > 35;

        // Warna abu-abu terang
        boolean grayGround =
            Math.abs(r - g) < 12 &&
            Math.abs(g - b) < 12 &&
            r > 85;

        return brownGround || grayGround;
    }

    private void animateWalk()
    {
        animDelay++;

        if (animDelay >= 5)
        {
            animDelay = 0;

            if (currentDirection.equals("down"))
            {
                animIndex =
                    (animIndex + 1) % walkDown.length;

                setImage(walkDown[animIndex]);
            }
            else if (currentDirection.equals("left"))
            {
                animIndex =
                    (animIndex + 1) % walkLeft.length;

                setImage(walkLeft[animIndex]);
            }
            else if (currentDirection.equals("right"))
            {
                animIndex =
                    (animIndex + 1) % walkRight.length;

                setImage(walkRight[animIndex]);
            }
            else if (currentDirection.equals("up"))
            {
                animIndex =
                    (animIndex + 1) % walkUp.length;

                setImage(walkUp[animIndex]);
            }
        }
    }

    private void resetIdleImage()
    {
        if (currentDirection.equals("down"))
            setImage(walkDown[0]);

        else if (currentDirection.equals("left"))
            setImage(walkLeft[0]);

        else if (currentDirection.equals("right"))
            setImage(walkRight[0]);

        else if (currentDirection.equals("up"))
            setImage(walkUp[0]);
    }
}