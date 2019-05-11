package entity;

public class Teacher {
	private int id;
	private String name;
	private String tele;
	private byte sex;
	private String address;
	private String pass;
	private String classes;
	private String institution;
	private String school;
	
	public Teacher() {}

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

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Teacher(int id, String name, String tele, byte sex, String address, String pass, String classes,
			String institution, String school) {
		super();
		this.id = id;
		this.name = name;
		this.tele = tele;
		this.sex = sex;
		this.address = address;
		this.pass = pass;
		this.classes = classes;
		this.institution = institution;
		this.school = school;
	}
	
}
