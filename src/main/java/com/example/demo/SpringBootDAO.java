package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class SpringBootDAO {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/new_schema?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";

    static final String USER = "root";
    static final String PASS = "1234";
    
    public static HashMap doQuery(String userId , String pwdId , String emailId , String nameId) throws SQLException {
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        HashMap resultMap = new HashMap<String,String>();
        String resultId = "";
        
        try {
        	// 註冊 JDBC 驅動
            Class.forName(JDBC_DRIVER);
        
            // 打開連結
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 執行查詢
            stmt = conn.createStatement();
            String sql = "SELECT * FROM memberaccount WHERE ID = '" + userId +"'";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
            	resultMap.put("ID", rs.getString("ID"));
            	resultMap.put("PASSWORD", rs.getString("PASSWORD"));
            }
            
            // 完成後關閉
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 處理 JDBC 錯誤
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			// 關閉資源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什麼都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
		}
        
        return resultMap;
     }
    
    public static int doInsert(String userId , String pwdId , String emailId , String nameId) {
    	Connection conn = null;
        Statement stmt = null;
        int line = 0;
        
        try {
        	// 註冊 JDBC 驅動
            Class.forName(JDBC_DRIVER);
        
            // 打開連結
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 執行寫入
            stmt = conn.createStatement();
            String insert_sql = "INSERT INTO memberaccount ( \n"
        			+ "ID,EMAIL,PASSWORD,NAME,CREATE_DATE)   \n"
        			+ "values ('" + userId + "','" + emailId + "','" + pwdId + "','" + nameId+ "',now())";
            line = stmt.executeUpdate(insert_sql);
            
            // 完成後關閉
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 處理 JDBC 錯誤
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			// 關閉資源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什麼都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
		}
        
    	return line;
    }

}
