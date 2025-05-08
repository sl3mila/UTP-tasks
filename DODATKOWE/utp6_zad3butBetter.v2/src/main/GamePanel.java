package main;

import PlsKillMe.StupidAFImage;
import appeals.Appeal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GamePanel extends JPanel {
    private int FPS = 60;

    //Future
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future future;
    public Future getFuture() {
        return this.future;
    }

    //bullshit
    private Appeal appeal = new Appeal(false);
    public Appeal getAppeal() {
        return this.appeal;
    }

    private JPanel panelImage = new JPanel();
    private String action = "stand";
    private StupidAFImage image = new StupidAFImage(panelImage, action);
    public String getAction() {
        return this.action;
    }
    public void setAction(String action) {
        image.setAction(action);
    }


    //class
    public GamePanel() {

        this.setPreferredSize(new Dimension(1300, 1000));
    }

    //methods
    public void startThisShit() {

        this.future = executor.submit(() -> {

            double drawinterval = 1_000_000_000 / FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;

            long timer = 0;
            int drawCount = 0;


            while (!Thread.interrupted()) {

                startAnimation();

                /*if (action.equals("change")) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    image.setAction("stand");
                }*/

                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawinterval;
                lastTime = currentTime;

                if (delta >= 1) {
                    update();
                    repaint();

                    delta--;
                    drawCount++;
                }

                /*if (timer >= 1_000_000_000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }*/
            }

        });
    }

    public void startAnimation() {
        Timer timerek = new Timer(1_000_000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                update();
                repaint();

                /*try {
                    if (!appeal.getState()) {
                        appeal.generateNewAppeal();
//                        while (appeal.isNotDone()){
                        action = "change";

                        Thread.sleep(1000);
//                        }
                    } else {
                        action = "stand";
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }*/
            }
        });

        timerek.start();
    }

    public void update() {

//        image.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponents(g);

        Graphics2D g2 = (Graphics2D) g;

        image.draw(g2);

        g2.dispose();
    }

    public void shutdownExec() {
        executor.shutdown();
    }
}
