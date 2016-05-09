package model;
/**
 * @version 
 * @author LiuCheng
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatisticsCl {
	private Statement statement = null;
	private ResultSet resultSet = null;
	private Connection connection = null;
	
	/**
	 * 关闭资源
	 */
	public void close() {

		try {

			if (resultSet != null) {

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
	 * 返回客户的总数目
	 * @return int
	 */
	public int getCustomerNumber() {
		int customerNumber = 0;
		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select count(*) from customers;");
			if (resultSet.next()) {
				customerNumber = resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			this.close();
		}
		return customerNumber;
	}
	

	/**
	 * 返回总租出音频的数目
	 * @return int
	 */
	public int getAudioNumber() {
		int audioNumber = 0;
		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select sum(hNumber) from hire;");
			if (resultSet.next()) {
				audioNumber = resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			this.close();
		}
		return audioNumber;
	}
	

	/**
	 * 返回总收益
	 * @return int
	 */
	public int getTotalProfits() {
		int totalProfits = 0;
		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select sum(hTotalPrice) from hire;");
			if (resultSet.next()) {
				totalProfits = resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			this.close();
		}
		return totalProfits;
	}
	
	
	/**
	 * 返回还剩余的押金
	 * @return int
	 */
	public int getRemainDeposit() {
		int remainDeposit = 0;
		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select sum(hDeposit) from hire;");
			if (resultSet.next()) {
				remainDeposit = resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			this.close();
		}
		return remainDeposit;
	}
	
	
	/**
	 * 返回今天共收回音频的个数
	 * @return int
	 */
	public int getTodayReturn() {
		int todayReturn = 0;
		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select sum(hNumber) from hire where hReturnDay=curdate();");
			if (resultSet.next()) {
				todayReturn = resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			this.close();
		}
		return todayReturn;
	}
	
	
	/**
	 * 返回今天共租出音频的个数
	 * @return int
	 */
	public int getTodayHire() {
		int todayHire = 0;
		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select sum(hNumber) from hire where hBeginDay=curdate();");
			if (resultSet.next()) {
				todayHire = resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			this.close();
		}
		return todayHire;
	}


	/**
	 * 返回今天的收益
	 * @return int
	 */
	public int getTodayProfits() {
		int todayProfits = 0;
		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select sum(hTotalPrice) from hire where hReturnDay=curdate();");
			if (resultSet.next()) {
				todayProfits = resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			this.close();
		}
		return todayProfits;
	}
}
