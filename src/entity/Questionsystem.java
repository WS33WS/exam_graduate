package entity;

public class Questionsystem {
	private int que_id;
	private String question;
	private String answer;
	private String RightAnswer;
	private String analysis;
	private String situation;
	private String points;
	private float l;
	private boolean auto;
	private String mykey;
	public Questionsystem() {}
	public String[] getKeys() {
		return mykey.split("/");
	}
	public int getQue_id() {
		return que_id;
	}
	public void setQue_id(int que_id) {
		this.que_id = que_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getRightAnswer() {
		return RightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		RightAnswer = rightAnswer;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public float getL() {
		return l;
	}
	public void setL(float l) {
		this.l = l;
	}
	public boolean isAuto() {
		return auto;
	}
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	public String getMykey() {
		return mykey;
	}
	public void setMykey(String mykey) {
		this.mykey = mykey;
	}
	public Questionsystem(int que_id, String question, String answer, String rightAnswer, String analysis,
			String situation, String points, float l, boolean auto, String mykey) {
		super();
		this.que_id = que_id;
		this.question = question;
		this.answer = answer;
		RightAnswer = rightAnswer;
		this.analysis = analysis;
		this.situation = situation;
		this.points = points;
		this.l = l;
		this.auto = auto;
		this.mykey = mykey;
	}
	@Override
	public String toString() {
		return "Questionsystem [que_id=" + que_id + ", question=" + question + ", answer=" + answer + ", RightAnswer="
				+ RightAnswer + ", analysis=" + analysis + ", situation=" + situation + ", points=" + points + ", l="
				+ l + ", auto=" + auto + ", mykey=" + mykey + "]";
	}
	
	
	
}
