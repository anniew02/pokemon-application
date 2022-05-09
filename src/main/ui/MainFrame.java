package ui;

import model.PartyPokemon;

import javax.swing.*;
import java.awt.*;

// Creates the main menu of the GUI
public class MainFrame extends JFrame {
    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 600;
    private JFrame frame;
    private MainPanel panel;

    // EFFECTS: constructs the main frame with the main panel
    public MainFrame() throws InterruptedException {
        frame = new JFrame();
        createSplashImage();
        panel = new MainPanel();
        panel.createPanel();
        createFrame();
    }

    // EFFECTS: displays the splash image at the beginning of the application
    public void createSplashImage() throws InterruptedException {
        ImageIcon imgIcon = new ImageIcon("pokemon_square.jpg");
        Image img = imgIcon.getImage();
        Image imgScaled = img.getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(imgScaled);
        frame.pack();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.add(new JLabel(imgIcon));
        frame.setVisible(true);
        Thread.sleep(2000);
    }

    // EFFECTS: creates the main frame settings
    private void createFrame() {
        frame.setTitle("Party Pokemon");
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setContentPane(panel);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
        frame.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        new MainFrame();
    }
}
