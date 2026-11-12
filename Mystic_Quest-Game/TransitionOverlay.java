import greenfoot.*;

public class TransitionOverlay extends Actor
{
    private int alpha = 0;
    private int fadeSpeed = 5;
    private World targetWorld;

    public TransitionOverlay(World nextWorld)
    {
        this.targetWorld = nextWorld;
        GreenfootImage img = new GreenfootImage(900, 600);
        img.setColor(Color.BLACK);
        img.fill();
        img.setTransparency(alpha);
        setImage(img);
    }

    public void act()
    {
        if (alpha < 255) {
            alpha += fadeSpeed;
            if (alpha > 255) alpha = 255;
            getImage().setTransparency(alpha);
        } else {
            // Pindah dunia setelah layar sepenuhnya hitam
            Greenfoot.setWorld(targetWorld);
        }
    }
}