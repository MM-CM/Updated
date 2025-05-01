/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseCon {
    public static Connection connectDB(){
        Connection database = null;
        
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
//        database = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "panget232005121966");
            database = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "panget232005121966");
            System.out.println("Successfully Connected!");
            
            }catch(ClassNotFoundException a){
            System.out.println(a);
    }catch(SQLException A){
            System.out.println(A);
  
       
    }
        return database;
    }
}
   
