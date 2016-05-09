
// �Կͻ���Ĳ���
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
	private int pageSize = 5;   //ÿҳ��ʾ�Ŀͻ���Ŀ
	private int rowCount = 0;   //�ͻ������� ����ֵ�����ݿ�õ�
	private int pageCount = 0;  //ҳ��������õ�
	
	
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
	 * ���ͻ��Ƿ����
	 * @param name �ͻ����û���
	 * @param password �ͻ�������
	 * @return һ��booleanֵ��true���ͻ����ڣ�false���ͻ������ڣ�
	 */
	public boolean checkCustomer(String name, String password) {
		/**
		 * 
		 */
		boolean b = false;
		
		try {
			//�������ݿ�
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
			
			//�ر���Դ
			this.close();
		}
		
		return b;
	}
	
	
	/**
	 * �����ҳ����ҳ��
	 * @return һ��intֵ
	 */
	public int getPageCount() {
		
		try {
			//�������ݿ�
			connection = new ConnectDB().getConnect();
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select count(*) from audiomanagesystem.customers");
			
			if(resultSet.next()){
				rowCount = resultSet.getInt(1);
			}
			
			//����pageCount��ҳ��
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
	 * �õ��û���Ҫ��ʾ�Ŀͻ���Ϣ
	 * @param pageNow Ҫ��ʾ��ҳ��
	 * @param need ����ʱ������
	 * @param needValue ����ʱ���͵�ֵ
	 * @return ArrayList<CustomerBean>����
	 */
	public ArrayList<CustomerBean> getCustomerByPage( int pageNow, String need, String needValue) {

		ArrayList<CustomerBean> arrayList = new ArrayList<>();
		
		try {
			//�������ݿ�
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
												
			if(need.equals("all")){
				resultSet = statement.executeQuery("select * from customers limit " + pageSize*(pageNow-1) + "," + pageSize + ";");
			} else if (need.equals("cId")) {
				resultSet = statement.executeQuery("select * from customers where cId='"+needValue+"'");
			} else if (need.equals("cName")) {
				resultSet = statement.executeQuery("select * from customers where cName='"+needValue+"'");
			}
	
			//��resultment��װ��ArrayList��
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
	 * ɾ���ͻ�
	 * @param cId ��Ҫɾ���Ŀͻ���id��
	 * @return booleanֵ��true��ɾ���ɹ���false��ɾ��ʧ�ܣ�
	 */
	public boolean deleteById (int cId) {
		
		boolean b = false;
		
		try {
			
			//�������ݿ�
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			int i = statement.executeUpdate("delete from customers where cId = " + cId + "");
			if(i == 1) {
				//ɾ���ɹ�
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			//�ر���Դ
			this.close();
		}
		
		return b;
	}
	

	/**
	 * ��ӿͻ�
	 * @param cName ��ӿͻ����û���
	 * @param cPassword ��ӿͻ�������
	 * @param cContactWay ��ӿͻ�����ϵ��ʽ
	 * @return booleanֵ��true����ӳɹ���false�����ʧ�ܣ�
	 */
	public boolean addCustomer(String cName, String cPassword, String cContactWay) {
		
		boolean b = false;
		
		try {
			
			//�������ݿ�
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			int i = statement.executeUpdate("insert into customers (cName, cPassword, cContactWay) value ('"+cName+"', '"+cPassword+"', '"+cContactWay+"')");
			if(i == 1) {
				//��ӳɹ�
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			//�ر���Դ
			this.close();
		}
		
		return b;
	}
	

	/**
	 * �޸Ŀͻ���Ϣ
	 * @param cId ��Ҫ�޸ĵĿͻ���id��
	 * @param cName ��Ҫ�޸ĵĿͻ����û���
	 * @param cPassword ��Ҫ�޸ĵĿͻ�������
	 * @param cContactWay ��Ҫ�޸ĵĿͻ�����ϵ��ʽ
	 * @return booleanֵ��true���޸ĳɹ���false���޸�ʧ�ܣ�
	 */
	public boolean updateCustomer(String cId, String cName, String cPassword, String cContactWay) {
		
		boolean b = false;
		
		try {
			
			//�������ݿ�
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			int i = statement.executeUpdate("update customers set cName='"+cName+"', cPassword='"+cPassword+"', cContactWay='"+cContactWay+"' where cId='"+cId+"'");
			if(i == 1) {
				//��ӳɹ�
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			//�ر���Դ
			this.close();
		}
		
		return b;
	}
}
