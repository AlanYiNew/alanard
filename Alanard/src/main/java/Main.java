import java.util.Map;

import alanard.context.Context;
import alanard.parsers.Commonparser;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Context context = new Context();
		Commonparser cparser = (Commonparser) context.getInstance(Commonparser.class);
		System.out.println(cparser.getParameter("db.master"));
		
	}

}
