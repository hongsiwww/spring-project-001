package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SpringBootService {
	static int flag = 0;
	
	public static String AccountQueryService(Map<String,String> map) {
		String userId = map.get("userId");
		String pwdId = map.get("pwdId");
		HashMap resultMap = new HashMap();
		String queryId = "";
		
		try {
			resultMap = SpringBootDAO.doQuery(userId,pwdId,"","");
			if(!resultMap.isEmpty()) {
				if((pwdId).equals(resultMap.get("PASSWORD"))) {
					queryId = userId;
				}else {
					queryId = "密碼錯誤";
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return queryId;
	}

	public static int AccountInsertService(Map<String,String> map) {
		String userId = map.get("userId");
		String pwdId = map.get("pwdId");
		String emailId = map.get("emailId");
		String nameId = map.get("nameId");
		/*
		 * String nameId = map.get("nameId"); try { nameId = new
		 * String(nameId.getBytes("UTF_8"),"UTF_8"); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 */
		
		Connection conn = null;
		HashMap resultMap = new HashMap();
		String queryId = "";
		flag = 0;
		
        try{
            
        	resultMap = SpringBootDAO.doQuery(userId,pwdId,emailId,nameId);
        	queryId = (String) resultMap.get("ID");
            
            if("".equals(queryId) || null == queryId) {
            	try {
            		int line = SpringBootDAO.doInsert(userId,pwdId,emailId,nameId);
            		if(line == 0) {
            			System.out.print("帳號新增失敗");
            			flag = 1;
            		}
            	}catch(Exception e) {
                    e.printStackTrace();
                    flag = 1;
            	}
            }else {
            	flag = 2;
            }
            
        }catch(Exception e){
            // 處理 Class.forName 錯誤
            e.printStackTrace();
            flag = 1;
        }finally{}
        
        System.out.println("帳號新增結束");
        
        return flag;
    }
}
