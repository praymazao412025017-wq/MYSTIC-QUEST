
import greenfoot.*;
import java.util.ArrayList;
import java.util.Collections;

public class WordPuzzle extends Actor
{
    private String[] questions = {
        "A - L - O - B",
        "U - K - U - B",
        "I - P - A",
        "U - N - B - G - A",
        "U - K - A - Y",
        "M - A - Y - A",
        "I - S - A - N",
        "I - P - A - S",
        "N - A - K - I",
        "A - J - E - M",
    };

    private String[] answers = {
        "BOLA",
        "BUKU",
        "API",
        "BUNGA",
        "KAYU",
        "AYAM",
        "NASI",
        "SAPI",
        "IKAN",
        "MEJA",
    };

    private ArrayList<Integer> urutanSoal =
        new ArrayList<Integer>();

    private int questionIndex = 0;

    private String input = "";
    private boolean answered = false;

    public WordPuzzle()
    {
        for (int i = 0; i < questions.length; i++)
        {
            urutanSoal.add(i);
        }

        Collections.shuffle(urutanSoal);

        updateDisplay();
    }

    // Mengambil indeks soal yang sudah diacak
    private int currentQuestion()
    {
        return urutanSoal.get(questionIndex);
    }

    public void act()
    {
        if (answered)
            return;

        String key = Greenfoot.getKey();

        if (key == null)
            return;

        key = key.toLowerCase();

        // Memasukkan huruf
        if (key.length() == 1 &&
            key.charAt(0) >= 'a' &&
            key.charAt(0) <= 'z')
        {
            if (input.length() <
                answers[currentQuestion()].length())
            {
                input += key.toUpperCase();
            }
        }

        // Menghapus huruf
        else if (key.equals("backspace"))
        {
            if (input.length() > 0)
            {
                input = input.substring(
                    0, input.length() - 1
                );
            }
        }

        // Memeriksa jawaban
        else if (key.equals("enter"))
        {
            checkAnswer();
            return;
        }

        updateDisplay();
    }

    private void checkAnswer()
    {
        Stage1World world =
            (Stage1World)getWorld();

        if (input.equals(answers[currentQuestion()]))
        {
            answered = true;
            world.playerCorrect();
        }
        else
        {
            input = "";
            world.playerWrong();
        }

        updateDisplay();
    }

    // Melanjutkan ke soal berikutnya
    public void nextQuestion()
    {
        questionIndex++;

        // Jika semua soal selesai, acak kembali
        if (questionIndex >= answers.length)
        {
            questionIndex = 0;
            Collections.shuffle(urutanSoal);
        }

        input = "";
        answered = false;

        updateDisplay();
    }

    private void updateDisplay()
    {
        int index = currentQuestion();

        GreenfootImage img =
            new GreenfootImage(500, 155);

        // Background panel
        img.setColor(new Color(15, 25, 40, 240));
        img.fill();

        // Bingkai emas
        img.setColor(new Color(218, 175, 75));
        img.drawRect(1, 1, 497, 152);

        img.setColor(new Color(120, 95, 50));
        img.drawRect(5, 5, 489, 144);

        // Judul
        img.setColor(Color.WHITE);
        img.setFont(
            new Font("Arial", true, false, 20)
        );

        img.drawString("SUSUN KATA", 20, 29);

        // Nomor soal
        img.setFont(
            new Font("Arial", true, false, 13)
        );

        img.drawString(
            "SOAL " + (questionIndex + 1)
            + " / " + answers.length,
            370, 27
        );

        // Garis
        img.setColor(new Color(218, 175, 75));
        img.drawLine(20, 38, 480, 38);

        // Huruf acak
        img.setColor(new Color(255, 215, 90));
        img.setFont(
            new Font("Arial", true, false, 21)
        );

        img.drawString(questions[index], 20, 69);

        // Kotak jawaban
        int length = answers[index].length();

        for (int i = 0; i < length; i++)
        {
            int x = 20 + (i * 55);

            if (i < input.length())
            {
                img.setColor(
                    new Color(190, 145, 55)
                );
            }
            else
            {
                img.setColor(
                    new Color(45, 60, 80)
                );
            }

            img.fillRect(x, 83, 45, 40);

            img.setColor(
                new Color(255, 215, 90)
            );

            img.drawRect(x, 83, 44, 39);

            img.setColor(Color.WHITE);

            img.setFont(
                new Font("Arial", true, false, 23)
            );

            String letter = "_";

            if (i < input.length())
            {
                letter = input.substring(i, i + 1);
            }

            img.drawString(letter, x + 14, 110);
        }

        // Petunjuk
        img.setColor(Color.LIGHT_GRAY);

        img.setFont(
            new Font("Arial", false, false, 12)
        );

        img.drawString(
            "ENTER: PERIKSA | BACKSPACE: HAPUS",
            20, 143
        );

        setImage(img);
    }
}
