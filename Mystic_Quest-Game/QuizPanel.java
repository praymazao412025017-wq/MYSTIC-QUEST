import greenfoot.*;

public class QuizPanel extends Actor
{
    private Portal currentPortal;
    private String question;
    private String[] options;
    private int correctAnswer;

    public QuizPanel(Portal portal, String question, String[] options, int correctAnswer)
    {
        this.currentPortal = portal;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;

        drawPanel();
    }

    private void drawPanel()
    {
        GreenfootImage panel = new GreenfootImage(500, 300);
        panel.setColor(new Color(30, 30, 40, 230));
        panel.fill();
        panel.setColor(Color.WHITE);
        panel.drawRect(0, 0, 499, 299);

        panel.drawString(question, 30, 50);

        char label = 'A';
        for (int i = 0; i < options.length; i++) {
            String text = label + ". " + options[i];
            int yPos = 110 + (i * 45);
            panel.drawString(text, 50, yPos);
            label++;
        }

        panel.drawString("[ Tekan A, B, C, atau D di Keyboard ]", 120, 270);
        setImage(panel);
    }

    public void act()
    {
        checkInput();
    }

    private void checkInput()
    {
        int selected = -1;

        if (Greenfoot.isKeyDown("a")) selected = 0;
        else if (Greenfoot.isKeyDown("b")) selected = 1;
        else if (Greenfoot.isKeyDown("c")) selected = 2;
        else if (Greenfoot.isKeyDown("d")) selected = 3;

        if (selected != -1) {
            if (selected == correctAnswer) {
                currentPortal.unlock();
                getWorld().removeObject(this);
            } else {
                showWrongAnswer();
            }
        }
    }

    private void showWrongAnswer()
    {
        GreenfootImage img = getImage();
        img.setColor(Color.RED);
        img.drawString("Jawaban Salah! Coba lagi...", 160, 240);
        setImage(img);
        Greenfoot.delay(20);
        drawPanel();
    }
}