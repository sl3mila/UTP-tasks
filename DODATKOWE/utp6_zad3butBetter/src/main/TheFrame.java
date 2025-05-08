package main;

import appeals.Appeal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.*;

public class TheFrame {
    static JFrame frame;
    static JList jlist;
    static JLabel labelText, imageLabel;
    private Future future;
    private Appeal appeal;

    private BufferedImage stand1, stand2, change1, change2, change3;
    private String action = "stand";

    public int spriteNum = 1;
    public int spriteCount = 0;

    public void getImage() {
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

    private BufferedImage image = stand1;

    public void draw(Graphics2D g2d, int width, int height) {

        switch (action) {
            case "stand":
                if (spriteNum % 2 == 1) {
                    image = stand1;
                }
                if (spriteNum % 2 == 0) {
                    image = stand2;
                }
                break;
            case "change":
                if (spriteNum == 1) {
                    image = change1;
                }
                if (spriteNum == 2) {
                    image = change2;
                }
                if (spriteNum == 3) {
                    image = change3;
                }
                break;
        }

        int x = (width - image.getWidth(null)) / 2;
        int y = (height - image.getHeight(null)) / 2;

        g2d.drawImage(image, x, y, null);
    }

    public TheFrame() throws ExecutionException, InterruptedException {
        SwingUtilities.invokeLater(() -> {
            getImage();

            frame = new JFrame("i can not anymore, please help");

            JPanel mainPanel = new JPanel(new BorderLayout());
            JPanel bottomPanel = new JPanel();


            JPanel panelImage = new JPanel() {
                public void paintComponent(Graphics g) {
                    super.paintComponents(g);
                    Graphics2D g2d = (Graphics2D) g;
                    draw(g2d, getWidth(), getHeight());

                }

                public Dimension getPrefferedSize() {
                    return new Dimension(1300, 780);
                }

                public void update() {

                }
            };

            labelText = new JLabel();
            labelText.setText("nothing picked yet");


            String[] str = {"is it done?", "I beg you S-T-O-P", "what do you want?"};
            jlist = new JList<>(str);

            //TODO PAINT THE FUKING IMAGE
            imageLabel = new JLabel();

            ExecutorService executor = Executors.newSingleThreadExecutor();

            future = executor.submit(() -> {

                double drawinterval = 1_000_000_000/FPS;
                double delta = 0;
                long lastTime = System.nanoTime();
                long currentTime;
                long timer = 0;
                int drawCount = 0;

                while (!Thread.interrupted()) {

                    currentTime = System.nanoTime();

                    delta += (currentTime - lastTime) / drawinterval;
                    lastTime = currentTime;

                    if (delta >= 1) {

                        panelImage.repaint();
                        delta--;
                        drawCount++;
                    }

                    action = "stand";
                    panelImage.repaint();
                    appeal = new Appeal(true);
                    try {
                    if (!appeal.getState()) {
                        appeal.generateNewAppeal();
//                        while (appeal.isNotDone()){
                        action = "change";
                        panelImage.repaint();
                            Thread.sleep(1500);
//                        }
                    } else {
                        action = "stand";
                        panelImage.repaint();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                }
            });


            JButton specialWorkButton = new JButton("WORK!!!");
            specialWorkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            try {
                                appeal = new Appeal(true);
                                action = "stand";

                                if (!appeal.getState()) {
                                    appeal.generateNewAppeal();
                                    action = "change";
                                    Thread.sleep(1000);
                                }

                                while (appeal.isNotDone()) {
                                    System.out.println("");
                                }
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } finally {
                                if (appeal != null) {

                                }
                            }
                            return null;
                        }
                    });

                }
            });

            Button buttonAccept = new Button("Accept");
            buttonAccept.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    appeal.setState(false);
                }
            });
            Button buttonDecline = new Button("Decline");
            buttonDecline.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    appeal.setState(false);
                }
            });
            Button buttonExecute = new Button("Execute");
            buttonExecute.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    appeal.setState(false);
                }
            });
            Button buttonReward = new Button("Reward");
            buttonReward.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    appeal.setState(false);
                }
            });

            Button buttonNext = new Button("NEXT!!");

            jlist.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int index = jlist.getSelectedIndex();

                        if (index == 0) {

                            if (future.isDone()) {
                                labelText.setText("action is done");
                            } else {
                                labelText.setText("is NOT done yet");
                            }

                        } else if (index == 1) {
                            appeal.setNotDone(false);
                            future.cancel(true);
                            labelText.setText("STOPPED");

                        } else if (index == 2) {
                            if (!appeal.getState()) {
//                                JOptionPane.showMessageDialog(null, "WAIT", "result", JOptionPane.ERROR_MESSAGE);
                                labelText.setText("job done...\nbut you know...\nyou can stay and work more");

                            } else {
                                String result = "It's a " + appeal.getTypeName() + " \nfor ministry of " + appeal.getMinistryName();
//                                JOptionPane.showMessageDialog(null, result, "result", JOptionPane.INFORMATION_MESSAGE);
                                labelText.setText(result);

                            }

                        }
                    }
                }
            });

            bottomPanel.setLayout(new FlowLayout((FlowLayout.CENTER)));

            bottomPanel.add(jlist);
            bottomPanel.add(labelText);

            panelImage.setSize(panelImage.getWidth(), panelImage.getHeight());

            bottomPanel.add(buttonAccept);
            bottomPanel.add(buttonDecline);
            bottomPanel.add(buttonExecute);
            bottomPanel.add(buttonReward);
            bottomPanel.add(buttonNext);

            mainPanel.add(panelImage, BorderLayout.CENTER);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);

            frame.getContentPane().add(mainPanel);

            frame.pack();
            frame.setSize(1300, 1000);
            frame.setLocationRelativeTo(null);

            frame.setResizable(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            executor.shutdown();

        });
    }
}
