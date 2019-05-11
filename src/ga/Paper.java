package ga;

import java.util.ArrayList;
import java.util.List;
//种群个体实体类Unit，
//包含编号、适应度、题数、总分、难度系数、知识点分布、包含的题目等信息
//（也可以修改一下试卷实体，用试卷实体表示）：
public class Paper {
	public Paper()
    {
        ID = 0;
        AdaptationDegree = 0.00;
        KPCoverage = 0.00;
        ProblemList = new ArrayList<Problem>();
    }
    /// 编号
    public int ID;
    /// 适应度
    public double AdaptationDegree;
    /// 难度系数（本试卷所有题目分数*难度系数/总分）
    public double Difficulty;
    /// 总分
    public int SumScore;
    /// 知识点分布
    public double KPCoverage ;
    /// 题目
    public List<Problem> ProblemList;
    //-------------------------------------------------------
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getAdaptationDegree() {
		return AdaptationDegree;
	}
	public void setAdaptationDegree(double adaptationDegree) {
		AdaptationDegree = adaptationDegree;
	}
	public double getDifficulty() {
		double diff = 0.00;
		for(int i=0;i<ProblemList.size();i++) {
			diff += ProblemList.get(i).Difficulty * ProblemList.get(i).Score;
		}
		return diff / SumScore;
	}
	public void setDifficulty(double difficulty) {
		Difficulty = difficulty;
	}
	public int getProblemCount() {
		return ProblemList.size();
	}
	
	public int getSumScore() {
		int score = 0;
		for(int i=0;i<ProblemList.size();i++) {
			score +=ProblemList.get(i).Score;
		}
		return score;
	}
	public void setSumScore(int sumScore) {
		SumScore = sumScore;
	}
	public double getKPCoverage() {
		return KPCoverage;
	}
	public void setKPCoverage(double kPCoverage) {
		KPCoverage = kPCoverage;
	}
	public List<Problem> getProblemList() {
		return ProblemList;
	}
	public void setProblemList(List<Problem> problemList) {
		ProblemList = problemList;
	}
    
    

}
