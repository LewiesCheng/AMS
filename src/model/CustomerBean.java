
//对应客户表，代表数据
package model;

public class CustomerBean{
	
	private int cId;          //客户的编号
	private String cName;        //客户的姓名
	private String cPassword;    //客户的登录密码
	private String cContactWay;  //客户的联系方式
	
	public int getcId() {
		return cId;
	}
	public void setcId(int i) {
		this.cId = i;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcPassword() {
		return cPassword;
	}
	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}
	public String getcContactWay() {
		return cContactWay;
	}
	public void setcContactWay(String cContactWay) {
		this.cContactWay = cContactWay;
	}
	
	

}
