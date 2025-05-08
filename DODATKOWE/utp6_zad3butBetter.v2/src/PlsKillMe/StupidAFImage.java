package PlsKillMe;

import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StupidAFImage {
    public BufferedImage stand1, stand2, change1, change2, change3;

    public String action  ;
    public String getAction() {
        return this.action;
    }
    public void setAction(String action){
        this.action = action;
    }

    public int spriteCount = 0;
    public int spriteNum = 1;

    JPanel panel;

    int width = 1300;
    int height = 730;

    public StupidAFImage(JPanel panel, String action) {
        this.panel = panel;

        this.action = action;

        getImage();
    }

    public void update() {
        spriteCount++;
        if (spriteCount > 75) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCount = 0;
        }
    }

    private void getImage() {

        try {
            stand1 = ImageIO.read(getClass().getResourceAsStream("../graphics/stand1.png"));
            stand2 = ImageIO.read(getClass().getResourceAsStream("../graphics/stand2.png"));
            change1 = ImageIO.read(getClass().getResourceAsStream("../graphics/change1.png"));
            change2 = ImageIO.read(getClass().getResourceAsStream("../graphics/change2.png"));
            change3 = ImageIO.read(getClass().getResourceAsStream("../graphics/change3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //draw
    String lastAction = action;
    String recentAction = "";
    int lastSpriteNum = 1;
    int newSpriteNum;

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (action) {
            case "stand":
                recentAction = "stand";
                if (recentAction != lastAction) {
                    spriteNum = 1;
                }

                if (spriteNum % 2 == 1) {
                    image = stand1;
                }
                if (spriteNum % 2 == 0) {
                    image = stand2;
                }

                lastAction = recentAction;
                break;
            case "change":

                recentAction = "change";

                if (lastSpriteNum == 3 && newSpriteNum == 1) {
                    action = "stand";
                }
                if (lastSpriteNum != newSpriteNum) {
                    lastSpriteNum = newSpriteNum;
                }

                if (recentAction != lastAction) {
                    spriteNum = 1;
                }

                if (spriteNum == 1) {
                    newSpriteNum = 1;
                    image = change1;
                }
                if (spriteNum == 2) {
                    newSpriteNum = 2;
                    image = change2;
                }
                if (spriteNum == 3) {
                    newSpriteNum = 3;
                    image = change3;
                }

                lastAction = recentAction;

                break;
        }

        int x = (width - 1300);
        int y = (height - 730);

        g2.drawImage(image, x, y, null);
    }
}
