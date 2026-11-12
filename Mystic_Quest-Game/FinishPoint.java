import greenfoot.*;

public class FinishPoint extends Actor
{
    public FinishPoint()
    {
        GreenfootImage img = getImage();
        if (img != null) {
            img.scale(45, 45);
            setImage(img);
        }
    }

    public void act()
    {
        if (isTouching(Player.class)) {
            Stage2World world = (Stage2World) getWorld();
            world.gameCleared();
        }
    }
}