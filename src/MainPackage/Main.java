package MainPackage;

import gui.MainWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("Failed To Start The Application!");
            System.out.println(ex.getMessage());
        }
        JFrame j = new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setUndecorated(true);
        j.add(new MainWindow());
        j.setVisible(true);
        j.setTitle("Path Tracker");
        JOptionPane.showMessageDialog(null,
                resources.Constants.read,
                "Information Panel",
                JOptionPane.QUESTION_MESSAGE);
    }

}
