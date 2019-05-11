package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.Knowledge;
import exam.DbUtils;

public class KnowledgeDao {
private static QueryRunner runner = new QueryRunner(DbUtils.getDataSource());//导入数据库数据
	//每门课的知识点列表
//输入科目名称（不是课程名），返回知识点列表
	public static  List<Knowledge> findBySituation(String situation) {
		try {
			String sql="select id id,situation situation,point point,pointName pointName\r\n" + 
					"from knowledge\r\n" + 
					"where situation=?";
			List<Knowledge> kno=runner.query(sql, new BeanListHandler<>(Knowledge.class),situation);
			return kno;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
