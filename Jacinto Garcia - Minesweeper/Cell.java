
/**
 * Write a description of class Cell here.
 * 
 * I will be representing a mine using the number: 42
 * 
 * @author Wiebe 
 * @version 10.11.16
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell implements ActionListener{
    //Variables you need to work with
    private int value;

    //Variables you don't need to worry about or care about.
    private JButton button;
    /**
     * This constructor is complete and does not need modification
     */
    public Cell(){
        button = new JButton();
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(20,20));
        button.setMargin(new Insets(0,0,0,0));
        value = 0;
    }

    /** This Method tells me if the cell is a bomb.
     * 
     * @return True if it is a bomb, otherwse false.
     */
    boolean isBomb(){
        if(value == 42){
            return true;

        }else{
            return false;
        }
    }

    //Additional Methods may be required. Please make them yourself.
    public void addNum(){
        value++;
    }

    public void setBomb(){
        value = 42;
    }
    //The following methods are used for the User Inferface. These methods are fully functional and do not need to be modified.

    public void checkCell(){
        button.setEnabled(false);
        displayValue();
    }

    public void displayValue(){
        //u2600
        if(this.isBomb()){
            button.setText("\u2600");
            button.setBackground(Color.RED);
        }else if(value!=0){
            button.setText(String.valueOf(value));
        }
    }

    public JButton getButton() {
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkCell();
    }
}
