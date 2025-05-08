package main;

import appeals.Appeal;

import javax.swing.*;

public class MyImage {
    JPanel panel;
    Appeal appeal;

    public int spriteNum = 1;
    public int spriteCount = 0;

    public MyImage(JPanel panel, Appeal appeal) {

        this.panel = panel;
        this.appeal = appeal;
    }

    public void update() {
        if (spriteCount > 10) {
            if (!appeal.getState()) {
                action = "change";
            } else {
                action = "stand";
            }

            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCount = 0;
            repaint();
        }
        spriteCount++;
    }

    public void draw() {

    }
}
