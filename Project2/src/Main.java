/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.io.*;

/**
 *
 * @author Timothy Folds, Donald Dedman, Ashley Barr
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws SQLException, IOException
    {
        String password;
        String userId;
        String id = "Video";
        String passwrd = "unfproject2";
        
        int option;
        
        boolean exit = false;
        boolean exit2 = false;
        
        Controller control = new Controller();
        
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        
        //doesn't allow user to continut until they enter the correct userID and password
        while(exit == false)
        {
            //gets userID
            System.out.print("Enter UserID: ");
            userId = br.readLine();
            
            //safeguard incase user can't remember userID or password (prevents infinite loop) (exits app)
            if(userId.equals("Exit") == true)
            {
                exit2 = true;
                break;
            }
            
            //gets password
            System.out.print("Enter Password: ");
            password = br.readLine();
            
            //if userID and password are corrent then exit loop
            if(userId.equals(id) == true && password.equals(passwrd) == true)
            {
                control.SQLConnection(userId, password);            
                break;
            }
            else if(userId.equals(id) != true && password.equals(passwrd) == true)
            {
                System.out.print("Invalid UserID\n");
            }
            else if(userId.equals(id) == true && password.equals(passwrd) != true)
            {
                System.out.print("Invalid password\n");
            }
            else
            {
                System.out.print("Invalid UserID and Password\n");
            }
        }
        
        System.out.print("\n\n");
        
        //keeps the user coming back to the main menu until they are ready to exit the application
        while(exit2 == false)
        {
            //calls main menu function
            control.mainMenu();
            
            //tries to get user input (int only), if non-int then set option to an invalid option
            try
            {
                //gets user input as to what they want to do and converts to an integer
                option = Integer.parseInt(br.readLine());
            }
            catch(NumberFormatException e)
            {
                //sets option to an invalid option
                option = 7;
            }
            
            System.out.print("\n");
            
            //depending on what option user chooses a different case will occur
            switch (option)
            {
                case 1:                
                    System.out.print("You chose to Checkout a Movie\n");
                    Checkout checkout = new Checkout();
                    break;                
                case 2:               
                    System.out.print("You chose to Return a Movie\n");
                    //call return class/function here
                    break;                
                case 3:               
                    System.out.print("You chose to Adjust Balance\n");
                    //call adjust balance class/function here
                    break;                
                case 4:               
                    System.out.print("You chose to Locate a Movie\n");
                    //call locate class/function here
                    break;              
                case 5:               
                    System.out.print("You chose to Update the Inventory\n");
                    //call update class/function here
                    break;               
                case 6:                
                    System.out.print("You chose to view Reports\n");
                    //call reports cass/function here
                    break;                
                case 0:                
                    System.out.print("You chose to exit the program\n");
                    System.out.print("Have a good Day\n");
                    exit2 = true;
                    break;                
                default:
                    System.out.print("You have entered an invalid option\n");
                    break;
            }
            
            System.out.print("\n");
            
        }
    }
}
