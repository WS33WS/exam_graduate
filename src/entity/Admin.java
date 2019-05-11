package entity;

public class Admin {
	private int id;
	private String name;
	private String tele;
	private byte sex;
	private String address;
	private String pass;
	private String school;
	
	
	
	public Admin() {}
	
	public Admin(int id, String name, String tele, byte sex, String address, String pass, String school) {
		super();
		this.id = id;
		this.name = name;
		this.tele = tele;
		this.sex = sex;
		this.address = address;
		this.pass = pass;
		this.school = school;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	

}
