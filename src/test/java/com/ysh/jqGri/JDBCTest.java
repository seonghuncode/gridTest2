//package com.ysh.jqGri;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//import org.junit.Test;
//
//public class JDBCTest {
//
//	private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; 
//	private static final String URL = "jdbc:mysql://127.0.0.1:3306/jq_grid_test";
//
//	private static final String USER = "ysh";
//	private static final String PW = "ysh123";
//
//	@Test
//	public void testConnection() throws Exception{
//		Class.forName(DRIVER);
//		
//		try(Connection conn = DriverManager.getConnection(URL, USER, PW)){
//			
//			System.out.println("conn : " + conn); // 콘솔창에서 연결정보를 출력하여 확인한다.
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("e : " + e);
//		}
//	}
//
//}
