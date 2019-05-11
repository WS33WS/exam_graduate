package entity;

public class LookSinSystem {
	private Questionsystem content;
	private String my;
	private String score;
	public Questionsystem getContent() {
		return content;
	}
	public void setContent(Questionsystem content) {
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
	public LookSinSystem(Questionsystem content, String my, String score) {
		super();
		this.content = content;
		this.my = my;
		this.score = score;
	}
	public LookSinSystem() {}
	@Override
	public String toString() {
		return "LookSinSystem [content=" + content + ", my=" + my + ", score=" + score + "]";
	}
	

}
