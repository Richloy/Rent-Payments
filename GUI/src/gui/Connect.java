package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.File;


public class Connect {
    List<String> payList;
    Path p = Paths.get("./payrecords.txt");

    public Connect() {
        this.payList = new ArrayList<String>();
    }
    
    public String[] getLastFivePayments() {
        
        String[] lastFive = new String[5];
        int x = 0;
        for(int i = payList.size()-1; i >= payList.size()-5; i--) {
            lastFive[x] = payList.get(i);
            x++;
        }
        return lastFive;
    }
    
    public double totalPayments() {
        
        double total = 0.0;
        for(String ele:payList) {
            total += Double.parseDouble(ele.split(" ")[1]);
            System.out.println(total);
        }
        
        return total;
    }
    
    public boolean confirmPayment(String date, int amount) {
        
        int input = JOptionPane.showConfirmDialog(new JFrame(), 
                 "You are about to record a payment, are the date and amount correct?\nDate:        " + date + 
                 "\nAmount:   €" + amount, 
                 "PAYMENT CONFIRMATION", JOptionPane.CANCEL_OPTION);
        String updateRental = date + " " + amount;
        if(input == 0) {
            updatePayment(updateRental);
            return true;
        } else {
            return false;
        }
    }
    
    public String connectToDoc() {
        
        try (InputStream in = Files.newInputStream(p);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                payList.add(line);
            }
        } catch (IOException x) {
            System.err.println(x);
        }        
        return "Connected";
    }

    void addPayment(String payment, String date) {
        
        System.out.println(payment + "Paid on " + date);
        
    }

    private void updatePayment(String updateRental) {
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/Rich/Desktop/Git/Rent-Payments/GUI/payrecords.txt", true))) {
            bw.newLine();
            bw.write(updateRental);
            // no need to close it.
            //bw.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void openAllRent() {
        
        try {
            if ((new File("c:/Users/Rich/Desktop/Git/Rent-Payments/GUI/payrecords.txt")).exists()) {
                Process p = Runtime
                   .getRuntime()
                   .exec("rundll32 url.dll,FileProtocolHandler c:/Users/Rich/Desktop/Git/Rent-Payments/GUI/payrecords.txt");
                p.waitFor();
            } else {
                System.out.println("File does not exist");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


