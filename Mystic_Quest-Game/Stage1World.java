
import greenfoot.*;

public class Stage1World extends World
{
    private int hpAri = 100;
    private int hpMonster = 100;

    private HealthBar barAri;
    private HealthBar barMonster;

    private boolean gameOver = false;

    public Stage1World()
    {
        super(900, 600, 1);

        // BACKGROUND
        GreenfootImage bg =
            new GreenfootImage(
                "Gemini_Generated_Image_nvvc26nvvc26nvvc.jpg"
            );

        int newWidth = (int)(
            (double) bg.getWidth() * 600
            / bg.getHeight()
        );

        bg.scale(newWidth, 600);
        setBackground(bg);

        // ARI
        addObject(new Player(), 150, 480);

        // MONSTER
        addObject(new Enemy(), 770, 500);

        // HEALTH BAR ARI
        barAri = new HealthBar(
            "ARI",
            100,
            new Color(40, 200, 100)
        );

        addObject(barAri, 150, 55);

        // HEALTH BAR MONSTER
        barMonster = new HealthBar(
            "MONSTER",
            100,
            new Color(220, 60, 60)
        );

        addObject(barMonster, 750, 55);

        // WORD PUZZLE
        addObject(new WordPuzzle(), 450, 515);

        updateHP();
    }

    // MEMPERBARUI HP
    private void updateHP()
    {
        barAri.updateHP(hpAri);
        barMonster.updateHP(hpMonster);
    }

    // LASER MENGENAI MONSTER
    public void laserHit()
    {
        if (gameOver)
            return;

        hpMonster -= 20;

        if (hpMonster < 0)
            hpMonster = 0;

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

            Greenfoot.stop();
        }
    }

    // RACUN MENGENAI ARI
    public void poisonHit()
    {
        if (gameOver)
            return;

        hpAri -= 10;

        if (hpAri < 0)
            hpAri = 0;

        updateHP();

        showText(
            "RACUN MENGENAI ARI! -10 HP",
            450, 380
        );

        if (hpAri == 0)
        {
            gameOver = true;

            showText(
                "ARI KALAH!",
                450, 350
            );

            Greenfoot.stop();
        }
    }

    // JAWABAN BENAR
    public void playerCorrect()
    {
        if (gameOver)
            return;

        Player ari =
            (Player)getObjects(Player.class).get(0);

        ari.shootLaser();

        showText(
            "BENAR! ARI MENEMBAKKAN LASER!",
            450, 380
        );

        WordPuzzle puzzle =
            (WordPuzzle)getObjects(
                WordPuzzle.class
            ).get(0);

        puzzle.nextQuestion();
    }

    // JAWABAN SALAH
    public void playerWrong()
    {
        if (gameOver)
            return;

        Enemy monster =
            (Enemy)getObjects(Enemy.class).get(0);

        monster.shootPoison();

        showText(
            "SALAH! MONSTER MENEMBAKKAN RACUN!",
            450, 380
        );
    }
}