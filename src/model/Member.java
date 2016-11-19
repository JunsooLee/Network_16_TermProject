package model;

public class Member {
	private int Grade;
	private String ID;
	private String password;
	private int num_win;
	private int point;
	public int getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		Grade = grade;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNum_win() {
		return num_win;
	}
	public void setNum_win(int num_win) {
		this.num_win = num_win;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}
