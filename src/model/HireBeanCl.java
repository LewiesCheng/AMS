//�Զ�hireBean�Ĵ���
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
	private int pageSize = 5; // ÿҳ��ʾ��������Ŀ
	private int rowCount = 0; // ���޵����� ����ֵ�����ݿ�õ�
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
				resultSet = statement.executeQuery("select count(*) from audiomanagesystem.hire");
			}else {
				resultSet = statement.executeQuery("select count(*) from audiomanagesystem.hire where "+need+"='"+needValue+"'");
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
	 * �õ��û���Ҫ��ʾ��������Ϣ
	 * @param pageNow ��Ҫ��ʾ��Ϣ��ҳ��
	 * @param need ��ѯʱ�Ĳ�ѯ����
	 * @param needValue ��ѯ���͵�ֵ
	 * @return ArrayList<HireBean>��
	 */
	public ArrayList<HireBean> getHireByPage(int pageNow, String need, String needValue) {

		ArrayList<HireBean> arrayList = new ArrayList<>();

		try {
			// �������ݿ�
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();

			if(need.equals("all")){
				resultSet = statement.executeQuery("select * from hire limit " + pageSize * (pageNow - 1) + "," + pageSize + ";");
			}else {
				resultSet = statement.executeQuery("select * from hire where "+need+"='"+needValue+"' limit " + pageSize * (pageNow - 1) + "," + pageSize + ";");
			}

			// ��resultment��װ��ArrayList��
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
	 * ���ݿͻ�id��ѯ����δ�黹�����޼�¼
	 * @param pageNow δ�黹��Ϣ����ʾҳ��
	 * @param hCustomerId ��ѯ�Ŀͻ�id��
	 * @return ArrayList<HireBean>��
	 */
	public ArrayList<HireBean> getNoReturnHireByPage(int pageNow, String hCustomerId) {
		
		ArrayList<HireBean> arrayList = new ArrayList<>();

		try {
			// �������ݿ�
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();

			resultSet = statement.executeQuery("select * from hire where hCustomerId='"+hCustomerId+"' and hWhetherReturn='no' limit " + pageSize * (pageNow - 1) + "," + pageSize + ";");

			// ��resultment��װ��ArrayList��
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
	 * ��ӽ����Ϣ
	 * @param hCustomerId �����Ϣ�Ŀͻ�id
	 * @param hProductId �����Ϣ����Ʒid
	 * @param hNumber ���������
	 * @param hBeginDay ��������
	 * @param hDeposit Ѻ��
	 * @return booleanֵ��true����ӳɹ���false�����ʧ�ܣ�
	 */
	public boolean addHire(String hCustomerId, String hProductId, String hNumber, String hBeginDay, String hDeposit) {

		boolean b = false;

		try {

			// �������ݿ�
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
				// ��ӳɹ�
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
	 * �黹������Ϣ
	 * @param hId �黹��Ʒ������id
	 * @param hReturnDay �黹����
	 * @param hTotalPrice �ܼ۸�
	 * @return booleanֵ��true���黹�ɹ���false���黹ʧ�ܣ�
	 */
	public boolean hireReturn(String hId, String hReturnDay, String hTotalPrice) {

		boolean b = false;
		int i = 0, j = 0;
		try {

			// �������ݿ�
			connection = new ConnectDB().getConnect();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select hProductId,hNumber from hire where hId='"+hId+"'");
			if(resultSet.next()){
				i = statement.executeUpdate("update products set pInventory=pInventory+"+resultSet.getInt(2)+" where pId='"+resultSet.getInt(1)+"' ");
			}
			
			j = statement.executeUpdate("update hire set hReturnDay='"+hReturnDay+"', hDeposit='0', hTotalPrice='"+hTotalPrice+"', hWhetherReturn='yes' where hId='"+hId+"' ");
			if (i == 1 && j == 1) {
				// �黹�ɹ�
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
	 * ��֤�ͻ��Ƿ����
	 * @param cName ��֤�ͻ����û���
	 * @param cPassword ��֤�ͻ�������
	 * @return booleanֵ��true����֤�ɹ���false����֤ʧ�ܣ�
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
			
			//�ر���Դ 
			this.close();
		}
		
		return b;
	}
	
	
	/**
	 * ���ݿͻ���ȡ���ͻ���id��
	 * @param cName �ͻ����û���
	 * @return int���ͻ�id�ţ�
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
			
			//�ر���Դ 
			this.close();
		}
		
		return cId;
	}
	
	
	/**
	 * �黹ʱ����cId��ѯδ�黹�ļ�¼
	 * @param cId �ͻ�id��
	 * @return int���ÿͻ�δ�黹�ļ�¼������
	 */
	public int getPageCountOfNoReturnByCId(String cId) {
		
		int pageCount = 0;
		
		try {
			// �������ݿ�
			connection = new ConnectDB().getConnect();

			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select count(*) from audiomanagesystem.hire where hCustomerId='"+cId+"' and hWhetherReturn='no'");

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
	 * �黹ʱ����hId�͹黹���ڼ���Ӧ�ս��
	 * @param hId ����id
	 * @param hReturnDay �黹����
	 * @return int��Ӧ�ս�
	 */
	public int getReturnMoney(String hId, String hReturnDay) {
		
		int returnMoney = 0;
		int hProductId = 0;
		try {
			// �������ݿ�
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
	 * �黹ʱ����hId����Ѻ����
	 * @param hId ����id
	 * @return int�����ص�Ѻ���
	 */
	public int getHireDepositByhId(String hId) {
		int hDeposit = 0;
		try {
			// �������ݿ�
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
	 * ��õ�ǰ���� *����2016-01-01
	 */
	public String  getTodayDate() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = simpleDateFormat.format(date).toString();
		return todayDate;
	}
}
