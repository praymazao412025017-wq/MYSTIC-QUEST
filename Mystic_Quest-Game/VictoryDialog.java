import greenfoot.*;

public class VictoryDialog extends Actor
{
    private int displayTimer = 120; // Tampil selama ~2 detik (120 ticks)

    public VictoryDialog()
    {
        GreenfootImage panel = new GreenfootImage(500, 200);
        
        // Buat Background Panel Transparan Mewah
        panel.setColor(new Color(20, 20, 30, 230));
        panel.fillRect(0, 0, 500, 200);
        
        // Bingkai Emas
        panel.setColor(new Color(245, 195, 65));
        panel.drawRect(4, 4, 491, 191);
        panel.drawRect(6, 6, 487, 187);

        // Teks Kemenangan
        panel.setFont(new Font("Arial", true, false, 24));
        panel.drawString("STAGE 1 CLEARED!", 135, 70);

        panel.setFont(new Font("Arial", false, false, 16));
        panel.setColor(Color.WHITE);
        panel.drawString("Memasuki Crystal Cave Labyrinth...", 120, 120);

        setImage(panel);
    }

    public void act()
    {
        displayTimer--;
        if (displayTimer <= 0) {
            // Mulai efek layar redup menuju Stage 2
            World currentWorld = getWorld();
            if (currentWorld != null && currentWorld.getObjects(TransitionOverlay.class).isEmpty()) {
                currentWorld.addObject(new TransitionOverlay(new Stage2World()), 450, 300);
            }
        }
    }
}