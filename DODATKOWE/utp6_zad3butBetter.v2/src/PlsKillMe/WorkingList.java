package PlsKillMe;

import appeals.Appeal;
import main.GamePanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.concurrent.Future;

public class WorkingList extends JList {


    private JLabel labelText;
    public JLabel getLabelText() {
        return  labelText;
    }

    //THE GETTERS
    private GamePanel gamePanel;
    private Future future;
    private Appeal appeal;

    private JPanel panel;

    String[] str = {"is it done?", "I beg you S-T-O-P", "what do you want?"};

    JList list;
    public JList getList() {
        return list;
    }

    public WorkingList(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

            future = gamePanel.getFuture();
            appeal = gamePanel.getAppeal();
            labelText = new JLabel();

            labelText.setText("nothing picked yet");
            list = new JList<>(str);

            list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int index = list.getSelectedIndex();

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
    }
}
