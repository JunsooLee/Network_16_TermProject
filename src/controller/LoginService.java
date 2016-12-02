package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import asset.DBConnectionMgr;

public class LoginService {
	 public static void main(String[] args) {
		 
	    }
	 
	    public static boolean loginTest(String id, String password) {
	        boolean flag = false;
	 
	        DBConnectionMgr pool = DBConnectionMgr.getInstance();
	 
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        String sql = null;
	        String getPass = null;
	 
	        try {
	            con = pool.getConnection();
	            sql = "select password from user where binary(id)=?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, id);
	            rs = pstmt.executeQuery();
	 
	            if (rs.next()) {
	                getPass = rs.getString("password");
	                if (getPass.equals(password)) {
	                    flag = true;
	                }
	            }
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	 
	        } finally {
	            pool.freeConnection(con, pstmt, rs);
	        }
	        return flag;
	    }
	    public static boolean Joining(String id, String password){
	    	   boolean flag = false;
	    		 
		       DBConnectionMgr pool = DBConnectionMgr.getInstance();
		 
		       Connection con = null;
		       PreparedStatement pstmt = null;
		       int rs;
		       String sql = null;
		       
		       try {
		            con = pool.getConnection();
		            sql = "insert into user("+"id,grade,password,win,point)"+"values(?,?,?,?,?)";
		            pstmt = con.prepareStatement(sql);
		            
		            pstmt.setString(1, id);
		            pstmt.setInt(2, 1);
		            pstmt.setString(3, password);
		            pstmt.setInt(4, 0);
		            pstmt.setInt(5, 0);
		            
		            rs = pstmt.executeUpdate();
		 
		            if(rs > 0) {
		                System.out.println("Success");
		                flag=true;
		            }else{
		            	System.out.println("Fail");
		            }
		 
		        } catch (Exception e) {
		            e.printStackTrace();
		 
		        }
		        return flag;
	    }
}
