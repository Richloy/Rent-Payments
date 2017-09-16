package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class Rent {
    
    private final double rentOwingToDate;
    
    public Rent() throws ParseException {
        this.rentOwingToDate = calculateRent();
    }    
    
    public static double calculateRent() throws ParseException {
        
        double rentTotal = 0.0;
        ArrayList<Date> mondays = new ArrayList<>();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        String start = "01/12/2016";
        String end = dateFormat.format(today);
        
        Calendar scal=Calendar.getInstance();
        scal.setTime(dateFormat.parse(start));
        Calendar ecal=Calendar.getInstance();
        ecal.setTime(dateFormat.parse(end));
        ecal.add(Calendar.DATE, 7);

        while(!scal.equals(ecal)){
            scal.add(Calendar.DATE, 1);
            if(scal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
                mondays.add(scal.getTime());
                rentTotal += 69.61;
            }
        }
        System.out.println(rentTotal);
        return rentTotal;
    }
    
    public double getRentOwingToDate() {
        return this.rentOwingToDate;
    }
    
    public boolean isDateValid(int year, int month, int day) {
        boolean dateIsValid = true;
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            dateIsValid = false;
        }
        return dateIsValid;
    }
}