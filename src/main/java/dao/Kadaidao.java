package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Kadaidto;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class Kadaidao {
	
	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	public static int regisiterAccount(Kadaidto customer) {
		String sql = "INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		
		String salt = GenerateSalt.getSalt(32);
		
		String Hashedpw  = GenerateHashedPw.getSafetyPassword(customer.getPassword(), salt);
		
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1,customer.getName());
			pstmt.setInt(2,customer.getOld());
			pstmt.setString(3,customer.getGender());
			pstmt.setInt(4,customer.getTel());
			pstmt.setString(5,customer.getMail());
			pstmt.setString(6,Hashedpw);
			pstmt.setString(7,salt);
			
			result = pstmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		}catch(URISyntaxException e) {
			e.printStackTrace();
		}finally {
			System.out.println(result+"件更新しました。");
		}
		return result;	
		
	}
	public static String getSalt(String mail) {
		String sql = "SELECT salt FROM customer WHERE mail = ?";
		
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);
			
			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					String salt = rs.getString("salt");
					return salt;
					
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Kadaidto login(String mail,String Hashedpw) {
		String sql = "SELECT * FROM customer WHERE mail = ? AND password = ?";
		
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);
			pstmt.setString(2, Hashedpw);
			
			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					String name = rs.getString("name");
					int old = rs.getInt("old");
					String gender = rs.getString("gender");
					int tel = rs.getInt("tel");
					String salt = rs.getString("salt");
					
					return new Kadaidto(name,old,gender,tel,mail,null,null,salt);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
