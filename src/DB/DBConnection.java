/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    
     // Configurações do banco de dados
    //private static final String URL = "jdbc:postgresql://172.16.13.204:5432/Desenvolvimento";
    //private static final String USUARIO = "postgres";
   // private static final String SENHA = "S1GP4R_admin";
    
    private static Connection conn = null; 
    
    public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url       = props.getProperty("dburl");                                      
				conn = DriverManager.getConnection(url,props);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
    
    public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
    
    private static Properties loadProperties() {
        
         // O arquivo "db.properties" fica nesse caminho
         // C:\Users\90913370100\Documents\NetBeansProjects\Treinamento
         
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
    
    
    public static void closeStatement(Statement st){
      
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) { throw new DbException(e.getMessage());
            }
        }
     
    }
    public static void closeResultSet(ResultSet rs){
      
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) { throw new DbException(e.getMessage());
            }
        }
     
    }
}
