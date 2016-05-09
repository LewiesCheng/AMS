
// 对客户表的操作
package model;

/**
 * 
 * @version 
 * @author LiuCheng
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class CustomerBeanCl {

	private Statement statement = null;
	private ResultSet resultSet = null;
	private Connection connection = null;
	private int pageSize = 5;   //每页显示的客户数目
	private int rowCount = 0;   //客户的总数 ，该值从数据库得到
	private int pageCount = 0;  //页数，计算得到
	
	
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
	 * 检查客户是否存在
	 * @param name 客户的用户名
	 * @param password 客户的密码
	 * @return 一个boolean值（true：客户存在；false：客户不存在）
	 */
	public boolean checkCustomer(String name, String password) {
		/**
		 * 
		 */
		boolean b = false;
		
		try {
			//链接数据库
			connection = new ConnectDB().getConnect();
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select cPassword from customers where cName = '"+ name +"'");
			
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
	
	
	/**
	 * 计算分页的总页数
	 * @return 一个int值
	 */
	public int getPageCount() {
		
		try {
			//链接数据库
			connection = new ConnectDB().getConnect();
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select count(*) from audiomanagesystem.customers");
			
			if(resultSet.next()){
				rowCount = resultSet.getInt(1);
			}
			
			//计算pageCount，页数
			if (rowCount % pageSize == 0) {
				
				pageCount = rowCount / pageSize;
			} else{
				
				pageCount = rowCount / pageSize + 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
			this.close();
		}
		
		return pageCount;
	}
	
	
	/**
	 * 得到用户需要显示的客户信息
	 * @param pageNow 要显示的页码
	 * @param need 查找时的类型
	 * @param needValue 查找时类型的值
	 * @return ArrayList<CustomerBean>类型
	 */
	public ArrayList<CustomerBean> getCustomerByPage( int pageNow, String need, String needValue) {

		ArrayList<CustomerBean> arrayList = new ArrayList<>();
		
		try {
			//链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
												
			if(need.equals("all")){
				resultSet = statement.executeQuery("select * from customers limit " + pageSize*(pageNow-1) + "," + pageSize + ";");
			} else if (need.equals("cId")) {
				resultSet = statement.executeQuery("select * from customers where cId='"+needValue+"'");
			} else if (need.equals("cName")) {
				resultSet = statement.executeQuery("select * from customers where cName='"+needValue+"'");
			}
	
			//将resultment封装到ArrayList中
			while (resultSet.next()) {
				
				
				CustomerBean customerBean = new CustomerBean();
				customerBean.setcId(resultSet.getInt(1));
				customerBean.setcName(resultSet.getString(2));
				customerBean.setcPassword(resultSet.getString(3));
				customerBean.setcContactWay(resultSet.getString(4));
			
				arrayList.add(customerBean);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
			this.close();
		}
		
		return arrayList;
	}
	

	/**
	 * 删除客户
	 * @param cId 需要删除的客户的id号
	 * @return boolean值（true：删除成功；false：删除失败）
	 */
	public boolean deleteById (int cId) {
		
		boolean b = false;
		
		try {
			
			//链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			int i = statement.executeUpdate("delete from customers where cId = " + cId + "");
			if(i == 1) {
				//删除成功
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			//关闭资源
			this.close();
		}
		
		return b;
	}
	

	/**
	 * 添加客户
	 * @param cName 添加客户的用户名
	 * @param cPassword 添加客户的密码
	 * @param cContactWay 添加客户的联系方式
	 * @return boolean值（true：添加成功；false：添加失败）
	 */
	public boolean addCustomer(String cName, String cPassword, String cContactWay) {
		
		boolean b = false;
		
		try {
			
			//链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			int i = statement.executeUpdate("insert into customers (cName, cPassword, cContactWay) value ('"+cName+"', '"+cPassword+"', '"+cContactWay+"')");
			if(i == 1) {
				//添加成功
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			//关闭资源
			this.close();
		}
		
		return b;
	}
	

	/**
	 * 修改客户信息
	 * @param cId 需要修改的客户的id号
	 * @param cName 需要修改的客户的用户名
	 * @param cPassword 需要修改的客户的密码
	 * @param cContactWay 需要修改的客户的联系方式
	 * @return boolean值（true：修改成功；false：修改失败）
	 */
	public boolean updateCustomer(String cId, String cName, String cPassword, String cContactWay) {
		
		boolean b = false;
		
		try {
			
			//链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			int i = statement.executeUpdate("update customers set cName='"+cName+"', cPassword='"+cPassword+"', cContactWay='"+cContactWay+"' where cId='"+cId+"'");
			if(i == 1) {
				//添加成功
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			//关闭资源
			this.close();
		}
		
		return b;
	}
}
