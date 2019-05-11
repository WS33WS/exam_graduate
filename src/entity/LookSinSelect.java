package entity;

public class LookSinSelect {
	private Questionselect content;
	private String my;
	private String score;
	public Questionselect getContent() {
		return content;
	}
	public void setContent(Questionselect content) {
		this.content = content;
	}
	public String getMy() {
		return my;
	}
	public void setMy(String my) {
		this.my = my;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public LookSinSelect(Questionselect content, String my, String score) {
		super();
		this.content = content;
		this.my = my;
		this.score = score;
	}
	public LookSinSelect() {}
	@Override
	public String toString() {
		return "LookSinSelect [content=" + content + ", my=" + my + ", score=" + score + "]";
	}
	
	

}
