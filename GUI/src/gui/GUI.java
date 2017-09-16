/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.text.ParseException;

/**
 *
 * @author Rich
 */
public class GUI {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {
        
        //UI ui = new UI();
        PaymentGUI ui = new PaymentGUI();
        ui.setVisible(true);
        // TODO code application logic here
    }
    
}
