//对应数据库中的hire表
package model;

import java.sql.Date;

public class HireBean {

	private int hId;
	private int hCustomerId;
	private int hProductId;
	private int hNumber;
	private Date hBeginDay;
	private Date hReturnDay;
	private int hDeposit;
	private int hTotalPrice;
	private String hWhetherReturn;
	
	public int gethId() {
		return hId;
	}
	public void sethId(int hId) {
		this.hId = hId;
	}
	public int gethCustomerId() {
		return hCustomerId;
	}
	public void sethCustomerId(int hCustomerId) {
		this.hCustomerId = hCustomerId;
	}
	public int gethProductId() {
		return hProductId;
	}
	public void sethProductId(int hProductId) {
		this.hProductId = hProductId;
	}
	public int gethNumber() {
		return hNumber;
	}
	public void sethNumber(int hNumber) {
		this.hNumber = hNumber;
	}
	public Date gethBeginDay() {
		return hBeginDay;
	}
	public void sethBeginDay(Date hBeginDay) {
		this.hBeginDay = hBeginDay;
	}
	public Date gethReturnDay() {
		return hReturnDay;
	}
	public void sethReturnDay(Date hReturnDay) {
		this.hReturnDay = hReturnDay;
	}
	public int gethDeposit() {
		return hDeposit;
	}
	public void sethDeposit(int hDeposit) {
		this.hDeposit = hDeposit;
	}
	public int gethTotalPrice() {
		return hTotalPrice;
	}
	public void sethTotalPrice(int hTotalPrice) {
		this.hTotalPrice = hTotalPrice;
	}
	public String gethWhetherReturn() {
		return hWhetherReturn;
	}
	public void sethWhetherReturn(String hWhetherReturn) {
		this.hWhetherReturn = hWhetherReturn;
	}
	
}
