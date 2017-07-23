
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import alanard.context.Context;
import alanard.database.Databaseconnection;
import alanard.models.User;
import alanard.models.mappers.Usermapper;
import alanard.parsers.Commonparser;
import alanard.utils.Log;

public class Main {
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Context context = new Context();
		Commonparser cparser = (Commonparser) context.getInstance(Commonparser.class);
		Databaseconnection dbconnection = (Databaseconnection) context.getInstance(Databaseconnection.class);
		SqlSession sqlSession = dbconnection.getSqlSessionFactory().openSession();
		User user = new User();
		user.setUsername("qq245655664");
		user.setUserpwd("qq81938776");
		
		sqlSession.getMapper(Usermapper.class).insert(user);
		sqlSession.commit();
		sqlSession.close();

		System.out.println(cparser.getParameter("db.master"));
		
	}

}
