package alanard.parsers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import alanard.enums.Errors;

public class Commonparser {
	private Map<String,Object> parser;
	
	public Commonparser() {
		
		parser = new HashMap<String,Object>();
		
	}
	
	public Errors initCommonParser() {
		
		JSONObject jObj;
		try {
			jObj = JSON.parseObject(IOUtils.toString(new FileInputStream("configs/alanard_configs.json")));

			parse("",jObj);
			return Errors.success;	
		}   catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Errors.error;
		}	
		
	}
	
	public Object getParameter(String s) {
		return parser.get(s);
	
	}
	
	private void parse(String path,JSONObject jObj) {
		Set<String> keys = jObj.keySet();
		for (String key:keys) {
			Object v = jObj.get(key);
			if (v instanceof JSONObject) {
				parse(path+key+".",(JSONObject)v);
			}	else {
				parser.put(path+key, v);
			}
		}
	}
}
