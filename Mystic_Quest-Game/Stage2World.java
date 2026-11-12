import greenfoot.*;

public class Stage2World extends World
{
    private int openedPortals = 0;
    private int totalPortals = 3; // Terdapat 3 gerbang utama di map

    public Stage2World()
{
    super(900, 600, 1);

    // Memasang Background Map
    GreenfootImage bg = new GreenfootImage(
        "Gemini_Generated_Image_1dxmce1dxmce1dxm.jpg"
    );

    bg.scale(900, 600);
    setBackground(bg);

    // Player Stage 2
    addObject(new PlayerStage2(), 70, 535);

    // Finish Point
    addObject(new FinishPoint(), 345, 120);

    // Portal
    setupPortals();

    // Status Portal
    updateHUD();
}

    private void setupPortals()
    {
        String[] opt1 = {"Biru", "Merah", "Hijau", "Kuning"};
        String[] opt2 = {"10", "12", "14", "16"};
        String[] opt3 = {"C", "D", "E", "F"};

        // Portal 1: Presisi menutupi Gerbang Merah (Kiri Tengah)
        addObject(new Portal("Pola: Merah - Biru - Merah - ...?", opt1, 0), 160, 390);

        // Portal 2: Presisi menutupi Gerbang Biru (Bawah Tengah)
        addObject(new Portal("Pola: 2 - 4 - 6 - 8 - ...?", opt2, 0), 555, 470);

        // Portal 3: Presisi menutupi Gerbang Hijau (Kanan Tengah)
        addObject(new Portal("Pola: A - C - A - C - ...?", opt3, 0), 750, 360);
    }

    public void showQuizPanel(Portal portal, String question, String[] options, int correctAnswer)
    {
        if (getObjects(QuizPanel.class).isEmpty()) {
            addObject(new QuizPanel(portal, question, options, correctAnswer), 450, 300);
        }
    }

    public void portalUnlocked()
    {
        openedPortals++;
        updateHUD();
    }

    private void updateHUD()
    {
        showText("Portal Terbuka: " + openedPortals + "/" + totalPortals, 120, 30);
    }

    public void gameCleared()
    {
        showText("TAMAT! SELAMAT KAMU BERHASIL MENYELESAIKAN GAME!", 450, 300);
        Greenfoot.stop();
    }
}