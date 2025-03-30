//This version was made using 2D arrays, hashtables and linked lists are other ways this project could be achieved!

package com.mycompany.yay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class theMath extends JFrame {
    public int currentScore;
    public int highScore;
    public Block[][] arr;
    Block currentBlock;
    public int[][] infoPasser;
    public TheDrawer currentScreen;

    public theMath() {
        currentScore = 0;
        loadHighScore();
        arr = new Block[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                currentBlock = new Block(i, j, 0);
                arr[i][j] = currentBlock;
            }
        }
        setPositions();
        infoPasser = new int[16][3];
        SetInfo();
        addBlock();
        addBlock();
        SetInfo();
       TheDrawer draw = new TheDrawer(infoPasser);
        draw.mathScreenSetter(this);
        displaySetter(draw);
        checkScore();
    }

    public void displaySetter(TheDrawer current) {
        currentScreen = current;
    }

    public void refreshDisplay() {
      SetInfo();
      currentScreen.drawAgain();
    }
    
    public void checkScore(){
        if(currentScore > highScore){
            saveHighScore();
            currentScreen.updateCurrentScore(currentScore);
            currentScreen.updateHighScore(currentScore);
            highScore = currentScore;
        }
        else{
            currentScreen.updateCurrentScore(currentScore);
            currentScreen.updateHighScore(highScore);
        }
    }
public void addBlock() {
    boolean added = false;
    while (!added) {
        int row = (int) (Math.random() * 4);
        int col = (int) (Math.random() * 4);

        if (arr[row][col].getValue() == 0) {
            arr[row][col].setValue(2);
            added = true;
        }
    }
    if(isGameOver() == true){
        currentScreen.gameEnded();
    }
}

public boolean isGameOver() {
    // Check for empty cells
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            if (arr[i][j].getValue() == 0) {
                return false; // At least one empty cell exists
            }
        }
    }

    // Check for possible merges (adjacent cells with the same value)
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            int currentValue = arr[i][j].getValue();

            // Check right
            if (j < 3 && currentValue == arr[i][j + 1].getValue()) {
                return false; // Merge possible to the right
            }

            // Check down
            if (i < 3 && currentValue == arr[i + 1][j].getValue()) {
                return false; // Merge possible downward
            }
        }
    }

    // No empty cells and no possible merges
    return true;
}


   public void endGame(){
      // System.out.println("called");
        currentScreen.gameEnded();
    }

    public void SetInfo() {
        Block currentBlock;
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                currentBlock = arr[i][j];
                infoPasser[counter][0] = currentBlock.getValue(); // Block value
                infoPasser[counter][1] = currentBlock.getXCoor(); // X coordinate
                infoPasser[counter][2] = currentBlock.getYCoor(); // Y coordinate
                counter++;
            }
        }
    }

    public void setPositions() {
        Block currentBlock;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                currentBlock = arr[i][j];
                // Setting XCoor
                if (j == 0) {
                    currentBlock.setXCoor(50);
                } else if (j == 1) {
                    currentBlock.setXCoor(150);
                } else if (j == 2) {
                    currentBlock.setXCoor(250);
                } else if (j == 3) {
                    currentBlock.setXCoor(351);
                }
                // Setting Y Coor
                if (i == 0) {
                    currentBlock.setYCoor(207);
                } else if (i == 1) {
                    currentBlock.setYCoor(307);
                } else if (i == 2) {
                    currentBlock.setYCoor(407);
                } else if (i == 3) {
                    currentBlock.setYCoor(508);
                }
            }
        }
    }

    private void saveHighScore() {
        try (BufferedWriter output = new BufferedWriter(new FileWriter("src/HighScore.txt"))) {
            output.write(String.valueOf(currentScore));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadHighScore() {
        try (BufferedReader input = new BufferedReader(new FileReader("src/HighScore.txt"))) {
            String line = input.readLine();
            highScore = Integer.valueOf(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void moveDown() {
        boolean moved = false;
        for (int j = 0; j < 4; j++) {
            // Shift values down
            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    if (arr[k + 1][j].getValue() == 0 && arr[k][j].getValue() != 0) {
                        arr[k + 1][j].setValue(arr[k][j].getValue());
                        arr[k][j].setValue(0);
                        moved = true;
                    }
                }
            }

            // Combine adjacent blocks with the same value
            for (int i = 3; i > 0; i--) {
                if (arr[i][j].getValue() != 0 && arr[i][j].getValue() == arr[i - 1][j].getValue()) {
                    arr[i][j].setValue(arr[i][j].getValue() * 2);
                    arr[i - 1][j].setValue(0);
                    currentScore += arr[i][j].getValue();
                    moved = true;
                }
            }

            // Shift again after merging
            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    if (arr[k + 1][j].getValue() == 0 && arr[k][j].getValue() != 0) {
                        arr[k + 1][j].setValue(arr[k][j].getValue());
                        arr[k][j].setValue(0);
                        moved = true;
                    }
                }
            }
        }

        if (moved) {
            addBlock();
            checkScore();
            refreshDisplay();
        }
    }
    
    public void moveUp() {
    boolean moved = false;
    for (int j = 0; j < 4; j++) {
        // Shift values up
        for (int i = 3; i > 0; i--) {
            for (int k = 0; k < 3; k++) {
                if (arr[k][j].getValue() == 0 && arr[k + 1][j].getValue() != 0) {
                    arr[k][j].setValue(arr[k + 1][j].getValue());
                    arr[k + 1][j].setValue(0);
                    moved = true;
                }
            }
        }

        // Combine adjacent blocks with the same value
        for (int i = 0; i < 3; i++) {
            if (arr[i][j].getValue() != 0 && arr[i][j].getValue() == arr[i + 1][j].getValue()) {
                arr[i][j].setValue(arr[i][j].getValue() * 2);
                arr[i + 1][j].setValue(0);
                currentScore += arr[i][j].getValue();
                moved = true;
            }
        }

        // Shift again after merging
        for (int i = 3; i > 0; i--) {
            for (int k = 0; k < 3; k++) {
                if (arr[k][j].getValue() == 0 && arr[k + 1][j].getValue() != 0) {
                    arr[k][j].setValue(arr[k + 1][j].getValue());
                    arr[k + 1][j].setValue(0);
                    moved = true;
                }
            }
        }
    }

    if (moved) {
        addBlock();
        checkScore();
        refreshDisplay();
    }
}
public void moveLeft() {
    boolean moved = false;
    for (int i = 0; i < 4; i++) {
        // Shift values left
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (arr[i][k].getValue() == 0 && arr[i][k + 1].getValue() != 0) {
                    arr[i][k].setValue(arr[i][k + 1].getValue());
                    arr[i][k + 1].setValue(0);
                    moved = true;
                }
            }
        }

        // Combine adjacent blocks with the same value
        for (int j = 0; j < 3; j++) {
            if (arr[i][j].getValue() != 0 && arr[i][j].getValue() == arr[i][j + 1].getValue()) {
                arr[i][j].setValue(arr[i][j].getValue() * 2);
                arr[i][j + 1].setValue(0);
                currentScore += arr[i][j].getValue();
                moved = true;
            }
        }

        // Shift again after merging
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (arr[i][k].getValue() == 0 && arr[i][k + 1].getValue() != 0) {
                    arr[i][k].setValue(arr[i][k + 1].getValue());
                    arr[i][k + 1].setValue(0);
                    moved = true;
                }
            }
        }
    }

    if (moved) {
        addBlock();
        checkScore();
        refreshDisplay();
    }
}
public void moveRight() {
    boolean moved = false;
    for (int i = 0; i < 4; i++) {
        // Shift values right
        for (int j = 3; j > 0; j--) {
            for (int k = 3; k > 0; k--) {
                if (arr[i][k].getValue() == 0 && arr[i][k - 1].getValue() != 0) {
                    arr[i][k].setValue(arr[i][k - 1].getValue());
                    arr[i][k - 1].setValue(0);
                    moved = true;
                }
            }
        }

        // Combine adjacent blocks with the same value
        for (int j = 3; j > 0; j--) {
            if (arr[i][j].getValue() != 0 && arr[i][j].getValue() == arr[i][j - 1].getValue()) {
                arr[i][j].setValue(arr[i][j].getValue() * 2);
                arr[i][j - 1].setValue(0);
                currentScore += arr[i][j].getValue();
                moved = true;
            }
        }

        // Shift again after merging
        for (int j = 3; j > 0; j--) {
            for (int k = 3; k > 0; k--) {
                if (arr[i][k].getValue() == 0 && arr[i][k - 1].getValue() != 0) {
                    arr[i][k].setValue(arr[i][k - 1].getValue());
                    arr[i][k - 1].setValue(0);
                    moved = true;
                }
            }
        }
    }

    if (moved) {
        addBlock();
        checkScore();
        refreshDisplay();
    }
}
}


