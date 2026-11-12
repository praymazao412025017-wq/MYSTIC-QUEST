import greenfoot.*;

public class MenuWorld extends World
{
    private GreenfootSound bgm = new GreenfootSound("Prelude_MainMenuSong.mp3");

    public MenuWorld()
    {
        super(900, 600, 1);

        GreenfootImage bg = new GreenfootImage("WhatsApp Image 2026-11-10 at 11.26.19.jpeg");
        bg.scale(900, 600);
        setBackground(bg);
        prepare();
    }

    // Musik mulai ketika RUN ditekan
    public void started()
    {
        bgm.playLoop();
    }

    // Musik benar-benar berhenti ketika skenario dihentikan
    public void stopped()
    {
        bgm.stop();
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
                if (x >= 350 && x <= 550 && y >= 330 && y <= 380)
                {
                    bgm.stop();
                    Greenfoot.setWorld(new Stage1World());
                }

                // SETTING
                else if (x >= 350 && x <= 550 && y >= 385 && y <= 435)
                {
                    showText("Setting belum tersedia", 450, 520);
                }

                // QUIT
                else if (x >= 350 && x <= 550 && y >= 440 && y <= 490)
                {
                    bgm.stop();
                    Greenfoot.stop();
                }
            }
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}