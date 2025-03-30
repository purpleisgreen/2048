package com.mycompany.yay;

public class Block {
    public int row;
    public int column;
    public int value;
    public int xCoor;
    public int yCoor;
    public boolean merged;

    public Block(int row, int column, int value){
        this.row = row;
        this.column = column;
        this.value = value;
        merged = false;
    }
    
    public void setXCoor(int num){
        xCoor = num;
    }
    public void setYCoor(int num){
        yCoor = num;
    }
    public void setMerged(Boolean status){
        merged = status;
    }
    
    public int getXCoor(){
        return xCoor;
    }
    public int getYCoor(){
        return yCoor;
    }
    public int getValue(){
        return value;
    }
    
    public int getColumn(){
        return column;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public int getRow(){
        return row;
    }
}
