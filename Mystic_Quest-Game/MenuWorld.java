
import greenfoot.*;

public class MenuWorld extends World
{
    public MenuWorld()
    {
        super(900, 600, 1);

        // Memanggil gambar menu utama
        GreenfootImage bg =
            new GreenfootImage("WhatsApp Image 2026-11-10 at 11.26.19.jpeg");

        // Menyesuaikan ukuran gambar
        bg.scale(900, 600);

        // Memasang background
        setBackground(bg);
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(null))
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();

            if (mouse != null)
            {
                int x = mouse.getX();
                int y = mouse.getY();

                // PLAY
                if (x >= 350 && x <= 550 &&
                    y >= 330 && y <= 380)
                {
                    Greenfoot.setWorld(
                        new Stage1World()
                    );
                }

                // SETTING
                else if (x >= 350 && x <= 550 &&
                         y >= 385 && y <= 435)
                {
                    showText(
                        "Setting belum tersedia",
                        450, 520
                    );
                }

                // QUIT
                else if (x >= 350 && x <= 550 &&
                         y >= 440 && y <= 490)
                {
                    Greenfoot.stop();
                }
            }
        }
    }
}
