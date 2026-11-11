
import greenfoot.*;

public class HealthBar extends Actor
{
    private String characterName;
    private int maxHP;
    private int currentHP;
    private Color barColor;

    public HealthBar(
        String name,
        int max,
        Color color)
    {
        characterName = name;
        maxHP = max;
        currentHP = max;
        barColor = color;

        updateDisplay();
    }

    public void updateHP(int hp)
    {
        currentHP = Math.max(0, Math.min(hp, maxHP));
        updateDisplay();
    }

    private void updateDisplay()
    {
        GreenfootImage img =
            new GreenfootImage(260, 55);

        // Panel gelap
        img.setColor(new Color(25, 30, 35, 210));
        img.fill();

        // Nama karakter
        img.setColor(Color.WHITE);
        img.setFont(
            new Font("Arial", true, false, 17)
        );

        img.drawString(
            characterName + "  " +
            currentHP + "/" + maxHP,
            10, 19
        );

        // Background bar
        img.setColor(Color.DARK_GRAY);
        img.fillRect(10, 29, 240, 16);

        // Isi bar berdasarkan HP
        int barWidth =
            (int)(240.0 * currentHP / maxHP);

        img.setColor(barColor);

        if (barWidth > 0)
        {
            img.fillRect(
                10, 29, barWidth, 16
            );
        }

        // Bingkai bar
        img.setColor(Color.WHITE);
        img.drawRect(10, 29, 239, 15);

        setImage(img);
    }
}