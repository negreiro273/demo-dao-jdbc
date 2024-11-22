/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import DB.DBConnection;
import DB.DbException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class DepartmentDaoJdbc implements DepartmentDao{

    private Connection conn;
    
    public DepartmentDaoJdbc(Connection conn){this.conn = conn; };
    
    
    @Override
    public void insert(Department obj) {     
        PreparedStatement st = null;
        ResultSet         rs = null;    
        try {            
            st = conn.prepareStatement("Insert into department(name)Values(?)",Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());            
            boolean rowsResult = st.execute();            
            if(rowsResult == false){                
                rs = st.getGeneratedKeys();
                rs.next();
                obj.setId(rs.getInt(1));                
            }
            
        } catch (SQLException e) {           
            throw new DbException("Error...!!! "+e.getMessage());            
        } finally {            
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);                    
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;       
        try {            
            st = conn.prepareStatement("update department set name = ? Where id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.execute();            
        } catch (SQLException e) {            
            throw new DbException("Error...!! "+e.getMessage());
        } finally {
            DBConnection.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("Delete from department where id =?");
            st.setInt(1, id);
            st.execute();
            
        } catch (SQLException e) {            
            throw new DbException("Error..!! "+e.getMessage());
        } finally {
            DBConnection.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
     
        PreparedStatement st = null;
        ResultSet         rs = null;
        
        try {
          
            st = conn.prepareStatement("Select a.id, a.Name from department a Where a.id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if(rs.next()){
                
             Department  obj = new Department();
             obj.setId(rs.getInt("id"));
             obj.setName(rs.getString("Name"));
             return obj;
            }
            
            return null;
            
            
        } catch (SQLException e) {
            
            throw  new DbException("Error...!! ao Filtar os Dados.."+e.getMessage());
            
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(st);
            
        }
     
    }

    @Override
    public List<Department> findAll() {
       
        List<Department> list = new ArrayList();
        PreparedStatement st = null;
        ResultSet         rs = null;
        
        
        try {
            
            st = conn.prepareStatement("Select a.id, a.Name from department a");
            rs = st.executeQuery();
            
            while(rs.next())
            {
             Department obj = new Department();
             obj.setId(rs.getInt("id"));
             obj.setName(rs.getString("name"));             
             list.add(obj);            
            }
            
            return list;
            
        } catch (SQLException e) {
           
            throw new DbException("Error..!! ao consultar os Dados"+e.getMessage());
            
            
        } finally {
            
            DBConnection.closeStatement(st);
            DBConnection.closeResultSet(rs);
            
        }
    }
    
    
}
