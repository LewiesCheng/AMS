//对应数据库中的administrator.products表,
package model;

public class ProductBean {

	private int pID;             //音像制品的编号
	private String pType;        //音像制品的类型
	private String pName;        //音像制品的名字
	private int pInventory;      //音像制品的库存
	private int pPriceOneDay;  //音像制品出租的每天价格
	
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
