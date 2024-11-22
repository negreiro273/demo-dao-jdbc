/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package application;


import java.util.Date;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJdbc;
import model.entities.Department;
import model.entities.Seller;
import DB.DBConnection;

public class Program {

    public static void main(String[] args) {
        // TODO code application logic here
  
        
        DepartmentDao departmentDao = new DepartmentDaoJdbc(DBConnection.getConnection());
        List<Department> list;
        
        

        
        /*
        System.out.println("==== Test 3 : Insert Department ====="); 
        Department dp = new Department(null, "SUTINF");
        departmentDao.insert(dp);
        System.out.println(dp.getId());
        */
        
        System.out.println("==== Test 4 : Update Department ====="); 
        //Department dp = departmentDao.findAll(9);
        departmentDao.update(new Department(9, "UNIGEP"));
        
        System.out.println("==== Test 5 : Delete Department ====="); 
        //Department dp = departmentDao.findAll(9);
        departmentDao.deleteById(8);
        
        
        System.out.println("==== Test 1 : findAll =====");  
        System.out.println(departmentDao.findAll());
        
        System.out.println("==== Test 2 : findByDepartment ====="); 
        System.out.println(departmentDao.findById(9));       
        
        
        
        /*        
        SellerDao sellerDao = DaoFactory.createSellerDao();
        
        System.out.println("==== Test 1 : findById =====");
        
        Seller seller = sellerDao.findById(3);
        System.out.println(seller.toString());
        
        System.out.println("==== Test 2 : findByDepartment =====");
        Department department = new Department(4,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for(Seller obj : list){System.out.println(obj);}
        
       
        System.out.println("==== Test 3 : findAll =====");        
        List<Seller> list1 = sellerDao.findAll();
        for(Seller obj : list1){System.out.println(obj);}     
      
       
        System.out.println("==== Test 4 : Insert Seller =====");  
        Seller newSeller = new Seller(null, "laura", "laura@Gmail", new Date(), 8000.00, department);
        sellerDao.insert(newSeller);
        //sellerDao.insert(new Seller(null, "Safirah", "Safirah@Gmail", new Date(), 4000.00, department));
        System.out.println(newSeller.getId()); 
       
        System.out.println("==== Test 5 : Update Seller =====");  
        sellerDao.update(new Seller(12, "Maria Luiza", "LuizeNegreiro@gmail.com", new Date(), 8000.00, department));
        
        System.out.println("==== Test 6 : Delete Seller =====");  
        sellerDao.deleteById(12);
        */
        
        
        
        
    }
    
}
