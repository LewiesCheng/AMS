//��Ӧ���ݿ��е�administrator.products��,
package model;

public class ProductBean {

	private int pID;             //������Ʒ�ı��
	private String pType;        //������Ʒ������
	private String pName;        //������Ʒ������
	private int pInventory;      //������Ʒ�Ŀ��
	private int pPriceOneDay;  //������Ʒ�����ÿ��۸�
	
	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpInventory() {
		return pInventory;
	}
	public void setpInventory(int pInventory) {
		this.pInventory = pInventory;
	}
	public int getpPriceOneDay() {
		return pPriceOneDay;
	}
	public void setpPriceOneDay(int pPriceOneDay) {
		this.pPriceOneDay = pPriceOneDay;
	}
		
}
