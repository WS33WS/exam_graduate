package exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ExamSchDao;
import dao.ExamScoreDao;
import entity.ExamSch;
import entity.Examscore;

public class WorkWithData {
//	计算出班级成绩的平均值
//	豆粉给豆绿本场考试ID
	
	public static float average(float[] all) {
		float end=0;
		int num=0;
//		循环遍历计算出平均数
		for (float a : all) {
			if(a>0) {
				end+=a;
				num++;
			}
		}
		return end/num;
	} 
	
//	计算出班级成绩的方差
	public static float variance(float[] all) {
//		得到本次考试平均成绩
		float averag=WorkWithData.average(all);
		float end=0;
		int num=0;
//		循环遍历计算出平均数
		for (float a : all) {
			if(a>0) {
				end+= Math.pow((a-averag),2);
				num++;
			}
		}
		return end/num;
	} 
//	计算中位数
	public static float mid(float[] all) {
		Arrays.sort(all);
//		如果人数是奇数，取最中间的
		if(all.length%2==1) {
			int a=all.length/2;
			return all[a];
		}
//		如果人数是偶数，取中间平均值
		else {
			int a=all.length/2;
			float b=(float) ((all[a]+all[a-1])/2.00);
			return b;
		}
	}
//	计算众数
	public static float majorityElement(float[] nums) {

		Map<Float, Integer> map = new HashMap<Float, Integer>();
		int n = nums.length;
		// 统计每个元素出现的次数
		for (float num : nums) 
		{
			Integer count = map.get(num);
			if (count == null)
				count = 1;
			else
				count++;
			map.put(num, count);
		}
		List<Map.Entry<Float, Integer>> entries = new ArrayList<>(map.entrySet());
        // 对 entries 按出现频率从大到小排序
        Collections.sort(entries, new Comparator<Map.Entry<Float, Integer>>() {
            @Override
            public int compare(Map.Entry<Float, Integer> e1, Map.Entry<Float, Integer> e2) {
                return e2.getValue() - e1.getValue();
            }
        });

        List<Float> modalNums = new ArrayList<>();
        modalNums.add(entries.get(0).getKey()); // 排序后第一个 entry 的键肯定是一个众数

        int size = entries.size();
        for (int i = 1; i < size; i++) {
            // 如果之后的 entry 与第一个 entry 的 value 相等，那么这个 entry 的键也是众数
            if (entries.get(i).getValue().equals(entries.get(0).getValue())) {
                modalNums.add(entries.get(i).getKey());
            } else {
                break;
            }
        }

        return modalNums.get(0);
	}
//	计算偏态
//	根据中数，中位数众数来求
	public static int  skewness(float[] all) {
		float ave=WorkWithData.average(all);
		float mid=WorkWithData.mid(all);
		float maj=WorkWithData.majorityElement(all);
//		比较得出
		int[] a=null;
		if(ave==mid&&ave==mid) {
//			System.out.println("正态分布");
			return 0;
		}
		else if(ave>=mid&&mid>=maj) {
//			System.out.println("正偏态分布");
			return 1;
		}
		else {
//			System.out.println("负偏态分布");
			return -1;
		}
	}
//	得出结论
	public static String anal(int exam_id) {
		ExamSch sch=ExamSchDao.findByExamId(exam_id);
//		首先求出
		List<Examscore> hha=ExamScoreDao.findByTestId(exam_id);
		String a=null;
		if(hha.size()==0){
			a="没有学生作答";
		}
		else{
			float[] all=new float[hha.size()];
			for(int i=0;i<hha.size();i++) {
				all[i]=hha.get(i).getScore();
			}

			float ave=WorkWithData.average(all);
			float vari=WorkWithData.variance(all);
			float maj=WorkWithData.majorityElement(all);
			float mid=WorkWithData.mid(all);
			int sk=WorkWithData.skewness(all);
			
			
//			平均数过大输出
			String aveStr = null;
			float eight=sch.getSumScore()*85/100;
			float six=sch.getSumScore()*60/100;
			if(ave>eight) {
				aveStr="平均分过大，有一半以上的人分数大于"+ave+"分。";
			}
			else if(ave<six) {
				aveStr="平均分过小，有一半以上的人分数小于"+ave+"分。";
			}
			else aveStr="";
			
//			方差过大输出
			String variStr ="";
//			偏态输出
			String sStr = "";
			if(sk==0) {
				sStr="呈正态分布，是一份很优秀的试卷。";
			}
			else if(sk==1) {
				sStr="呈正偏态分布，低分人数相对较多。";
			}
			else {
				sStr="呈负偏态分布，高分人数相对较多。";
			}
//			总输出
			a="本场考试平均分为"+ave+"，方差为"+vari+"。"+aveStr+sStr;
			
		}
		
		return a;
		
	} 
//调试测试
	

}
