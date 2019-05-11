package entity;

import java.util.Date;

public class ExamSch {
	
	private int examId;
	private int classes;
	private String examName;
	private Date timeBeg;
	private Date timeEnd;
	private String selects;
	private String systemF;
	private String systemT;
	private int selectScore;
	private int systemScoreF;
	private int systemScoreT;
	private int sumScore;
	
	public ExamSch(){}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getClasses() {
		return classes;
	}

	public void setClasses(int classes) {
		this.classes = classes;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Date getTimeBeg() {
		return timeBeg;
	}

	public void setTimeBeg(Date timeBeg) {
		this.timeBeg = timeBeg;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getSelects() {
		return selects;
	}

	public void setSelects(String selects) {
		this.selects = selects;
	}

	public String getSystemF() {
		return systemF;
	}

	public void setSystemF(String systemF) {
		this.systemF = systemF;
	}

	public String getSystemT() {
		return systemT;
	}

	public void setSystemT(String systemT) {
		this.systemT = systemT;
	}

	public int getSelectScore() {
		return selectScore;
	}

	public void setSelectScore(int selectScore) {
		this.selectScore = selectScore;
	}

	public int getSystemScoreF() {
		return systemScoreF;
	}

	public void setSystemScoreF(int systemScoreF) {
		this.systemScoreF = systemScoreF;
	}

	public int getSystemScoreT() {
		return systemScoreT;
	}

	public void setSystemScoreT(int systemScoreT) {
		this.systemScoreT = systemScoreT;
	}

	public int getSumScore() {
		return sumScore;
	}

	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}

	public ExamSch(int examId, int classes, String examName, Date timeBeg, Date timeEnd, String selects, String systemF,
			String systemT, int selectScore, int systemScoreF, int systemScoreT, int sumScore) {
		super();
		this.examId = examId;
		this.classes = classes;
		this.examName = examName;
		this.timeBeg = timeBeg;
		this.timeEnd = timeEnd;
		this.selects = selects;
		this.systemF = systemF;
		this.systemT = systemT;
		this.selectScore = selectScore;
		this.systemScoreF = systemScoreF;
		this.systemScoreT = systemScoreT;
		this.sumScore = sumScore;
	}

	@Override
	public String toString() {
		return "ExamSch [examId=" + examId + ", classes=" + classes + ", examName=" + examName + ", timeBeg=" + timeBeg
				+ ", timeEnd=" + timeEnd + ", selects=" + selects + ", systemF=" + systemF + ", systemT=" + systemT
				+ ", selectScore=" + selectScore + ", systemScoreF=" + systemScoreF + ", systemScoreT=" + systemScoreT
				+ ", sumScore=" + sumScore + "]";
	}


	
}
