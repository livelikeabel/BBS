package user;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn;//������ ���̽��� �����ϰ� ���ִ� ��ü�̴�.
	private PreparedStatement pstmt;
	private ResultSet rs; //��� ������ ���� �� �ִ� �ϳ��� ��ü
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";//mysql server
			String dbID = "root"; //root������ ������ �� �ְ� ���ش�.
			String dbPassword = "1111";
			Class.forName("com.mysql.jdbc.Driver");//my sql�� ������ �� �ֵ��� �Ű�ü ������ �ϴ� ���̺귯��
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//�α����� �õ��ϴ� �Լ�
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";//������ DB�� ����� ����
		try {
			pstmt = conn.prepareStatement(SQL);//������ SQL ������ ������ ���̽��� �����ϴ� �������� �ν��Ͻ��� �����´�.
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //�α��� ����
				}
				else
					return 0; // ��й�ȣ ����ġ
			}
			return -1; //���̵� ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //������ ���̽� ����
	}
}
