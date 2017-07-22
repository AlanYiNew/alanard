package alanard.context;

import java.util.HashMap;
import java.util.Map;

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
		cparser.initCommonParser();
		System.out.println("Finished initializing commonparser");
	}
	
	public Object getInstance(Class<?> type) {
		
		return context.get(type);
	
	}
	
	
	
	
}
