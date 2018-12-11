package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Question;

public class questionDAO {
	private static ArrayList<Question> c1;
	 private static List list = new ArrayList<String>();

	public static List getId() {
		
		String sql = "select * from question_bank";
//		String sql1 = "select * from option_table";
		try {
			Connection c = DBpool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();// ִ�в�ѯ
			
//			PreparedStatement ps1 = c.prepareStatement(sql1);
//			ResultSet rs1 = ps1.executeQuery();// ִ�в�ѯ

			while (rs.next()) {
				list.add(rs.getInt(1));
				//System.out.println(list.get(0));
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<Question> getQuestion(int a, int b) {
		c1 = new ArrayList<Question>();
		String sql = "select * from question_bank where id=?";
		String sql1 = "select * from option_table where id=?";
		try {
			Connection c = DBpool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			PreparedStatement ps1 = c.prepareStatement(sql1);
			ps.setInt(1, a);
			ps1.setInt(1, a);
			// ps.setString(2, "2");
			ResultSet rs = ps.executeQuery();// ִ�в�ѯ
			ResultSet rs1 = ps1.executeQuery();// ִ�в�ѯ

//            ResultSet rs = ps.getGeneratedKeys();

			while (rs.next() && rs1.next()) {
				Question q = new Question();
				q.setCode(rs.getString(4));
				q.setStem("���ǵ�" + b + "����:"+rs.getString(2));
				q.setContent(rs.getString(2));// �����ݿ���������Ŀ
//            	while (rs1.next()) {
//            		q.addOption(rs1.getString(i));
//            		i++;
//            		System.out.println(q.getOptions());
//            	}
				for (int i = 0; i < rs.getInt(3); i++) {
					q.addOption(rs1.getString(i + 3));
					//System.out.println(q.getOptions());
				}
//    			q.addOption("��");
//    			q.addOption("��");
//    			q.setAnswer("��");//�����ݿ�������Ĵ�
				q.setSeq(rs.getInt(1) + ""); //// �����ݿ�������Ĵ����
				q.setContent(rs.getString(2));
				c1.add(q);
				//System.out.println(q.getSeq());

			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c1;
	}
	
public static  void setAnswer(String a,String b,String c) {
		
		String sql = "insert into answer value (?,?,?)";

		try {
			Connection conn = DBpool.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a);
			ps.setString(2, b);
			ps.setString(3, c);
			ps.executeUpdate();// ִ�в�ѯ

			

			}catch(Exception e) {
				e.printStackTrace();
			}
	
	}

	
	public static void main(String[] args) {
		// conn=DBpool.getConnection();
		questionDAO.getQuestion(60, 1);
		questionDAO.getId();
	}
}
