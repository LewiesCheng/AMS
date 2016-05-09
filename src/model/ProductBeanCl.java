//��productBean�Ĵ���
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
	private int pageSize = 5; // ÿҳ��ʾ��������Ʒ��Ŀ
	private int rowCount = 0; // ������Ʒ������ ����ֵ�����ݿ�õ�
	private int pageCount = 0; // ҳ��������õ�

	/**
	 * �ر���Դ
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
	 * ���ط�ҳ����ҳ��
	 * @param need ��ѯʱ�Ĳ�ѯ����
	 * @param needValue ��ѯ���͵�ֵ
	 * @return int��
	 */
	public int getPageCount(String need, String needValue) {

		try {
			// �������ݿ�
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

			// ����pageCount��ҳ��
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
	 * �õ��û���Ҫ��ʾ��������Ʒ��Ϣ
	 * @param pageNow ��ʾ��Ϣ��ҳ��
	 * @param need ��ѯʱ��Ҫ��ʾ������
	 * @param needValue ��ѯ���͵�ֵ
	 * @return ArrayList<ProductBean>��
	 */
	public ArrayList<ProductBean> getProductByPage(int pageNow, String need, String needValue) {

		ArrayList<ProductBean> arrayList = new ArrayList<>();

		try {
			// �������ݿ�
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();

			if(need.equals("all")){
				resultSet = statement.executeQuery("select * from products limit " + pageSize * (pageNow - 1) + "," + pageSize + ";");
			}else {
				resultSet = statement.executeQuery("select * from products where "+need+"='"+needValue+"' limit " + pageSize * (pageNow - 1) + "," + pageSize + ";");
			}

			// ��resultment��װ��ArrayList��
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
	 * ɾ��������Ʒ����Ϣ��ͨ����Ʒid��
	 * @param pId Ҫɾ����Ʒ��id��
	 * @return booleanֵ��true��ɾ���ɹ���false��ɾ��ʧ�ܣ�
	 */
	public boolean deleteById(int pId) {

		boolean b = false;

		try {

			// �������ݿ�
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();

			int i = statement.executeUpdate("delete from products where pId = " + pId + "");
			if (i == 1) {
				// ɾ���ɹ�
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			// �ر���Դ
			this.close();
		}

		return b;
	}


	/**
	 * ���������Ʒ
	 * @param pType ��Ʒ������
	 * @param pName ��Ʒ������
	 * @param pInventory ��Ʒ�Ŀ��
	 * @param pPriceOneDay ��Ʒ����ÿ��ļ۸�
	 * @return booleanֵ��true����ӳɹ���false�����ʧ�ܣ�
	 */
	public boolean addProduct(String pType, String pName, String pInventory, String pPriceOneDay) {

		boolean b = false;

		try {

			// �������ݿ�
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			String name = new String(pName.getBytes("ISO-8859-1"),"UTF-8");
			int i = statement.executeUpdate("insert into products (pType, pName, pInventory, pPriceOneDay) value ('"
					+ pType + "', '" + name + "', '" + pInventory + "', '" + pPriceOneDay + "')");
			if (i == 1) {
				// ɾ���ɹ�
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			// �ر���Դ
			this.close();
		}

		return b;
	}


	/**
	 * �޸�������Ʒ��Ϣ
	 * @param pId ��Ʒ��id��
	 * @param pType ��Ʒ������
	 * @param pName ��Ʒ������
	 * @param pInventory ��Ʒ�Ŀ��
	 * @param pPriceOneDay ��Ʒ����ÿ��ļ۸�
	 * @return booleanֵ��true���޸ĳɹ���false���޸�ʧ�ܣ�
	 */
	public boolean updateProduct(String pId, String pType, String pName, String pInventory, String pPriceOneDay) {

		boolean b = false;

		try {

			// �������ݿ�
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			String name = new String(pName.getBytes("ISO-8859-1"),"UTF-8");
			int i = statement.executeUpdate("update products set pType='"+pType+"', pName='"+name+"', pInventory='"+pInventory+"', pPriceOneDay='"+pPriceOneDay+"' where pId='"+pId+"' ");
			if (i == 1) {
				// ɾ���ɹ�
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			// �ر���Դ
			this.close();
		}

		return b;
	}
	

}
