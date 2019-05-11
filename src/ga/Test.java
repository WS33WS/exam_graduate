package ga;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import dao.QuestionDao;
import entity.Questionselect;
import entity.Questionsystem;

public class Test {
	/// 知识点分布权重
	public static double F2 ;
	/// 试卷难度系数权重
	public static double F1 ;
//自动组卷的综合函数：
	//输入subject：科目门类 TotalScore：试卷总分  point：知识点数组 ,
//	EachType：每个类型题目的个数数组（先选择，再大题）selectScore：每个选择题的分数 systemScore：每道大题的分数
//	难度系数dic,难度权重 f1,适应度期望值 expect
	public static Paper mymain(String subject,int TotalScore,List<Integer> point,int[] EachType,int selectScore,int systemScore,double f1,double dic,double expect) throws IllegalAccessException, InvocationTargetException {
		//期望试卷
		F1=f1;
		F2=1-f1;
        PaperRequires reqPaper = new PaperRequires();
        reqPaper.ID=1;
        reqPaper.TotalScore = TotalScore;
        reqPaper.Difficulty=dic;
        reqPaper.Points=new ArrayList<Integer>();
        reqPaper.Points.addAll(point);
//        for(int i=0;i<point.length;i++) {reqPaper.Points.add(point[i]); }
        reqPaper.EachTypeCount=EachType;
        //根据学科得到题目
		List<Questionselect> Allquestion=QuestionDao.findAllQuestion(subject);//选择题
		List<Questionsystem> AllquestionSystem=QuestionDao.findAllQuestionSystem(subject);//简答题
		//转换格式
		List<Problem> ProblemDB = new ArrayList<Problem>();
		List<Problem> ProblemDBSelect = new ArrayList<Problem>();
		List<Problem> ProblemDBSAQ = new ArrayList<Problem>();
		ProblemDB=formatChangeSelect(ProblemDB, Allquestion, 0, selectScore);
		ProblemDB=formatChangeSystem(ProblemDB, AllquestionSystem, 1,systemScore);
		ProblemDBSelect=formatChangeSelect(ProblemDB, Allquestion, 0, selectScore);
		ProblemDBSAQ=formatChangeSystem(ProblemDB, AllquestionSystem, 1,systemScore);
      //迭代次数计数器
        int count = 1;
        //最大迭代次数
        int maxCount = 10;
      //初始化种群
        List<Paper> unitPaper =initPaper(20, reqPaper,ProblemDB);//首先出30份试题
        System.out.println("\n\n      -------遗传算法组卷系统---------\n\n");
        System.out.println("初始种群：");
        for(int i=0;i<unitPaper.size();i++) {
        	ShowUnit(unitPaper.get(i));
        }
        while (!IsEnd(unitPaper, expect))
        {
            System.out.println("在第 " + (count++) + " 代未得到结果");
            if (count > maxCount)
            {
                System.out.println("计算 " + maxCount + " 代仍没有结果，请重新设计条件！");
                Paper failunit=new Paper();
                return failunit;
            }
            //选择
            unitPaper = Select(unitPaper, 10);
            //交叉
            unitPaper = Cross(unitPaper, 20, reqPaper);
            //是否可以结束（有符合要求试卷即可结束）
            if (IsEnd(unitPaper, expect))
            {
                break;
            }
            //变异
            unitPaper = Change(unitPaper,ProblemDBSelect,ProblemDBSAQ, reqPaper);
        }
            System.out.println("在第 " + count + " 代得到结果，结果为：\n");
            System.out.println("期望试卷难度：" + reqPaper.Difficulty + "\n");
            ShowResult(unitPaper, expect);
            
            System.out.println("===============end==================");
            //选出适应度最高的
            Paper temp=unitPaper.get(0);
            for(int l=1;l<unitPaper.size();l++) {
            	if(temp.AdaptationDegree<unitPaper.get(l).AdaptationDegree) {
            		temp=unitPaper.get(l);
            	}
            }
            Paper endUnit=new Paper();
            endUnit=temp;
            List<Paper> endList=new ArrayList<Paper>();
            endList.add(endUnit);
            ShowResult(endList, expect);
            return endUnit;
	}

//	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException 
//	{
//		List<Integer> point=new ArrayList<Integer>();
//		point.add(1);
//		point.add(2);
//		int[] EachType={4,2};
//		Paper unit=mymain("语文",28,point,EachType,2, 10,0.5,0.6,0.7);
//	}
	 /// 初始种群 
    //count个体数量； paper期望试卷；problemList题库；returns初始种群
    public static List<Paper> initPaper(int count, PaperRequires reqPaper, List<Problem> problemList) throws IllegalAccessException, InvocationTargetException
    {
        List<Paper> unitPaper = new ArrayList<Paper>();
        int[] eachTypeCount = reqPaper.EachTypeCount;
        Paper paper;
        Random rand = new Random();
        for (int i = 0; i < count; i++){//出count份卷子
            paper = new Paper();
            paper.ID = i + 1;//尝试出的卷子编号，自增长
            paper.AdaptationDegree = 0.00;//适应度

            //总分限制
            while (reqPaper.TotalScore != paper.SumScore){
                paper.ProblemList.clear();

                //各题型题目数量限制
                
                for (int j = 0; j < eachTypeCount.length; j++){//几个题目类型
                	List<Problem> oneTypeProblem=new ArrayList<Problem>();
                	for(int m=0;m<problemList.size();m++) {
                		if(problemList.get(m).Type==j) {
                			if(IsContain(reqPaper.Points,problemList.get(m))) {
                				oneTypeProblem.add(problemList.get(m));
                			}
                		}
                	}
                	if(oneTypeProblem.size()<eachTypeCount[j]) {
                		System.out.println("题库数据不够"+j);
                	}
                    
                    for (int k = 0; k < eachTypeCount[j]; k++){//题型中的题目个数
                        //选择不重复的题目
                    	int index = rand.nextInt(oneTypeProblem.size() - k);
                    	Problem pro = new Problem();
                    	BeanUtils.copyProperties(pro,oneTypeProblem.get(index));
                        paper.ProblemList.add(pro);
                        Problem temp = new Problem();
                        BeanUtils.copyProperties(temp,oneTypeProblem.get(oneTypeProblem.size() - 1 - k));
                        BeanUtils.copyProperties(oneTypeProblem.get(oneTypeProblem.size() - 1 - k),oneTypeProblem.get(index));//后面赋值给前面
                        BeanUtils.copyProperties(oneTypeProblem.get(index),temp);
                    }
                }
                paper.SumScore=paper.getSumScore();
                paper.Difficulty=paper.getDifficulty();
            }
            unitPaper.add(paper);
        }

//        计算知识点覆盖率及适应度
        unitPaper = GetDOKP(unitPaper, reqPaper);
        unitPaper = Fitness(unitPaper, reqPaper,F2, F1);

        return unitPaper;
    } 
    // 题目知识点是否符合试卷要求
    //points要求知识点，problem一道试题  返回一个布尔值
    private static boolean IsContain(List<Integer> points,Problem problem){
        for (int i = 0; i < problem.Points.size(); i++)
        {
            if (points.contains(problem.Points.get(i)))
            {
                return true;
            }
        }
        return false;
    }
  /// 显示种群个体题目编号
    ///u种群个体
    public static void ShowUnit(Paper u)
    {
        System.out.println("编号\t知识点分布\t难度系数");
        System.out.println(u.ID + "\t" + u.KPCoverage + "\t\t" + u.Difficulty);
        System.out.print("本试卷题目有：  ");
        for(int i=0;i<u.ProblemList.size();i++) {
        	System.out.print(u.ProblemList.get(i).getID() + "， ");
        }
        System.out.println();
        System.out.println();
    }
  /// 显示结果
    /// unitList种群expect期望适应度
    public static void ShowResult(List<Paper> unitList, double expect)
    {
    	for(int i=0;i<unitList.size();i++) {
    		if (unitList.get(i).AdaptationDegree >= expect)
            {
                System.out.println("第" + unitList.get(i).ID + "套：");
                System.out.println("题目数量\t知识点分布\t难度系数\t适应度");
                System.out.println(unitList.get(i).ProblemList.size() + "\t\t" + unitList.get(i).KPCoverage + "\t\t" + unitList.get(i).Difficulty + "\t\t" + unitList.get(i).AdaptationDegree+"\n\n");
            }
    	}
    }
//    是否达到目标
    public static boolean IsEnd(List<Paper> unitPaper, double endcondition)
    {
        if (unitPaper.size() > 0)
        {
            for (int i = 0; i < unitPaper.size(); i++)
            {
                if (unitPaper.get(i).AdaptationDegree >= endcondition)
                {
                    return true;
                }
            }
        }
        return false;
    }
//    选择算子（轮盘赌选择）
//    unitList种群，count选择次数，returns进入下一代的种群
    public static List<Paper> Select(List<Paper> unitPaper, int count){
        List<Paper> selectedUnitPaper = new ArrayList<Paper>();

        //种群个体适应度和
        double AllAdaptationDegree = 0;
        for(int i=0;i<unitPaper.size();i++) {
        	AllAdaptationDegree += unitPaper.get(i).AdaptationDegree;
        }
//        System.out.println(AllAdaptationDegree);
        Random rand = new Random();
        while (selectedUnitPaper.size() != count){
            //选择一个0—1的随机数字
            double randDegree = rand.nextInt(100)*0.01;
            //选择符合要求的个体
            double degree = 0.00;
            for (int j = 0; j < unitPaper.size(); j++)
            {
                if (degree<randDegree&&randDegree<=(degree+unitPaper.get(j).AdaptationDegree/AllAdaptationDegree))
                {
                    //不重复选择
                    if (!selectedUnitPaper.contains(unitPaper.get(j)))
                    {
                        selectedUnitPaper.add(unitPaper.get(j));
                    }
                    break;
                }
                degree += unitPaper.get(j).AdaptationDegree/AllAdaptationDegree;
            }
        }
        return selectedUnitPaper;
    } 
    // 计算知识点覆盖率
    /// unitPaper卷子群; reqPaper期望试卷
    public static List<Paper> GetDOKP(List<Paper> unitPaper, PaperRequires reqPaper)
    {
		Set<Integer> bingji = new HashSet<>();
		for (int i = 0; i < unitPaper.size(); i++) {
			for (int j = 0; j < unitPaper.get(i).ProblemList.size(); j++) {
				bingji.addAll(unitPaper.get(i).ProblemList.get(j).Points);
			}
			unitPaper.get(i).KPCoverage = bingji.size() * 1.00 / reqPaper.Points.size();

		}
		return unitPaper;
    }
// 计算适应度
    //unitPaper试卷群，reqPaper期望试卷，f2知识点分布在适应度计算中所占权重，
    //f1试卷难度系数在适应度计算中所占权重，返回unitPaper种群
    public static List<Paper> Fitness(List<Paper> unitPaper, PaperRequires reqPaper, double f2, double f1)
    {
        unitPaper = GetDOKP(unitPaper, reqPaper);
        for (int i = 0; i < unitPaper.size(); i++)
        {
            unitPaper.get(i).AdaptationDegree = 1 - (1 - unitPaper.get(i).KPCoverage) * f2 - Math.abs(unitPaper.get(i).Difficulty - reqPaper.Difficulty) * f1;
        }
        return unitPaper;
    }
    /// 交叉算子
    /// unitPaper种群count为交叉后产生的新种群个体数量reqPaper 期望试卷;return recomUnitPaper
    public static List<Paper> Cross(List<Paper> unitPaper, int count, PaperRequires reqPaper) throws IllegalAccessException, InvocationTargetException
    {
        List<Paper> recomUnitPaper = new ArrayList<Paper>();
        Random rand = new Random();
        while (recomUnitPaper.size() != count)
        {
            //随机选择两个个体
            int indexOne = rand.nextInt(unitPaper.size());
            int indexTwo = rand.nextInt(unitPaper.size());
            Paper unitOne;
            Paper unitTwo;
            if (indexOne != indexTwo)
            {
                unitOne = unitPaper.get(indexOne);
                unitTwo = unitPaper.get(indexTwo);

                //随机选择一个交叉位置
                int forRand=unitOne.ProblemList.size()- 2;
                int crossPosition = rand.nextInt(forRand);

                //保证交叉的题目分数和相同，题型相同
                
                double scoreOne = unitOne.ProblemList.get(crossPosition).getScore() + unitOne.ProblemList.get(crossPosition+1).getScore();
                double scoreTwo = unitTwo.ProblemList.get(crossPosition).getScore() + unitTwo.ProblemList.get(crossPosition+1).getScore();
//                if (scoreOne == scoreTwo&&unitOne.ProblemList.get(crossPosition).Type==unitOne.ProblemList.get(crossPosition+1).Type&&unitTwo.ProblemList.get(crossPosition).Type==unitTwo.ProblemList.get(crossPosition+1).Type)
                if (scoreOne == scoreTwo)
                {
                    //两个新个体
                    Paper unitNewOne = new Paper();
//                    unitNewOne.ProblemList.addAll(unitOne.ProblemList);
                    unitNewOne.ProblemList.addAll(unitOne.ProblemList);
                    Paper unitNewTwo = new Paper();
                    unitNewTwo.ProblemList.addAll(unitTwo.ProblemList);

                    //交换交叉位置后面两道题
                    for (int i = crossPosition; i < crossPosition + 2; i++)
                    {
                    	Problem temp1=new Problem();
                    	BeanUtils.copyProperties(temp1,unitTwo.ProblemList.get(i));
                    	BeanUtils.copyProperties(unitNewOne.ProblemList.get(i),temp1);
                    	Problem temp2=new Problem();
                    	BeanUtils.copyProperties(temp2,unitOne.ProblemList.get(i));
                    	BeanUtils.copyProperties(unitNewTwo.ProblemList.get(i),temp2);
                    }
                    //判断本套题是否有题目重复
                    if(Equals(unitNewOne.ProblemList)) {
                    	//添加到新种群集合中
                    	unitNewOne.ID = recomUnitPaper.size();
                    	if (recomUnitPaper.size() < count)
                        {
                        	
                            recomUnitPaper.add(unitNewOne);
                        }
                    }
                    if(Equals(unitNewOne.ProblemList)) {
                    	//添加到新种群集合中
                    	unitNewTwo.ID = recomUnitPaper.size();
                    	if (recomUnitPaper.size() < count)
                        {
                            recomUnitPaper.add(unitNewTwo);
                        }
                    }
                }
            }

            
           
        }
        //计算难度和总分
        for(int i=0;i<recomUnitPaper.size();i++) {
        	recomUnitPaper.get(i).setSumScore(recomUnitPaper.get(i).getSumScore());
        	recomUnitPaper.get(i).setDifficulty(recomUnitPaper.get(i).getDifficulty());
        	
        }

        //计算知识点覆盖率及适应度
        
        recomUnitPaper = GetDOKP(recomUnitPaper, reqPaper);
        recomUnitPaper = Fitness(recomUnitPaper, reqPaper, F2, F1);

        return recomUnitPaper;
    }
    /// 变异算子
    //unitList种群problemList题库paper期望试卷;returnsList
    public static List<Paper> Change(List<Paper> unitPaper, List<Problem> problemSelect, List<Problem> problemSAQ, PaperRequires reqPaper) throws IllegalAccessException, InvocationTargetException
    {
        Random rand = new Random();
        int index = 0;
        for(int i=0;i<unitPaper.size();i++) {
        	 //随机选择一道题
            index = rand.nextInt(unitPaper.get(i).ProblemList.size());
            Problem temp = unitPaper.get(i).ProblemList.get(index);

            //得到这道题的知识点
            Problem problem = new Problem();
            for (int j = 0; j < temp.Points.size(); j++)
            {
                if (reqPaper.Points.contains(temp.Points.get(j)))
                {
                    problem.Points.add(temp.Points.get(j));
                }
            }
          //从数据库中选择包含此题有效知识点的同类型同分数不同题号试题
            List<Problem> smallDBSelect=new ArrayList<Problem>();
            List<Problem> smallDBSAQ=new ArrayList<Problem>();
            if(temp.Type==0) {//选择题
            	 for(int j=0;j<problemSelect.size();j++) {
            		 if(IsContain(problemSelect.get(j).Points, problem)) {
            			 smallDBSelect.add(problemSelect.get(j));         			 
            		 }
            	 }
            	 //从符合要求的选择题中随机选一题替换
                 if (smallDBSelect.size() > 0)
                 {
                     int changeIndex = rand.nextInt(smallDBSelect.size());
                     BeanUtils.copyProperties(unitPaper.get(i).ProblemList.get(index),smallDBSelect.get(changeIndex));
                 }
            }
            else {//大题
            	for(int j=0;j<problemSAQ.size();j++) {
           		 if(IsContain(problemSAQ.get(j).Points, problem)) {
           			 smallDBSAQ.add(problemSAQ.get(j));         			 
           		 }
           	//从符合要求的大题中随机选一题替换
                 if (smallDBSAQ.size() > 0)
                 {
                     int changeIndex = rand.nextInt(smallDBSAQ.size());
                     BeanUtils.copyProperties(unitPaper.get(i).ProblemList.get(index),smallDBSAQ.get(changeIndex));
                 }
            	}
            }
        }
      //计算难度和总分
        for(int i=0;i<unitPaper.size();i++) {
        	unitPaper.get(i).setSumScore(unitPaper.get(i).getSumScore());
        	unitPaper.get(i).setDifficulty(unitPaper.get(i).getDifficulty());
        	
        }

        //计算知识点覆盖率跟适应度
        unitPaper = GetDOKP(unitPaper, reqPaper);
        unitPaper = Fitness(unitPaper, reqPaper, F2, F1);
        return unitPaper;
    } 
    public static List<Problem> formatChangeSelect(List<Problem> ProblemDB,List<Questionselect> Allquestion,int type,int score) {
    	Problem model;
		for(int i=0;i<Allquestion.size();i++) {
			model = new Problem();
			model.ID=Allquestion.get(i).getQue_id();
			model.Type=type;
			model.Score=score;
			model.Difficulty=Allquestion.get(i).getL();
			String[] select = Allquestion.get(i).getPoints().split(",");
			for(int j=0;j<select.length;j++) {
				model.Points.add(Integer.parseInt(select[j]));
			}
            ProblemDB.add(model);
		}
		return ProblemDB;
    }
    public static List<Problem> formatChangeSystem(List<Problem> ProblemDB,List<Questionsystem> Allquestion,int type,int score) {
    	Problem model;
		for(int i=0;i<Allquestion.size();i++) {
			model = new Problem();
			model.ID=Allquestion.get(i).getQue_id();
			model.Type=type;
			model.Score=score;
			model.Difficulty=Allquestion.get(i).getL();
			String[] select = Allquestion.get(i).getPoints().split(",");
			for(int j=0;j<select.length;j++) {
				model.Points.add(Integer.parseInt(select[j]));
			}
            ProblemDB.add(model);
		}
		return ProblemDB;
    }
    public static boolean Equals(List<Problem> x)
    {
        boolean result = true;
        int len=x.size();
        for(int i=0;i<(len-1);i++) {
     		for(int j=(i+1);j<len;j++) {
     			if (x.get(i).ID == x.get(j).ID)
                {
                    result = false;
                    break;
                }
     		}
     	}
        return result;
    }
    
}
