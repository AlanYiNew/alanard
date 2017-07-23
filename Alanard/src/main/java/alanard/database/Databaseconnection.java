package alanard.database;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Databaseconnection {
	private SqlSessionFactory sqlSessionFactory;
	
	public Databaseconnection(){
		String resource = "mybatis_configs.xml";
		initDatabaseconnection(resource);
	}
	
	public Databaseconnection(String resource) {
		initDatabaseconnection(resource);
	}
	
	private void initDatabaseconnection (String resource){
		
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;	
	}
}
