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
	 * �ر���Դ
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
	 * ��֤����Ա�û��Ƿ����
	 * @param name ����Ա���û���
	 * @param password ����Ա������
	 * @return
	 */
	public boolean checkUser(String name, String password){
		boolean b = false;
		
		try {
			//�������ݿ�
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
			
			//�ر���Դ 
			this.close();
		}
		
		return b;
	}
}
