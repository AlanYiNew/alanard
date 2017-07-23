package alanard.context;

import java.util.HashMap;
import java.util.Map;

import alanard.database.Databaseconnection;
import alanard.parsers.Commonparser;

public class Context {
	private static Map<Class<?>,Object> context;
	
	public Context() {
	
		if (context == null) {
			synchronized (Context.class) {
				if (context == null) {
					context = new HashMap<Class<?>,Object>();
				}
			}
		}
		
		//Find and init all singleton here
		//TODO May need to make it more flexible for users to define their own singleton here
		Commonparser cparser = new Commonparser();
		context.put(Commonparser.class, cparser);
		System.out.println("Finished initializing commonparser");
		
		Databaseconnection dbconnection = new Databaseconnection();
		context.put(Databaseconnection.class, dbconnection);
		System.out.println("Finished initializing dbconnection");
		context.put(Commonparser.class, cparser);
	}
	
	public Object getInstance(Class<?> type) {
		
		return context.get(type);
	
	}
	
	
	
	
}
