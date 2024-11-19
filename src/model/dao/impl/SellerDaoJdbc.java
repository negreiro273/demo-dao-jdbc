/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import DB.DBConnection;
import DB.DbException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


public class SellerDaoJdbc implements SellerDao{
    
    private Connection conn;
    
    public SellerDaoJdbc(Connection conn){ this.conn = conn;}    
    
    @Override
    public void insert(Seller obj) {
       
    }

    @Override
    public void update(Seller obj) {
       
    }

    @Override
    public void deleteById(Integer id) {
       
    }

    @Override
    public Seller findById(Integer id) {
        
        //conn                   = DBConnection.getConnection();
        PreparedStatement st   = null;
        ResultSet         rs   = null;
                
        try {
            
            st = conn.prepareStatement("Select seller.*,department.name as DepName "+
                                       "from seller "+
                                       "Inner Join department on seller.departmentId = department.id "+
                                       "Where seller.id =?");
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if(rs.next()){
                
               Department dp = instantiateDepartment(rs);               
               Seller obj    = instantiateSeller(rs,dp);               
               return obj;
            }
            
            return null;    
            
        } catch (SQLException e) {
             throw new DbException(e.getMessage());
        }finally{
            
             DBConnection.closeStatement(st);
             DBConnection.closeResultSet(rs);             
        
        }
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

                                                           /*Propagar Sessões*/
    private Department instantiateDepartment(ResultSet rs) throws SQLException {
     
        Department dp =   new Department();
        dp.setId(rs.getInt("departmentId"));
        dp.setName(rs.getString("DepName"));
        return dp;     
     
    }
                                                                   /*Propagar Sessões*/
    private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException {
        
       Seller obj = new Seller();
       obj.setId(rs.getInt("Id"));
       obj.setName(rs.getString("Name"));
       obj.setEmail(rs.getString("Email"));
       obj.setBaseSalary(rs.getDouble("BaseSalary"));
       obj.setBirthDate(rs.getDate("BirthDate"));
       obj.setDepartment(dp);        
       
       return obj;
        
        
    }
    
}
