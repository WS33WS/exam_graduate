package ga;

import java.util.ArrayList;
import java.util.List;
public class Problem {
	/// 编号
    public int ID ;
    /// 题型（1、2、3、4、5对应单选，多选，判断，填空，问答）
    public int Type ;
    /// 分数
    public int Score;
    /// 难度系数
    public double Difficulty;
    /// 知识点,每道题可能有好几个知识点
    public List<Integer> Points;
	public Problem()
    {
        ID = 0;
        Type = 0;
        Score = 0;
        Difficulty = 0.00;
        Points = new ArrayList<Integer>();///
    }

    public Problem(Problem p)
    {
        this.ID = p.ID;
        this.Type = p.Type;
        this.Score = p.Score;
        this.Difficulty = p.Difficulty;
        this.Points = p.Points;
    }

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
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
    

    
}
