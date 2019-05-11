package entity;

public class Knowledge {
	private int id;
	private String situation;
	private int point;
	private String pointName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public Knowledge() {};
	public Knowledge(int id, String situation, int point, String pointName) {
		super();
		this.id = id;
		this.situation = situation;
		this.point = point;
		this.pointName = pointName;
	}
	

}
