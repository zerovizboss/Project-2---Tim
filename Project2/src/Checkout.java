/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.sql.*;


/**
 *
 * @author n00830864
 */
public class Checkout 
{
    private String customerID;
    private String newCustomerID;
    private String LastName;
    private String FirstName;
    private String invoiceID;
    private String answer;
        
    private int balance = 0;
    private int TotalRentals = 0;
    private int count = 0;
    private int rate;
    private int inventoryID;
    private double totalCost = 0;
    private double rateFee;
        
    private boolean exit = false;
    private boolean testExit = false;
    private boolean newRental;
        
    private Date invoiceDate;
    private Date releaseDate;
    private Date expReturnDate;
    
    private Controller controller = new Controller();
    
    /**
     * Checkout constructor, called whenever checking out movies
     * @throws IOException
     * @throws SQLException 
     */
    public Checkout() throws IOException, SQLException
    {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));

        //gets customerID
        System.out.print("Enter Customer ID: \n");
        customerID = br.readLine();
        
        /*
            @Donny
            check if customerID inserted is a customerID in table, if no match make newCustomerID = "null"
        */
        
        //Delete this when the above comment is done
        newCustomerID = "notNull";      //strictly for testing
        
        //while loop to check if the customer already exists, if not create a new customer
        do
        {
            if (newCustomerID.equals("null"))
            {
                //retrieve information for new customer
                System.out.println("Enter First Name: ");
                FirstName = br.readLine();
                System.out.println("Enter Last Name: ");
                LastName = br.readLine();
                balance = 0;
                TotalRentals = 0;
                
                /*
                    create a new row in database here
                    use FirstName, LastName, balanc, and TotalRentals for input values in new row (customerID is Auto-increment) 
                */
            }
            else    // if not a new customer
            {
                /*
                    query the balance of the customer and store in balance
                */
                // Delete balance=1; when above query is implemented
                balance = 0;    // Strictly for testing
                if (balance != 0)   //checks if balance is not 0
                {
                    System.out.println("\nCustomer owes money: ");
                    while (exit == false)
                    {
                        System.out.println("Do you want to pay off the balanace?");
                        answer = br.readLine();
                        
                        switch (answer.toLowerCase()) 
                        {
                            case "yes":
                                //call adjust balance
                                exit = true;
                                break;
                            case "no":
                                exit = true;
                                break;
                            default:
                                System.out.print("Invalid Answer\n");
                                exit = false;
                                break;
                        }
                    }
                }
                
                /*
                    write a query that retrieves customer TotalRentals and store it in TotalRentals
                */
                
                if (TotalRentals == 2)  //if customer needs to return movies
                {
                    exit = false;
                    while (exit == false)
                    {
                        System.out.print("\nDo you want to return any movies?");
                        answer = br.readLine();
                        
                        switch (answer)
                        {
                            case "yes":
                                //call return
                                exit = true;
                                break;
                            case "no":
                                return;
                            default:
                                System.out.print("Invalid Answer\n");
                                exit = false;
                                break;
                        }
                    }
                }
                
                //delete this when you get above totalrentals query complete
                TotalRentals = 1;   //for testing
                
                if (TotalRentals < 2)   //if customer can check out a movie
                {
                    invoiceDate = controller.getCurrentDate();
  
                    /*
                        Insert a new row into Invoice table using invoiceDate, totalCost (currently = 0, will change after we get the lines), and customerID
                    */
                    
                    count = 0;  //line ID
                    
                    //accounts for multiple lines
                    while (count < 2)
                    {
                        //makes sure TotalRentals doesn't exced 2 rentals
                        if (TotalRentals < 2)
                        {
                            count++;
                            TotalRentals++;
                            
                            /*
                                write query to get the invoiceID for above invoice just created (max invoiceID) 
                            */
                            System.out.print("\nInsert Inventory ID: ");
                            inventoryID = Integer.parseInt(br.readLine());
                            
                            /*
                                write a query to get releaseDate based on InventoryID
                            */
                            
                            newRental = controller.checkNewRelease(releaseDate, invoiceDate);
                            expReturnDate = controller.getReturnDate(invoiceDate, newRental);
                            rate = controller.getRate(inventoryID, newRental);
                            
                            /*
                                insert invoiceID, count (lineID), expReturnDate, rate, and inventoryID into table as new row
                            */
                            
                            /*
                                write a query to get rateFee based on rate
                            */
                            
                            totalCost = totalCost + rateFee; 
                        }
                        else
                        {
                            System.out.print("\nYou have reached the Total Rentals allowed.\n");
                            count = 2;
                        }
                    }
                    
                    /*
                        write a query to modify TotalCost in invoice table using totalCost
                    */
                }
                testExit = true; //for testing
            }
        } while (testExit == false); // this will be if customerID = null)
        
    }
}
