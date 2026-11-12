import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CrystalCaveWorld2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CrystalCaveWorld extends World
{
    int[][] caveMap = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1},
            {1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
            {1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1},
            {1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},     
        };
    public CrystalCaveWorld()
    {    
        super(900, 600, 1);
        int tileSize = 45; // Adjust tile size so the whole grid fits nicely on your 770x600 canvas
        int startX = 80 ;   // Left padding to center your maze
        int startY = 50;   // Top padding

        for (int row = 0; row < caveMap.length; row++)
        {
            for (int col = 0; col < caveMap[row].length; col++)
            {
                if (caveMap[row][col] == 1)
                {
                    int x = startX + (col * tileSize);
                    int y = startY + (row * tileSize);
                    addObject(new stone1(), x, y);
                }
            }
        }

        PlayerStage2 Player = new PlayerStage2();
        addObject(Player,30,455);

        // Membuat efek gelap
        Darkness darkness = new Darkness(Player);
        addObject(darkness, 450, 300);

        // Mengatur urutan tampilan
        setPaintOrder(Player.class, Darkness.class);    

        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
