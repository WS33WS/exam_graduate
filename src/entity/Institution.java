package entity;

public class Institution {
	private String institution;
	private int count;
	private String name;
	
	public Institution() {}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Institution(String institution, int count) {
		super();
		this.institution = institution;
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
