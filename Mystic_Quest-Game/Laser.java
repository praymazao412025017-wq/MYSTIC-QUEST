
import greenfoot.*;

public class Laser extends Actor
{
    public Laser()
    {
        GreenfootImage img =
            new GreenfootImage(45, 14);

        // Membuat laser biru bercahaya
        img.setColor(new Color(0, 220, 255));
        img.fillOval(0, 2, 40, 10);

        img.setColor(Color.WHITE);
        img.fillOval(8, 4, 22, 6);

        setImage(img);
    }

    public void act()
    {
        // Laser bergerak ke kanan
        setLocation(getX() + 10, getY());

        // Periksa apakah mengenai monster
        Enemy enemy = (Enemy)getOneIntersectingObject(
            Enemy.class
        );

        if (enemy != null)
        {
            Stage1World world =
                (Stage1World)getWorld();

            world.laserHit();
            enemy.takeDamage();

            getWorld().removeObject(this);
            return;
        }

        // Hapus laser jika keluar layar
        if (getX() >= getWorld().getWidth() - 5)
        {
            getWorld().removeObject(this);
        }
    }
}