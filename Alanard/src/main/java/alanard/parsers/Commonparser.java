package alanard.parsers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import alanard.enums.Errors;

public class Commonparser {
	private Map<String,Object> parser;
	private JSONObject root;
	
	public Commonparser() {
		
		parser = new HashMap<String,Object>();
		initCommonParser();
	}
	
	private Errors initCommonParser() {
		
		try {
			root = JSON.parseObject(IOUtils.toString(new FileInputStream("configs/alanard_configs.json")));

			parser = parse(root);
			return Errors.success;	
		}   catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Errors.error;
		}	
		
	}
	
	/**
	 * Root method to getParameter
	 * @param s
	 * @return String
	 */
	public String getParameter(String s) {
		return (String) parser.get(s);
	}
	
	/**
	 * Access the value without the key
	 * @param key 
	 * @return a faltten hashmap of the object
	 * e.g
	 * getParameter("jedis.useCluster") is equivalent to getObject("jedis").get("useCluster")
	 * TODO optimize the counting
	 */
	public Map<String,Object> getObject(String key){
		JSONObject cur = root;
		int count = key.split(".").length - 1;
		while (count-- > 0) {
			Object v = cur.get(key);
			if (v instanceof JSONObject) {
				cur = (JSONObject) v;
			}	else {
				return null;
			}
		}
		
		if (cur instanceof JSONObject) {
			return null;
		}	else {
			return parse((JSONObject) cur);
		}
	}
	
	/**
	 * The top method that invoke the recursion function below to parse a JSONObject and flatten it into map
	 * @param jObj
	 * @return a hashmap object with faltten key
	 */
	private Map<String,Object> parse(JSONObject jObj){
		HashMap<String,Object> hm = new HashMap<String,Object>();
		parse("",jObj,hm);
		return hm;
	}
		
	/**
	 * This is a recursion method
	 * @param path key path
	 * @param jObj JSONObject that contains the configuration
	 */
	private void parse(String path,JSONObject jObj,Map<String,Object> hm) {
		
		Set<String> keys = jObj.keySet();
		for (String key:keys) {
			Object v = jObj.get(key);
			if (v instanceof JSONObject) {
				parse(path+key+".",(JSONObject)v,hm);
			}	else if (v instanceof JSONArray) {
				JSONArray ja = (JSONArray)v;
				for (int i = 0; i < ja.size();i++) {
					HashMap<String,Object> emptyMap = new HashMap<String,Object>();
					parse("",ja.getJSONObject(i),emptyMap);
					hm.put(key, emptyMap);
				};
				
			}	else {
				hm.put(path+key, v);
			}
		}
	
	}
}
