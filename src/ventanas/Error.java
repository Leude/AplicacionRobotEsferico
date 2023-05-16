package ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Error extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Error() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
        Error dialog = new Error();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
