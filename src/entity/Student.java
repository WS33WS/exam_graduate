package entity;

import java.util.List;

/**
 * @author Honey
 *
 */
public class Student {
	private int id;
	private String name;
	private String tele;
	private byte sex;
	private String address;
	private String pass;
	private String classes;
	private int exam_state;
	private List<Integer> allClasses;
	
	public Student() {}

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

	public int getExam_state() {
		return exam_state;
	}

	public void setExam_state(int exam_state) {
		this.exam_state = exam_state;
	}

	public List<Integer> getAllClasses() {
		return allClasses;
	}

	public void setAllClasses(List<Integer> allClasses) {
		this.allClasses = allClasses;
	}

	public Student(int id, String name, String tele, byte sex, String address, String pass, String classes,
			int exam_state, List<Integer> allClasses) {
		super();
		this.id = id;
		this.name = name;
		this.tele = tele;
		this.sex = sex;
		this.address = address;
		this.pass = pass;
		this.classes = classes;
		this.exam_state = exam_state;
		this.allClasses = allClasses;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", tele=" + tele + ", sex=" + sex + ", address=" + address
				+ ", pass=" + pass + ", classes=" + classes + ", exam_state=" + exam_state + ", allClasses="
				+ allClasses + "]";
	}
	


	
}
