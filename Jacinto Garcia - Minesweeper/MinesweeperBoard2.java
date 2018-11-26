
/**
 * Write a description of class Minesweeper here.
 * 
 * @author (Jacinto Garcia) 
 * @version (11.5.18)
 */
import java.lang.Math;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MinesweeperBoard2{
    Cell[] board;
    int rows;
    int columns;
    public MinesweeperBoard2(){
        this(10,10);

    }

    public MinesweeperBoard2(int row, int column){
        //Put the constructor here.
        rows = row;
        columns = column;
        board = new Cell[rows*columns];

        //These pieces are for the GUI.
        JFrame frame = new JFrame();
        frame.add(addCells());

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try{
            addBombs(60);
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
        int x = 0;
        while(x < bombs){
            int randNum = (int)(Math.random()*(rows*columns));
            if(!board[randNum].isBomb()){
                board[randNum].setBomb();
                x++;
            }
        }
    }

    public void addNums(){
        for(int i = 0; i <= (rows*columns)-1; i++){
            if(!board[i].isBomb()){
                //checks right
                if(i%columns !=(columns -1)){
                    if(board[i+1].isBomb()){
                        board[i].addNum();
                    }
                }
                
                if(i%columns != 0){
                    //checks left
                    if(board[i-1].isBomb()){
                        board[i].addNum();
                    }
                }
                
                if(i < ((rows*columns)-columns)){
                    //checks bottom
                    if(board[i+columns].isBomb()){
                        board[i].addNum();
                    }
                    if(i%columns !=(columns -1)){
                        //checks bottom right
                        if(board[i+(columns+1)].isBomb()){
                            board[i].addNum();
                        }
                    }
                    if(i%columns != 0){
                        //checks bottom left
                        if(board[i+(columns-1)].isBomb()){
                            board[i].addNum();
                        }
                    }
                }
                
                if(i > (columns)-1){
                    //checks top
                    if(board[i-columns].isBomb()){
                        board[i].addNum();
                    }
                    //checks top left
                    if(i%columns != 0){
                        if(board[i-(columns+1)].isBomb()){
                            board[i].addNum();
                        }
                    }
                    //checks top right
                    if(i%columns !=(columns -1)){
                        if(board[i-(columns-1)].isBomb()){
                            board[i].addNum();
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
        int counter = 0;
        for(int i = 0; i < rows; i++){
            for(int l = 0; l < columns; l++){
                if(board[counter].isBomb()){
                    System.out.print("X"); System.out.print(" ");
                }
                else{
                    System.out.print("O"); System.out.print(" ");
                }
                counter++;
            }
            System.out.println("");
        }
    }

    public JPanel addCells(){
        JPanel panel = new JPanel(new GridLayout(rows,columns));
        for(int i = 0; i< rows*columns; i++){
            board[i]= new Cell();
            panel.add(board[i].getButton());
        }
        return panel;
    }

}
