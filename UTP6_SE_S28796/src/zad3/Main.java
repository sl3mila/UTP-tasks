/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad3;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
  static JFrame frame;
  static  JList jlist;
  static JLabel label;



  public static void main(String[] args) throws ExecutionException, InterruptedException {
    SwingUtilities.invokeLater( () -> {
      frame = new JFrame("i can not anymore, please help");

      Panel panel = new Panel();

      label = new JLabel();
      label.setText("nothing picked yet");

      String[] str = {"what state?", "I beg you S-T-O-P", "WHAT? HAPPEND?"};
      jlist = new JList<>(str);

      ExecutorService executor = Executors.newSingleThreadExecutor();
      Future<String> future = (Future<String>) executor.submit(() -> {

        for (int i = 0; i < 100; i++) {
          try {
            Thread.sleep(500);

          } catch (InterruptedException e) {
            System.out.println("STOPPED");
            Thread.currentThread().interrupt();

          }
          System.out.println("GOD PLEASE STOP!!");
        }
        return "OH GOD IT STOPPED";
      });

      jlist.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
          if (!e.getValueIsAdjusting()) {
            int index = jlist.getSelectedIndex();

            if (index == 0) {

              if (future.isDone()) {
                label.setText("action is done");
              } else {
                label.setText("is NOT done yet");
              }

            } else if (index == 1) {
              future.cancel(true);
              label.setText("STOPPED");

            } else if (index == 2) {
              try {
                if (!future.isDone()){
                  JOptionPane.showMessageDialog(null, "WAIT", "result", JOptionPane.ERROR_MESSAGE);

                } else {
                  String result = future.get();
                  JOptionPane.showMessageDialog(null, result, "result", JOptionPane.INFORMATION_MESSAGE);

                }
              } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException(ex);
              }
            }
          }
        }
      });

      panel.add(jlist);
      panel.add(label);
      frame.add(panel);

      frame.pack();
      frame.setSize(500, 500);

      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);

      executor.shutdown();

    });
  }
}
