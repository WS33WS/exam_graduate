package ga;

import java.util.List;

public class PaperRequires {
    /// 编号
    public int ID ;
    /// 总分
    public int TotalScore;
    /// 难度系数
    public double Difficulty ;
    /// 知识点
    public List<Integer> Points;
    /// 各种题型题数
    public int[] EachTypeCount;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getTotalScore() {
		return TotalScore;
	}
	public void setTotalScore(int totalScore) {
		TotalScore = totalScore;
	}
	public double getDifficulty() {
		return Difficulty;
	}
	public void setDifficulty(double difficulty) {
		Difficulty = difficulty;
	}
	public List<Integer> getPoints() {
		return Points;
	}
	public void setPoints(List<Integer> points) {
		Points = points;
	}
	public int[] getEachTypeCount() {
		return EachTypeCount;
	}
	public void setEachTypeCount(int[] eachTypeCount) {
		EachTypeCount = eachTypeCount;
	}
    

}
