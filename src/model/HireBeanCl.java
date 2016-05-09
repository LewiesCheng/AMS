//对对hireBean的处理
package model;
/**
 * @version
 * @author LiuCheng
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HireBeanCl {

	private Statement statement = null;
	private ResultSet resultSet = null;
	private Connection connection = null;
	private int pageSize = 5; // 每页显示的租赁数目
	private int rowCount = 0; // 租赁的总数 ，该值从数据库得到
	private int pageCount = 0; // 页数，计算得到
	
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
	 * 返回分页的总页数
	 * @param need 查询时的查询类型
	 * @param needValue 查询类型的值
	 * @return int型
	 */
	public int getPageCount(String need, String needValue) {

		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();

			statement = connection.createStatement();
			
			if(need.equals("all")){
				resultSet = statement.executeQuery("select count(*) from audiomanagesystem.hire");
			}else {
				resultSet = statement.executeQuery("select count(*) from audiomanagesystem.hire where "+need+"='"+needValue+"'");
			}

			
			if (resultSet.next()) {
				rowCount = resultSet.getInt(1);
			}

			// 计算pageCount，页数
			if (rowCount % pageSize == 0) {

				pageCount = rowCount / pageSize;
			} else {

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
	 * 得到用户需要显示的租赁信息
	 * @param pageNow 需要显示信息的页码
	 * @param need 查询时的查询类型
	 * @param needValue 查询类型的值
	 * @return ArrayList<HireBean>型
	 */
	public ArrayList<HireBean> getHireByPage(int pageNow, String need, String needValue) {

		ArrayList<HireBean> arrayList = new ArrayList<>();

		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();

			if(need.equals("all")){
				resultSet = statement.executeQuery("select * from hire limit " + pageSize * (pageNow - 1) + "," + pageSize + ";");
			}else {
				resultSet = statement.executeQuery("select * from hire where "+need+"='"+needValue+"' limit " + pageSize * (pageNow - 1) + "," + pageSize + ";");
			}

			// 将resultment封装到ArrayList中
			while (resultSet.next()) {

				HireBean hireBean = new HireBean();

				hireBean.sethId(resultSet.getInt(1));
				hireBean.sethCustomerId(resultSet.getInt(2));
				hireBean.sethProductId(resultSet.getInt(3));
				hireBean.sethNumber(resultSet.getInt(4));
				hireBean.sethBeginDay(resultSet.getDate(5));
				hireBean.sethReturnDay(resultSet.getDate(6));
				hireBean.sethDeposit(resultSet.getInt(7));
				hireBean.sethTotalPrice(resultSet.getInt(8));
				hireBean.sethWhetherReturn(resultSet.getString(9));
				
				arrayList.add(hireBean);
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
	 * 根据客户id查询他的未归还的租赁记录
	 * @param pageNow 未归还信息的显示页码
	 * @param hCustomerId 查询的客户id号
	 * @return ArrayList<HireBean>型
	 */
	public ArrayList<HireBean> getNoReturnHireByPage(int pageNow, String hCustomerId) {
		
		ArrayList<HireBean> arrayList = new ArrayList<>();

		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();

			resultSet = statement.executeQuery("select * from hire where hCustomerId='"+hCustomerId+"' and hWhetherReturn='no' limit " + pageSize * (pageNow - 1) + "," + pageSize + ";");

			// 将resultment封装到ArrayList中
			while (resultSet.next()) {

				HireBean hireBean = new HireBean();

				hireBean.sethId(resultSet.getInt(1));
				hireBean.sethCustomerId(resultSet.getInt(2));
				hireBean.sethProductId(resultSet.getInt(3));
				hireBean.sethNumber(resultSet.getInt(4));
				hireBean.sethBeginDay(resultSet.getDate(5));
				hireBean.sethReturnDay(resultSet.getDate(6));
				hireBean.sethDeposit(resultSet.getInt(7));
				hireBean.sethTotalPrice(resultSet.getInt(8));
				hireBean.sethWhetherReturn(resultSet.getString(9));
				
				arrayList.add(hireBean);
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
	 * 添加借出信息
	 * @param hCustomerId 添加信息的客户id
	 * @param hProductId 添加信息的制品id
	 * @param hNumber 出租的数量
	 * @param hBeginDay 出租日期
	 * @param hDeposit 押金
	 * @return boolean值（true：添加成功；false：添加失败）
	 */
	public boolean addHire(String hCustomerId, String hProductId, String hNumber, String hBeginDay, String hDeposit) {

		boolean b = false;

		try {

			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();

			resultSet = statement.executeQuery("select pInventory from products where pId ='"+hProductId+"' ");
			
			int i = 0, j = 0;
			int Num = Integer.parseInt(hNumber);
			if(resultSet.next()){
				if(resultSet.getInt(1) >= Num){
					int num = resultSet.getInt(1)-Num;
					i = statement.executeUpdate("insert into hire (hCustomerId, hProductId, hNumber, hBeginDay, hDeposit, hWhetherReturn) value ('"
							+ hCustomerId +"', '"+ hProductId +"', '"+ Num +"', '"+ hBeginDay +"', '"+ hDeposit +"', 'no')");
					j = statement.executeUpdate("update products set pInventory='"+num+"' where pId ='"+hProductId+"'");
				}
			}
			
			if (i == 1 && j == 1) {
				// 添加成功
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			// 关闭资源
			this.close();
		}

		return b;
	}
	

	/**
	 * 归还租赁信息
	 * @param hId 归还制品的租赁id
	 * @param hReturnDay 归还日期
	 * @param hTotalPrice 总价格
	 * @return boolean值（true：归还成功；false：归还失败）
	 */
	public boolean hireReturn(String hId, String hReturnDay, String hTotalPrice) {

		boolean b = false;
		int i = 0, j = 0;
		try {

			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select hProductId,hNumber from hire where hId='"+hId+"'");
			if(resultSet.next()){
				i = statement.executeUpdate("update products set pInventory=pInventory+"+resultSet.getInt(2)+" where pId='"+resultSet.getInt(1)+"' ");
			}
			
			j = statement.executeUpdate("update hire set hReturnDay='"+hReturnDay+"', hDeposit='0', hTotalPrice='"+hTotalPrice+"', hWhetherReturn='yes' where hId='"+hId+"' ");
			if (i == 1 && j == 1) {
				// 归还成功
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			// 关闭资源
			this.close();
		}

		return b;
	}
		

	/**
	 * 验证客户是否存在
	 * @param cName 验证客户的用户名
	 * @param cPassword 验证客户的密码
	 * @return boolean值（true：验证成功；false：验证失败）
	 */
	public boolean checkCustomer(String cName, String cPassword){
		boolean b = false;
		
		try {
			
			connection = new ConnectDB().getConnect();
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select cPassword from customers where cName = '"+ cName +"'");
			
			if (resultSet.next()) {
				
				if (resultSet.getString(1).equals(cPassword)) {
					
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
	 * 根据客户名取出客户的id号
	 * @param cName 客户的用户名
	 * @return int（客户id号）
	 */
	public int getCId (String cName) {
		
		int cId = 0;
		try {
			
			connection = new ConnectDB().getConnect();
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select cId from customers where cName = '"+ cName +"'");
			
			if (resultSet.next()) {
				
				cId = resultSet.getInt(1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			
			//关闭资源 
			this.close();
		}
		
		return cId;
	}
	
	
	/**
	 * 归还时根据cId查询未归还的记录
	 * @param cId 客户id号
	 * @return int（该客户未归还的记录个数）
	 */
	public int getPageCountOfNoReturnByCId(String cId) {
		
		int pageCount = 0;
		
		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();

			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select count(*) from audiomanagesystem.hire where hCustomerId='"+cId+"' and hWhetherReturn='no'");

			if (resultSet.next()) {
				rowCount = resultSet.getInt(1);
			}

			// 计算pageCount，页数
			if (rowCount % pageSize == 0) {

				pageCount = rowCount / pageSize;
			} else {

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
	 * 归还时根据hId和归还日期计算应收金额
	 * @param hId 租赁id
	 * @param hReturnDay 归还日期
	 * @return int（应收金额）
	 */
	public int getReturnMoney(String hId, String hReturnDay) {
		
		int returnMoney = 0;
		int hProductId = 0;
		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();

			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select hProductId,datediff('"+hReturnDay+"',hBeginDay),hNumber from hire where hId='"+hId+"'");
			if (resultSet.next()) {
				hProductId = resultSet.getInt(1);
				returnMoney = resultSet.getInt(2) * resultSet.getInt(3);
			}
			
			resultSet = statement.executeQuery("select pPriceOneDay from products where pId='"+hProductId+"'");
			if (resultSet.next()) {
				returnMoney = returnMoney * resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			this.close();
		}
		
		return returnMoney;
	}
	

	/**
	 * 归还时根据hId返回押金金额
	 * @param hId 租赁id
	 * @return int（返回的押金金额）
	 */
	public int getHireDepositByhId(String hId) {
		int hDeposit = 0;
		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();

			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select hDeposit from hire where hId='"+hId+"'");
			if (resultSet.next()) {
				hDeposit = resultSet.getInt(1);
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			this.close();
		}
		
		return hDeposit;
	}
	
	/**
	 * 获得当前日期 *形如2016-01-01
	 */
	public String  getTodayDate() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = simpleDateFormat.format(date).toString();
		return todayDate;
	}
}
