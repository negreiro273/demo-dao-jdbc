/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package application;


import java.util.List;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args) {
        // TODO code application logic here
  
        SellerDao sellerDao = DaoFactory.createSellerDao();
        
        System.out.println("==== Test 1 : findById =====");
        
        Seller seller = sellerDao.findById(3);
        System.out.println(seller.toString());
        
        System.out.println("==== Test 2 : findByDepartment =====");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for(Seller obj : list){System.out.println(obj);}
        
        System.out.println("==== Test 3 : findAll =====");        
        List<Seller> list1 = sellerDao.findAll();
        for(Seller obj : list1){System.out.println(obj);}     
        
        
        
    }
    
}
