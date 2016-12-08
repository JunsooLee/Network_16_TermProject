package thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import asset.DBConnectionMgr;
import model.Member;

public class CustomThread extends Thread{
	int timeCount;
	boolean flag = false;
	
	DBConnectionMgr pool = DBConnectionMgr.getInstance();
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	String getPass = null;
	String ID;
	String Password;
	
	int character;
	int grade;
	int win;
	int point;
	
	public CustomThread(String id, String password, Member info) {
		// TODO Auto-generated constructor stub
		Member member = new Member();
		ID = id;
		Password = password;
	}
	
	public void run(Member info){
		try {
	         con = pool.getConnection();
	         sql = "select grade ,password, win, point, charater from user where binary(id)=?";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, ID);
	         rs = pstmt.executeQuery();
	         System.out.println(ID);
	         info.setID(ID);
	 
	         if (rs.next()) {
	        	 grade=rs.getInt("grade");
	        	 getPass = rs.getString("password");
	             win=rs.getInt("win");
	             point=rs.getInt("point");
	             character=rs.getInt("charater");
	                
	             info.setGrade(grade);
	             info.setPassword(getPass);
	             info.setNum_win(win);
	             info.setPoint(point);
	             info.setCharacter(character);
	                
	             if (getPass.equals(Password)) {
	                 flag = true;
	                 }
	             }
	         } catch (Exception e) {
	        	 e.printStackTrace();	 
	        } finally {
	        	pool.freeConnection(con, pstmt, rs);
	        }
	}
	public boolean getResult(){
		return flag;
	}
}
