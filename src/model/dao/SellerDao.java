/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.util.List;
import model.entities.Department;
import model.entities.Seller;

/**
 *
 * @author 90913370100
 */
public interface SellerDao {
   
   void insert(Seller obj);
   void update(Seller obj);
   void deleteById(Integer id);
   Seller findById(Integer id);
   List<Seller> findAll(); 
   List<Seller> findByDepartment(Department department);
   
}
