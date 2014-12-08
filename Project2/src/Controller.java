/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.*;

/**
 *
 * @author n00830864
 */
public class Controller
{
    
    public Controller()
    {
        
    }      
    
    /**
     * converts java.util.Date into java.sql.Date
     * @param utilDate
     * @return 
     */
    private java.sql.Date convertDate(java.util.Date utilDate)
    {
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
    
    /**
     * converts sql date into util date
     * @param date
     * @return 
     */
    private java.util.Date setDate(java.sql.Date date)
    {
        java.util.Date newDate = new java.util.Date(date.getTime());
        return newDate;
    }
    
    
    /**
     * gets current date
     * @return 
     */
    public java.sql.Date getCurrentDate()
    {
        java.sql.Date sqlDate;
        Calendar cal = Calendar.getInstance();
        java.util.Date utilDate = cal.getTime();
        sqlDate = this.convertDate(utilDate);
        return sqlDate;
    }
    

    /**
     * adds x days to the date
     * @param date
     * @param days
     * @return 
     */
    public java.sql.Date addDays(java.util.Date date, int days)
    {
        java.sql.Date sqlDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        java.util.Date utilDate = cal.getTime();
        sqlDate = this.convertDate(utilDate);
        return sqlDate;
    }
    
    /**
     * checks if the movie is a new release using the release date and invoice date
     * @param startDate
     * @param endDate
     * @return 
     */
    public boolean checkNewRelease(java.sql.Date startDate, java.sql.Date endDate)
    {
        boolean newRelease = false;
        
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(startDate);
        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(endDate);
        
        int year = Calendar.YEAR;
        int month = Calendar.MONTH;
        int day = Calendar.DAY_OF_MONTH;
        
        GregorianCalendar c1 = new GregorianCalendar();
        GregorianCalendar c2 = new GregorianCalendar();
        
        c1.set(firstCalendar.get(year), firstCalendar.get(month), firstCalendar.get(day));
        c2.set(secondCalendar.get(year), secondCalendar.get(month), secondCalendar.get(day));
    
        long difference = (c1.getTimeInMillis() - c2.getTimeInMillis())/86400000;

        if(difference <= 30)
            newRelease = true;
        return newRelease;
    }
    
    /**
     * gets the expected return date based on if it is a new release or not
     * @param invoiceDate
     * @param newRelease
     * @return 
     */
    public java.sql.Date getReturnDate(java.sql.Date invoiceDate, boolean newRelease)
    {
        if (newRelease == true)
            return this.addDays(this.setDate(invoiceDate), 4);
        else
            return this.addDays(this.setDate(invoiceDate), 5);
    }
    
    /**
     * gets rate based on genre and whether it's a new release or not
     * @param inventoryID
     * @param newRelease
     * @return 
     */
    public int getRate(int inventoryID, boolean newRelease)
    {
        String genre;
        int rate;
        
        /*
            run a query to get the genre of the inventoryID given store it in genre
        */
        
        //delete this after above is done
        genre = "cartoon";  // used for testing
        
        if (!genre.equals("cartoon") && newRelease == true)
            rate = 1;
        else if (!genre.equals("cartoon") && newRelease == false)
            rate = 2;
        else
            rate = 3;
        
        return rate;
    }
    
    /**
     * connects to database
     * @param user
     * @param password 
     */
    public void SQLConnection(String user, String password)
    {
        try {
            //SQL connection declarations
            String driver = "com.mysql.jdbc.Driver";
            String connection = "jdbc:mysql://23.229.180.72:3306/VideoStore";

            //Load mySQL JDBC driver and connect
            Class.forName(driver);
            Connection mySQLconn = (Connection)DriverManager.getConnection(connection,user,password);
            if(!mySQLconn.isClosed()){
                mySQLconn.close();
            }
        }
        catch (SQLException sqlExc){   
            System.out.println("\nSQLException Error\n" + sqlExc);
        }
        catch (ClassNotFoundException cnfeExc){
            System.out.println("Class Not Found Exception Error" + cnfeExc);
        }
    }
    
    /**
     * prints out main menu
     */
    public void mainMenu()
    {
        System.out.print("Main Menu\n");
        System.out.print("Please choose an option by inserting a number: \n");
        System.out.print("1) Checkout Movie\n");
        System.out.print("2) Return Movie\n");
        System.out.print("3) Adjust Balance\n");
        System.out.print("4) Locate Movie\n");
        System.out.print("5) Update Inventory\n");
        System.out.print("6) Reports\n");
        System.out.print("0) Exit\n\n");
    }
}
