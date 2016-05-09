//对productBean的处理
package model;
/**
 * @version
 * @author LiuCheng
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductBeanCl {

	private Statement statement = null;
	private ResultSet resultSet = null;
	private Connection connection = null;
	private int pageSize = 5; // 每页显示的音像制品数目
	private int rowCount = 0; // 音像制品的总数 ，该值从数据库得到
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
				resultSet = statement.executeQuery("select count(*) from audiomanagesystem.products");
			}else {
				resultSet = statement.executeQuery("select count(*) from audiomanagesystem.products where "+need+"='"+needValue+"'");
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
	 * 得到用户需要显示的音像制品信息
	 * @param pageNow 显示信息的页码
	 * @param need 查询时需要显示的类型
	 * @param needValue 查询类型的值
	 * @return ArrayList<ProductBean>型
	 */
	public ArrayList<ProductBean> getProductByPage(int pageNow, String need, String needValue) {

		ArrayList<ProductBean> arrayList = new ArrayList<>();

		try {
			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();

			if(need.equals("all")){
				resultSet = statement.executeQuery("select * from products limit " + pageSize * (pageNow - 1) + "," + pageSize + ";");
			}else {
				resultSet = statement.executeQuery("select * from products where "+need+"='"+needValue+"' limit " + pageSize * (pageNow - 1) + "," + pageSize + ";");
			}

			// 将resultment封装到ArrayList中
			while (resultSet.next()) {

				ProductBean productBean = new ProductBean();

				productBean.setpID(resultSet.getInt(1));
				productBean.setpType(resultSet.getString(2));
				productBean.setpName(resultSet.getString(3));
				productBean.setpInventory(resultSet.getInt(4));
				productBean.setpPriceOneDay(resultSet.getInt(5));

				arrayList.add(productBean);
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
	 * 删除音像制品的信息，通过制品id号
	 * @param pId 要删除制品的id号
	 * @return boolean值（true：删除成功；false：删除失败）
	 */
	public boolean deleteById(int pId) {

		boolean b = false;

		try {

			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();

			int i = statement.executeUpdate("delete from products where pId = " + pId + "");
			if (i == 1) {
				// 删除成功
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
	 * 添加音像制品
	 * @param pType 制品的类型
	 * @param pName 制品的名字
	 * @param pInventory 制品的库存
	 * @param pPriceOneDay 制品出租每天的价格
	 * @return boolean值（true：添加成功；false：添加失败）
	 */
	public boolean addProduct(String pType, String pName, String pInventory, String pPriceOneDay) {

		boolean b = false;

		try {

			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			String name = new String(pName.getBytes("ISO-8859-1"),"UTF-8");
			int i = statement.executeUpdate("insert into products (pType, pName, pInventory, pPriceOneDay) value ('"
					+ pType + "', '" + name + "', '" + pInventory + "', '" + pPriceOneDay + "')");
			if (i == 1) {
				// 删除成功
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
	 * 修改音像制品信息
	 * @param pId 制品的id号
	 * @param pType 制品的类型
	 * @param pName 制品的名字
	 * @param pInventory 制品的库存
	 * @param pPriceOneDay 制品出租每天的价格
	 * @return boolean值（true：修改成功；false：修改失败）
	 */
	public boolean updateProduct(String pId, String pType, String pName, String pInventory, String pPriceOneDay) {

		boolean b = false;

		try {

			// 链接数据库
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			String name = new String(pName.getBytes("ISO-8859-1"),"UTF-8");
			int i = statement.executeUpdate("update products set pType='"+pType+"', pName='"+name+"', pInventory='"+pInventory+"', pPriceOneDay='"+pPriceOneDay+"' where pId='"+pId+"' ");
			if (i == 1) {
				// 删除成功
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
	

}
