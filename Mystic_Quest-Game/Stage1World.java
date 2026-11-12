
import greenfoot.*;

public class Stage1World extends World
{
    private int hpAri = 100;
    private int hpMonster = 100;

    private HealthBar barAri;
    private HealthBar barMonster;

    private boolean gameOver = false;
    private boolean cleanupPending = false;
    private boolean movingToStage2 = false;

    private int transitionTimer = 0;

    public Stage1World()
    {
        super(900, 600, 1);

        // Background Stage 1
        GreenfootImage bg = new GreenfootImage(
            "Gemini_Generated_Image_nvvc26nvvc26nvvc.jpg"
        );

        int newWidth = (int)(
            (double)bg.getWidth() * 600 / bg.getHeight()
        );

        bg.scale(newWidth, 600);
        setBackground(bg);

        // Karakter Ari dan Monster
        addObject(new Player(), 150, 480);
        addObject(new Enemy(), 770, 500);

        // Health Bar Ari
        barAri = new HealthBar(
            "ARI", 100, new Color(40, 200, 100)
        );

        addObject(barAri, 150, 55);

        // Health Bar Monster
        barMonster = new HealthBar(
            "MONSTER", 100, new Color(220, 60, 60)
        );

        addObject(barMonster, 750, 55);

        // Kotak susun kata
        addObject(new WordPuzzle(), 450, 515);

        updateHP();
    }

    public void act()
    {
        // Menunggu sebelum membersihkan objek
        if (cleanupPending)
        {
            transitionTimer++;

            if (transitionTimer >= 90)
            {
                cleanupStage1();

                cleanupPending = false;
                movingToStage2 = true;
            }

            return;
        }

        // Ari berjalan otomatis ke kanan
        if (movingToStage2)
        {
            java.util.List<Player> players =
                getObjects(Player.class);

            if (!players.isEmpty())
            {
                Player ari = players.get(0);

                // Ari berjalan ke kanan
                ari.setLocation(
                    ari.getX() + 3,
                    ari.getY()
                );

                // Jika Ari mencapai ujung kanan
                if (ari.getX() >= 850)
                {
                    Greenfoot.setWorld(new CrystalCaveWorld());
                    return;
                }
            }
        }
    }

    private void cleanupStage1()
    {
        // Menghilangkan Monster
        removeObjects(getObjects(Enemy.class));

        // Menghilangkan kotak susun kata
        removeObjects(getObjects(WordPuzzle.class));

        // Menghilangkan Health Bar
        removeObjects(getObjects(HealthBar.class));

        // Menghilangkan Laser yang tersisa
        removeObjects(getObjects(Laser.class));

        // Menghilangkan tulisan sebelumnya
        showText("", 450, 350);
        showText("", 450, 380);
    }

    private void updateHP()
    {
        if (barAri != null && barAri.getWorld() != null)
        {
            barAri.updateHP(hpAri);
        }

        if (barMonster != null && barMonster.getWorld() != null)
        {
            barMonster.updateHP(hpMonster);
        }
    }

    public boolean isMovingToStage2()
    {
        return movingToStage2;
    }

    // Ketika Laser mengenai Monster
    public void laserHit()
    {
        if (gameOver) return;

        hpMonster -= 20;

        if (hpMonster < 0)
        {
            hpMonster = 0;
        }

        updateHP();

        showText(
            "LASER MENGENAI MONSTER! -20 HP",
            450, 380
        );

        if (hpMonster == 0)
        {
            gameOver = true;

            showText(
                "SELAMAT! ARI MENANG!",
                450, 350
            );

            cleanupPending = true;
            transitionTimer = 0;
        }
    }

    // Ketika Ari terkena racun
    public void poisonHit()
    {
        if (gameOver) return;

        hpAri -= 10;

        if (hpAri < 0)
        {
            hpAri = 0;
        }

        updateHP();

        showText(
            "RACUN MENGENAI ARI! -10 HP",
            450, 380
        );

        if (hpAri == 0)
        {
            gameOver = true;

            showText("ARI KALAH!", 450, 350);

            Greenfoot.stop();
        }
    }

    // Ketika jawaban pemain benar
    public void playerCorrect()
    {
        if (gameOver) return;

        Player ari = (Player)getObjects(Player.class).get(0);

        if (ari != null)
        {
            ari.shootLaser();
        }

        showText(
            "BENAR! ARI MENEMBAKKAN LASER!",
            450, 380
        );

        WordPuzzle puzzle =
            (WordPuzzle)getObjects(WordPuzzle.class).get(0);

        if (puzzle != null)
        {
            puzzle.nextQuestion();
        }
    }

    // Ketika jawaban pemain salah
    public void playerWrong()
    {
        if (gameOver) return;

        Enemy monster =
            (Enemy)getObjects(Enemy.class).get(0);

        if (monster != null)
        {
            monster.shootPoison();
        }

        showText(
            "SALAH! MONSTER MENEMBAKKAN RACUN!",
            450, 380
        );
    }
}