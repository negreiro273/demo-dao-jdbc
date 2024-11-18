/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package application;

import java.util.Date;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args) {
        // TODO code application logic here
        Department obj = new Department(1, "Bools");
        System.out.println(obj.toString());
        
        
        Seller seller = new Seller(21, "Bob", "BoB@Gmail", new Date(), 3000.00, obj);
        System.out.println(seller.toString());
        
    }
    
}
