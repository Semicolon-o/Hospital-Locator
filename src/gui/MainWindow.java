package gui;

import algo.DijkstraAlgorithm;
import models.Graph;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainWindow extends JPanel implements resources.Constants {

    private Graph graph;
    private GraphPanel graphPanel;

    public MainWindow() {
        super.setLayout(new BorderLayout());
        setGraphPanel();
    }

    private void setGraphPanel() {
        graph = new Graph();
        graphPanel = new GraphPanel(graph);
        graphPanel.setPreferredSize(new Dimension(1000, 596));
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(graphPanel);
        scroll.getViewport().setViewPosition(new Point(0, 0));
        add(scroll, BorderLayout.CENTER);
        setTopPanel();
        setButtons();
    }

    private void setTopPanel() {
        JLabel info = new JLabel("COVID-19 Shortest Path Checker");
        Font f = info.getFont();
        info.setFont(new Font("Serif", Font.BOLD, 34));
        info.setForeground(new Color(255, 0, 0));
        info.setVerticalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(7, 0, 0));
        panel.add(info);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setPreferredSize(new Dimension(300, 60));
        add(panel, BorderLayout.NORTH);
    }

    private void setButtons() {
        JButton run = new JButton();
        setupIcon(run, "run");
        JButton reset = new JButton();
        setupIcon(reset, "reset");
        final JButton info = new JButton();
        setupIcon(info, "info");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(DrawUtils.parseColor("#000000"));
        buttonPanel.add(reset);
        buttonPanel.add(run);
        buttonPanel.add(info);

        reset.addActionListener((ActionEvent e) -> {
            graphPanel.reset();
        });

        info.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null,
                    resources.Constants.read,
                    "Information Panel",
                    JOptionPane.QUESTION_MESSAGE);
        });

        run.addActionListener((ActionEvent e) -> {
            DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
            try {
                dijkstraAlgorithm.run();
                graphPanel.setPath(dijkstraAlgorithm.getDestinationPath());
            } catch (IllegalStateException ise) {
                JOptionPane.showMessageDialog(null, ise.getMessage());
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupIcon(JButton button, String img) {
        try {
            Image icon = ImageIO.read(getClass().getResource(
                    "/resources/" + img + ".png"));
            ImageIcon imageIcon = new ImageIcon(icon);
            button.setIcon(imageIcon);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
