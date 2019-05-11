package entity;

public class Examscore {
	private  int id;
	private int test_id;
	private int stu_id;
	private String select_score;
	private String systemF_score;
	private String systemT_score;
	private String Select_answer;
	private String SystemF_answer;
	private String SystemT_answer;
	private int system_score;
	private boolean state;
	
	
	public Examscore() {}
	public float getSystemScore() {
		float a=0;
		if(systemF_score!=null) {
			String[] sysF=systemF_score.split(",");
			for(int i=0;i<sysF.length;i++) {
				a+=Float.parseFloat(sysF[i]);
			}
		}
		if(systemT_score!=null) {
			String[] sysT=systemT_score.split(",");
			for(int i=0;i<sysT.length;i++) {
				a+=Float.parseFloat(sysT[i]);
			}
		}
		return a;
		
	}

	
	public float getScore() {
		float a=0;
		if(select_score!=null) {
			a+=Float.parseFloat(select_score);
		}
		if(systemF_score!=null) {
			String[] sysF=systemF_score.split(",");
			for(int i=0;i<sysF.length;i++) {
				a+=Float.parseFloat(sysF[i]);
			}
		}
		
		if(systemT_score!=null) {
//		if(!("".equals(systemT_score))) {
			String[] sysT=systemT_score.split(",");
			for(int i=0;i<sysT.length;i++) {
				a+=Float.parseFloat(sysT[i]);
			}
		}
		return a;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public String getSelect_score() {
		return select_score;
	}
	public void setSelect_score(String select_score) {
		this.select_score = select_score;
	}
	public String getSystemF_score() {
		return systemF_score;
	}
	public void setSystemF_score(String systemF_score) {
		this.systemF_score = systemF_score;
	}
	public String getSystemT_score() {
		return systemT_score;
	}
	public void setSystemT_score(String systemT_score) {
		this.systemT_score = systemT_score;
	}
	public String getSelect_answer() {
		return Select_answer;
	}
	public void setSelect_answer(String select_answer) {
		Select_answer = select_answer;
	}
	public String getSystemF_answer() {
		return SystemF_answer;
	}
	public void setSystemF_answer(String systemF_answer) {
		SystemF_answer = systemF_answer;
	}
	public String getSystemT_answer() {
		return SystemT_answer;
	}
	public void setSystemT_answer(String systemT_answer) {
		SystemT_answer = systemT_answer;
	}
	public int getSystem_score() {
		return system_score;
	}
	public void setSystem_score(int system_score) {
		this.system_score = system_score;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public Examscore(int id, int test_id, int stu_id, String select_score, String systemF_score, String systemT_score,
			String select_answer, String systemF_answer, String systemT_answer, int system_score, boolean state) {
		super();
		this.id = id;
		this.test_id = test_id;
		this.stu_id = stu_id;
		this.select_score = select_score;
		this.systemF_score = systemF_score;
		this.systemT_score = systemT_score;
		Select_answer = select_answer;
		SystemF_answer = systemF_answer;
		SystemT_answer = systemT_answer;
		this.system_score = system_score;
		this.state = state;
	}
	@Override
	public String toString() {
		return "Examscore [id=" + id + ", test_id=" + test_id + ", stu_id=" + stu_id + ", select_score=" + select_score
				+ ", systemF_score=" + systemF_score + ", systemT_score=" + systemT_score + ", Select_answer="
				+ Select_answer + ", SystemF_answer=" + SystemF_answer + ", SystemT_answer=" + SystemT_answer
				+ ", system_score=" + system_score + ", state=" + state + "]";
	}
	

}
