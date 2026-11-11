
import greenfoot.*;

public class Poison extends Actor
{
    public Poison()
    {
        GreenfootImage img =
            new GreenfootImage(28, 28);

        // Membuat bola racun hijau
        img.setColor(new Color(40, 220, 60));
        img.fillOval(2, 2, 24, 24);

        img.setColor(new Color(180, 255, 100));
        img.fillOval(7, 6, 9, 9);

        setImage(img);
    }

    public void act()
    {
        // Racun bergerak ke kiri
        setLocation(getX() - 8, getY());

        // Periksa apakah mengenai Ari
        Player player = (Player)getOneIntersectingObject(
            Player.class
        );

        if (player != null)
        {
            Stage1World world =
                (Stage1World)getWorld();

            world.poisonHit();

            getWorld().removeObject(this);
            return;
        }

        // Hapus racun jika keluar layar
        if (getX() <= 5)
        {
            getWorld().removeObject(this);
        }
    }
}