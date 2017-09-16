package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;


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
}


