package model;
/**
 * @version
 * @author LiuCheng
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginCl {

	private Statement statement = null;
	private ResultSet resultSet = null;
	private Connection connection = null;
	
	/**
	 * 关闭资源
	 */
	public void close() {
		
		try {
			
			if(resultSet != null) {
				
				resultSet.close();
				resultSet = null;
			}
			
			if (statement != null) {
				
				statement.close();
				statement = null;
			}
			
			if (connection != null) {
				
				connection.close();
				connection = null;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 验证管理员用户是否存在
	 * @param name 管理员的用户名
	 * @param password 管理员的密码
	 * @return
	 */
	public boolean checkUser(String name, String password){
		boolean b = false;
		
		try {
			//链接数据库
			connection = new ConnectDB().getConnect();
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select aPassword from administrator where aName = '"+ name +"'");
			
			if (resultSet.next()) {
				
				if (resultSet.getString(1).equals(password)) {
					
					b = true;
					
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
			//关闭资源 
			this.close();
		}
		
		return b;
	}
}
