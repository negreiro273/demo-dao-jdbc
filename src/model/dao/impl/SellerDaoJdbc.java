/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import DB.DBConnection;
import DB.DbException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


public class SellerDaoJdbc implements SellerDao{
    
    private Connection conn;
    
    public SellerDaoJdbc(Connection conn){ this.conn = conn;}    
    
    @Override
    public void insert(Seller obj) {
        
        PreparedStatement st = null;
        ResultSet         rs = null;
        
        try {
            
            st = conn.prepareStatement("INSERT INTO seller (name,email,birthdate,basesalary,departmentid)" +
                                       "VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()) );
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            
            boolean rowsAffect = st.execute();
            if(rowsAffect == false){
                rs = st.getGeneratedKeys();
                 if(rs.next()){
                  //Esse Codigo pega o reigstro da primeira posição do 
                  // st.getGeneratedKeys();
                   obj.setId(rs.getInt(1));                 
                 }
            }
            
        } catch (SQLException e) {
            
            throw new DbException("Error..! Unable to enter Data.."+e.getMessage());
            
        } finally {            
            DBConnection.closeStatement(st);
            DBConnection.closeResultSet(rs);            
        }
    }

    @Override
    public void update(Seller obj) {
       
        PreparedStatement st = null;
        
        try {
            
            st = conn.prepareStatement("Update seller set name = ?,email = ?,birthdate = ?,basesalary = ?, departmentid = ? Where id = ?");
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime())); 
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5,obj.getDepartment().getId());
            st.setInt(6, obj.getId());            
            st.execute();                       
            
        } catch (SQLException e) {
            throw new DbException("Error...! Ocorreu um ERROR ao Atulaizar os Dados.." + e.getMessage());
            
        } finally { DBConnection.closeStatement(st); }
        
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        
        try {
            
            st = conn.prepareStatement("Delete from seller Where id = ?");
            st.setInt(1, id);
            st.execute();            
            
        } catch (SQLException e) {
            
            throw new DbException("ERROR..!! Não foi possivel excluir os dados..."+ e.getMessage());
            
        } finally {
            DBConnection.closeStatement(st);
        }
        
        
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
          PreparedStatement st = null;
          ResultSet         rs = null;
        
        try {
              
              st = conn.prepareStatement("Select Seller.*, department.name as DepName "+
                                          "from seller "+
                                          "Inner Join department on seller.departmentId = department.id "+
                                           "Order by seller.name");
        
              rs = st.executeQuery();
              
              List<Seller> list = new ArrayList<>();
              Map<Integer, Department> map = new HashMap<>();
              
              while(rs.next()){
               
               // Here you can check if the Method already exists using the Map
               Department dp  = map.get(rs.getInt("departmentId")); 
               // If it doesn't exist, I instantiate my Method
               if(dp == null){
                    dp = instantiateDepartment(rs);
                    map.put(rs.getInt("departmentId"), dp);
               }
               
               Seller obj    = instantiateSeller(rs,dp);               
               list.add(obj);  
               
              }
              
             return list;
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DBConnection.closeStatement(st);
            DBConnection.closeResultSet(rs);
        }
        
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

    @Override
    public List<Seller> findByDepartment(Department department) {
      
        PreparedStatement st = null;
        ResultSet         rs = null;
        
        try {
              
              st = conn.prepareStatement("Select Seller.*, department.name as DepName "+
                                          "from seller "+
                                          "Inner Join department on seller.departmentId = department.id "+
                                          "Where seller.departmentId =?"+
                                          "Order by seller.name");
              st.setInt(1, department.getId());
              rs = st.executeQuery();
              
              List<Seller> list = new ArrayList<>();
              Map<Integer, Department> map = new HashMap<>();
              
              while(rs.next()){
               
               // Here you can check if the Method already exists using the Map
               Department dp  = map.get(rs.getInt("departmentId")); 
               // If it doesn't exist, I instantiate my Method
               if(dp == null){
                    dp = instantiateDepartment(rs);
                    map.put(rs.getInt("departmentId"), dp);
               }
               
               Seller obj    = instantiateSeller(rs,dp);               
               list.add(obj);  
               
              }
              
             return list;
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DBConnection.closeStatement(st);
            DBConnection.closeResultSet(rs);
        }
        
        
        
    }
    
}
