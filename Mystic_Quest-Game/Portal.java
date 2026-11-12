import greenfoot.*;

public class Portal extends Actor
{
    private boolean isLocked = true;
    private String question;
    private String[] options;
    private int correctAnswerIndex;

    public Portal(String question, String[] options, int correctAnswerIndex)
    {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        
        GreenfootImage img = getImage();
        if (img != null) {
            // Skala agar menutupi lorong gerbang kuno
            img.scale(45, 60);
            setImage(img);
        }
    }

    public void act()
    {
        checkPlayerCollision();
    }

    private void checkPlayerCollision()
    {
        if (isLocked && isTouching(Player.class)) {
            Stage2World world = (Stage2World) getWorld();
            world.showQuizPanel(this, question, options, correctAnswerIndex);
        }
    }

    public void unlock()
    {
        isLocked = false;
        Stage2World world = (Stage2World) getWorld();
        if (world != null) {
            world.portalUnlocked();
        }
        getWorld().removeObject(this);
    }
}