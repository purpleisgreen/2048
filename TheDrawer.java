package com.mycompany.yay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TheDrawer extends JFrame {
    public theMath theScreen;
    public int[][] info;
    private Image backgroundImage;
    private Image block0;
    private Image block1;
    private Image block2;
    private Image block3;
    private Image block4;
    private Image block5;
    private Image block6;
    private Image block7;
    private Image block8;
    private Image block9;
    private Image block10;
    private Image block11;
    private Image block12;
    private Image block13;
    private Image block14;
    private Image block15;
    public int blockWidth;
    public int blockHeight;
    public int scaledWidth;
    public int scaledHeight;
    public int highScore;

    public JLabel currentScoreLabel;
    public JLabel highScoreLabel;
    public JLabel gameEndedLabel;
    
    public boolean over;
    public TheDrawer(int[][] theInfo) {
        over = false;
        info = theInfo;
        setSize(500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("2048");
        JLabel instructions1Label = new JLabel("Use the arrow keys to move the titles in the desired direction");
        JLabel instructions2Label = new JLabel("Tiles of the same value merge into one title, duplicating in value");
        JLabel instructions3Label = new JLabel("Merge the tiles to get the 2048 tile!");
        currentScoreLabel = new JLabel("Current Score:");
        highScoreLabel = new JLabel("High Score: ");
        JButton newGameButton = new JButton("New Game");
        gameEndedLabel = new JLabel("Game Over. Click the new game button to play again.");
        
        gameEndedLabel.setForeground(Color.RED);

        titleLabel.setFont(new java.awt.Font("Georgia", java.awt.Font.BOLD, 50));
        instructions1Label.setFont(new java.awt.Font("Georgia", java.awt.Font.PLAIN, 12));
        instructions2Label.setFont(new java.awt.Font("Georgia", java.awt.Font.PLAIN, 12));
        instructions3Label.setFont(new java.awt.Font("Georgia", java.awt.Font.PLAIN, 12));
        currentScoreLabel.setFont(new java.awt.Font("Georgia", java.awt.Font.BOLD, 14));
        highScoreLabel.setFont(new java.awt.Font("Georgia", java.awt.Font.BOLD, 14));
        newGameButton.setFont(new java.awt.Font("Georgia", java.awt.Font.BOLD, 12));
        gameEndedLabel.setFont(new java.awt.Font("Georgia", java.awt.Font.BOLD, 15));

        add(titleLabel);
        add(instructions1Label);
        add(instructions2Label);
        add(instructions3Label);
        add(currentScoreLabel);
        add(highScoreLabel);
        add(newGameButton);
         add(gameEndedLabel);

        titleLabel.setBounds(30, -40, 200, 150);  // (x, y, width, height)
        instructions1Label.setBounds(50, 55, 500, 50);  // (x, y, width, height)
        instructions2Label.setBounds(50, 75, 500, 50);  // (x, y, width, height)
        instructions3Label.setBounds(50, 95, 500, 50);  // (x, y, width, height)
        currentScoreLabel.setBounds(250, -5, 500, 50);  // (x, y, width, height)
        highScoreLabel.setBounds(250, 15, 500, 50);  // (x, y, width, height)
        newGameButton.setBounds(325, 120, 120, 25);  // Position for the "New Game" button
         highScoreLabel.setBounds(250, 15, 500, 50);  // (x, y, width, height)
         gameEndedLabel.setBounds(20, 500, 500, 200);  // (x, y, width, height)
        
         gameEndedLabel.setVisible(false);
        setLayout(null); // Set layout to null for absolute positioning
        setVisible(true);

        // Action listener for the New Game button
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGameButtonActionPerformed(e);  // Call your custom method when the button is clicked
            }
        });

        // Key listener for handling arrow key events
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP && over == false) {
                    theScreen.moveUp();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN&& over == false) {
                    theScreen.moveDown();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT && over == false) {
                    theScreen.moveLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && over == false) {
                    theScreen.moveRight();
                }
            }
        });

        setFocusable(true); // Ensure the JFrame can receive focus
        requestFocusInWindow(); // Request focus to capture key events

        drawAgain();
    }

    // Event handler for the "New Game" button
    private void newGameButtonActionPerformed(ActionEvent evt) {
        System.out.println("Pressed");
        // Dispose of the current screen (close the current window)
        this.dispose();
        // Create a new instance of the game screen (new game)
        theMath newScreen = new theMath();
    }

    public void gameEnded(){
         gameEndedLabel.setVisible(true);
        over = true;
    }
    public void updateCurrentScore(int num) {
        currentScoreLabel.setText("Current Score: " + num);
        currentScoreLabel.setFont(new java.awt.Font("Georgia", java.awt.Font.BOLD, 14));
        add(currentScoreLabel);
        currentScoreLabel.setBounds(250, -5, 500, 50);  // (x, y, width, height)
    }

    public void updateHighScore(int num) {
        highScoreLabel.setText("High Score: " + num);
        highScoreLabel.setFont(new java.awt.Font("Georgia", java.awt.Font.BOLD, 14));
        add(highScoreLabel);
        highScoreLabel.setBounds(250, 15, 500, 50);  // (x, y, width, height)
    }

    public void drawAgain() {
        setImages();
        repaint();
    }

    public void mathScreenSetter(theMath theScreen) {
        this.theScreen = theScreen;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Background Stuff
        int imgWidth = backgroundImage.getWidth(null);
        int imgHeight = backgroundImage.getHeight(null);
        double scale = 0.83333333333;
        int xOffset = (int) ((getWidth() - imgWidth * scale) / 2);
        int yOffset = (int) ((getHeight() - imgHeight * scale) / 2);
        int scaledWidth = (int) (imgWidth * scale);
        int scaledHeight = (int) (imgHeight * scale);
        g.drawImage(backgroundImage, xOffset - 5, yOffset + 30, scaledWidth, scaledHeight, this);

        // Drawing blocks
        g.drawImage(block0, info[0][1], info[0][2], 84, 84, null);
        g.drawImage(block1, info[1][1], info[1][2], 84, 84, null);
        g.drawImage(block2, info[2][1], info[2][2], 84, 84, null);
        g.drawImage(block3, info[3][1], info[3][2], 84, 84, null);
        g.drawImage(block4, info[4][1], info[4][2], 84, 84, null);
        g.drawImage(block5, info[5][1], info[5][2], 84, 84, null);
        g.drawImage(block6, info[6][1], info[6][2], 84, 84, null);
        g.drawImage(block7, info[7][1], info[7][2], 84, 84, null);
        g.drawImage(block8, info[8][1], info[8][2], 84, 84, null);
        g.drawImage(block9, info[9][1], info[9][2], 84, 84, null);
        g.drawImage(block10, info[10][1], info[10][2], 84, 84, null);
        g.drawImage(block11, info[11][1], info[11][2], 84, 84, null);
        g.drawImage(block12, info[12][1], info[12][2], 84, 84, null);
        g.drawImage(block13, info[13][1], info[13][2], 84, 84, null);
        g.drawImage(block14, info[14][1], info[14][2], 84, 84, null);
        g.drawImage(block15, info[15][1], info[15][2], 84, 84, null);
    }

    public void setImages() {
        backgroundImage = new ImageIcon("Images/gridBackground.png").getImage();
        block0 = new ImageIcon("Images/tile" + info[0][0] + ".png").getImage();
        block1 = new ImageIcon("Images/tile" + info[1][0] + ".png").getImage();
        block2 = new ImageIcon("Images/tile" + info[2][0] + ".png").getImage();
        block3 = new ImageIcon("Images/tile" + info[3][0] + ".png").getImage();
        block4 = new ImageIcon("Images/tile" + info[4][0] + ".png").getImage();
        block5 = new ImageIcon("Images/tile" + info[5][0] + ".png").getImage();
        block6 = new ImageIcon("Images/tile" + info[6][0] + ".png").getImage();
        block7 = new ImageIcon("Images/tile" + info[7][0] + ".png").getImage();
        block8 = new ImageIcon("Images/tile" + info[8][0] + ".png").getImage();
        block9 = new ImageIcon("Images/tile" + info[9][0] + ".png").getImage();
        block10 = new ImageIcon("Images/tile" + info[10][0] + ".png").getImage();
        block11 = new ImageIcon("Images/tile" + info[11][0] + ".png").getImage();
        block12 = new ImageIcon("Images/tile" + info[12][0] + ".png").getImage();
        block13 = new ImageIcon("Images/tile" + info[13][0] + ".png").getImage();
        block14 = new ImageIcon("Images/tile" + info[14][0] + ".png").getImage();
        block15 = new ImageIcon("Images/tile" + info[15][0] + ".png").getImage();
    }
}
