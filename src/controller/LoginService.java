package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

import asset.DBConnectionMgr;
import model.Member;
import thread.CustomThread;

public class LoginService {
	final static DBConnectionMgr pool = DBConnectionMgr.getInstance();

	static boolean flag = false;
	static Connection con = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	static String sql = null;
	static String getPass = null;

	static int character;
	static int grade;
	static int win;
	static int point;
	int timeCount;

	public static void main(String[] args) {

	}

	public static boolean loginTest(String id, String password, Member info) {
		Thread t;
		boolean check = false;

		t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					con = pool.getConnection();
					sql = "select grade ,password, win, point, charater from user where binary(id)=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
					info.setID(id);

					if (rs.next()) {
						grade = rs.getInt("grade");
						getPass = rs.getString("password");
						win = rs.getInt("win");
						point = rs.getInt("point");
						character = rs.getInt("charater");

						info.setGrade(grade);
						info.setPassword(getPass);
						info.setNum_win(win);
						info.setPoint(point);
						info.setCharacter(character);

						if (getPass.equals(password)) {
							flag = true;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					pool.freeConnection(con, pstmt, rs);
				}
			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	public static boolean Joining(String id, String password, int ck) {
		boolean flag = false;

		DBConnectionMgr pool = DBConnectionMgr.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet Rs = null;
		String sql = null;
		int rs = 0;

		try {
			con = pool.getConnection();
			sql = "select password from user where binary(id)=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			Rs = pstmt.executeQuery();
			if (Rs.next()) {
				rs = 0;
			} else {
				sql = "insert into user(" + "id,grade,password,win,point,charater)" + "values(?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setInt(2, 1);
				pstmt.setString(3, password);
				pstmt.setInt(4, 0);
				pstmt.setInt(5, 0);
				pstmt.setInt(6, ck);

				rs = pstmt.executeUpdate();
				flag = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}