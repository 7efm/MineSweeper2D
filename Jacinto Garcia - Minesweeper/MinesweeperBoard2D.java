
/**
 * Write a description of class Minesweeper here.
 * 
 * @author (Jacinto Garcia) 
 * @version (11.26.18)
 */
import java.lang.Math;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MinesweeperBoard2D{
    Cell[][] board;
    int rows;
    int columns;
    public MinesweeperBoard2D(){
        this(10,10);

    }

    public MinesweeperBoard2D(int row, int column){
        //Put the constructor here.
        rows = row;
        columns = column;
        board = new Cell[rows][columns];

        //These pieces are for the GUI.
        JFrame frame = new JFrame();
        frame.add(addCells());

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try{
            addBombs(43);
        } catch (Exception e){
            System.out.println("Error: " + e);
        }
        addNums();
        printBoard();
    }

    public void addBombs(int bombs) throws Exception{
        if(bombs < 0 || bombs > rows*columns){
            throw new Exception("Too many bombs or invalid number of bombs");
        }

        int x = 0; //
        while(x < bombs){
            int rCol = (int)(Math.random()*(columns)); //Generates random number for Columns
            int rRow = (int)(Math.random()*(rows));    //Generates random number for Rows
            if(!board[rRow][rCol].isBomb()){
                board[rRow][rCol].setBomb();
                x++;
            }

        }
    }

    public void addNums(){
        for(int rowC = 0; rowC < rows; rowC++){
            for(int columnC = 0; columnC < columns; columnC++){
                if(!board[rowC][columnC].isBomb()){
                    //Checking right and left
                    if(columnC%columns !=(columns -1)){
                        if(board[rowC][columnC+1].isBomb()){
                            board[rowC][columnC].addNum();
                        }
                    }
                    if(columnC%columns != 0){
                        if(board[rowC][columnC-1].isBomb()){
                            board[rowC][columnC].addNum();
                        }
                    }

                    if(rowC < (rows-1)){
                        //Checking bottom
                        if(board[rowC+1][columnC].isBomb()){
                            board[rowC][columnC].addNum();
                        }

                        if(columnC%columns !=(columns -1)){
                            //checks bottom right
                            if(board[rowC+1][columnC+1].isBomb()){
                                board[rowC][columnC].addNum();
                            }
                        }

                        if(columnC%columns != 0){
                            //checks bottom left
                            if(board[rowC+1][columnC-1].isBomb()){
                                board[rowC][columnC].addNum();
                            }
                        }
                    }

                    if(rowC > 0){
                        //checks top
                        if(board[rowC-1][columnC].isBomb()){
                            board[rowC][columnC].addNum();
                        }

                        //checks top left
                        if(columnC%columns != 0){
                            if(board[rowC-1][columnC-1].isBomb()){
                                board[rowC][columnC].addNum();
                            }
                        }
                        //checks top right
                        if(columnC%columns !=(columns -1)){
                            if(board[rowC-1][columnC+1].isBomb()){
                                board[rowC][columnC].addNum();
                            }
                        }

                    }
                }
            }
        }
    }

    /**This method is used for testing and will be deleted if using the GUI.
     *  It is still required for all students.
     */
    public void printBoard(){
        for(int rowC = 0; rowC < rows; rowC++){
            for(int columnC = 0; columnC < columns; columnC++){
                if(board[rowC][columnC].isBomb()){
                    System.out.print("X"); System.out.print(" ");
                }
                else{
                    System.out.print("O"); System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    public JPanel addCells(){
        JPanel panel = new JPanel(new GridLayout(rows,columns));
        for(int rowC = 0; rowC < rows; rowC++){
            for(int columnC = 0; columnC < columns; columnC++){
                board[rowC][columnC]= new Cell();
                panel.add(board[rowC][columnC].getButton());
            }
        }
        return panel;
    }

}
