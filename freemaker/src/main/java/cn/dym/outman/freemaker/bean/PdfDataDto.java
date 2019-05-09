package cn.dym.outman.freemaker.bean;

public class PdfDataDto {
	
//	办理编号
	private String numbering;
//	申办人
	private String sponsor;
//	联系方式
	private String phone;
//	受理时间
	private String acceptTime;
//	受理人
	private String acceptMan;
//	咨询电话
	private String tel;
//	承诺时间
	private String commitTime;
//	经办人
	private String manager;
//	办理类型
	private String type;
//	取件窗口
	private String pickupWindow;
//	经办人(签字)
	private String managerSign;
//	材料名称
	private String materialName;
//	备注
	private String note;
//	申请人签名
	private String sponsorSign;
//  标题
	private String title;
	/**
	 * @param//	办理编号
	private String numbering;
//	申办人
	private String sponsor;
//	联系方式
	private String phone;
//	受理时间
	private String acceptTime;
//	受理人
	private String acceptMan;
//	咨询电话
	private String tel;
//	承诺时间
	private String commitTime;
//	经办人
	private String manager;
//	办理类型
	private String type;
//	取件窗口
	private String pickupWindow;
//	经办人(签字)
	private String managerSign;
//	材料名称
	private String materialName;
//	备注
	private String note;
//	申请人签名
	private String sponsorSign;
//  标题
	private String title;
	 * */
	public PdfDataDto(String sponsorSign,String note,String materialName,String managerSign
			,String pickupWindow,String type,String manager,String commitTime,String tel
			,String acceptMan,String acceptTime,String phone,String sponsor,String numbering
			,String title) {
		// TODO Auto-generated constructor stub
		this.acceptMan=acceptMan;
		this.acceptTime=acceptTime;
		this.commitTime=commitTime;
		this.manager=manager;
		this.managerSign=managerSign;
		this.materialName=materialName;
		this.note=note;
		this.numbering=numbering;
		this.phone=phone;
		this.pickupWindow=pickupWindow;
		this.sponsor=sponsor;
		this.sponsorSign=sponsorSign;
		this.tel=tel;
		this.type=type;
		this.title=title;
	}
	
	
	//getset方法
	public String getNumbering() {
		return numbering;
	}
	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getAcceptMan() {
		return acceptMan;
	}
	public void setAcceptMan(String acceptMan) {
		this.acceptMan = acceptMan;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCommitTime() {
		return commitTime;
	}
	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPickupWindow() {
		return pickupWindow;
	}
	public void setPickupWindow(String pickupWindow) {
		this.pickupWindow = pickupWindow;
	}
	public String getManagerSign() {
		return managerSign;
	}
	public void setManagerSign(String managerSign) {
		this.managerSign = managerSign;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getSponsorSign() {
		return sponsorSign;
	}
	public void setSponsorSign(String sponsorSign) {
		this.sponsorSign = sponsorSign;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
}
