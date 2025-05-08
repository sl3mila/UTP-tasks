package main;


import PlsKillMe.WorkingList;
import appeals.Appeal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.*;

public class TheFrame {
    static JFrame frame;

//Animation panel bullshit
    public GamePanel gamePanel;
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    private Future future;
    private Appeal appeal;
    public String action;


    JPanel mainPanel, bottomPanel;
    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    //List and label
    WorkingList workingList;
    JList list;
    JLabel labelText;


    public TheFrame() throws ExecutionException, InterruptedException {
        SwingUtilities.invokeLater(() -> {

            frame = new JFrame("i can not anymore, please help");

            gamePanel = new GamePanel();


            mainPanel = new JPanel(new BorderLayout());
            bottomPanel = new JPanel();


            //TODO PAINT THE FUCKING IMAGE
            gamePanel.startThisShit();

            future = gamePanel.getFuture();
            appeal = gamePanel.getAppeal();
            action = gamePanel.getAction();

            workingList = new WorkingList(gamePanel);
            list = workingList.getList();
            labelText = workingList.getLabelText();


            //TODO working buttons
            JButton specialWorkButton = new JButton("WORK!!!");
            specialWorkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            try {
                                appeal = new Appeal(true);
                                gamePanel.setAction("stand");

                                if (!appeal.getState()) {
                                    appeal.generateNewAppeal();

                                    gamePanel.setAction("change");
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
            buttonNext.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gamePanel.setAction("change");

                    appeal.generateNewAppeal();
                }
            });


            //plz I beg you stop
            bottomPanel.add(list);
            bottomPanel.add(labelText);
            bottomPanel.setLayout(new FlowLayout((FlowLayout.CENTER)));



            gamePanel.setSize(gamePanel.getWidth(), gamePanel.getHeight());
            gamePanel.setDoubleBuffered(true);

            bottomPanel.add(buttonAccept);
            bottomPanel.add(buttonDecline);
            bottomPanel.add(buttonExecute);
            bottomPanel.add(buttonReward);
            bottomPanel.add(buttonNext);

            mainPanel.add(gamePanel, BorderLayout.CENTER);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);

            frame.getContentPane().add(mainPanel);

            frame.pack();
            frame.setSize(1300, 1000);
            frame.setLocationRelativeTo(null);

            frame.setResizable(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            //shut down executor
            gamePanel.shutdownExec();

        });
    }
}
