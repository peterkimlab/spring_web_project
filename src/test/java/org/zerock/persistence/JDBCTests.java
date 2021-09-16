package org.zerock.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;

public class JDBCTests {
 
    static{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void testConnection() {
        try(Connection con= DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:49161:xe",
                "c##book_ex", 
                "book_ex")) {
            
           
        }catch(Exception e){
            
        }
    }
}
