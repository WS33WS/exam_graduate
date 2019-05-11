package entity;

import java.util.List;

public class Classes {
	private int classesId;
	private String classesName;
	private int teacherId;
	private String situation;
	private String teacherName;
	private String allStudent;
	private List<Student> student;
	
	public Classes() {}

	public int getClassesId() {
		return classesId;
	}

	public void setClassesId(int classesId) {
		this.classesId = classesId;
	}

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getAllStudent() {
		return allStudent;
	}

	public void setAllStudent(String allStudent) {
		this.allStudent = allStudent;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public Classes(int classesId, String classesName, int teacherId, String teacherName, String allStudent,
			List<Student> student) {
		super();
		this.classesId = classesId;
		this.classesName = classesName;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.allStudent = allStudent;
		this.student = student;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	@Override
	public String toString() {
		return "Classes [classesId=" + classesId + ", classesName=" + classesName + ", teacherId=" + teacherId
				+ ", situation=" + situation + ", teacherName=" + teacherName + ", allStudent=" + allStudent
				+ ", student=" + student + "]";
	}
	


	
}
