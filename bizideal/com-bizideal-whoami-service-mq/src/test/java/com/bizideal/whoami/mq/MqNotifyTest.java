package com.bizideal.whoami.mq;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;

import com.bizideal.whoami.pojo.DubboxResult;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * Unit test for simple App.
 */
public class MqNotifyTest {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	@Test
	public void AppTest() {
		Gson gson = new Gson();
		List<DubboxResult> persons = new ArrayList<DubboxResult>();
		DubboxResult p = null;
		for (int i = 0; i < 10; i++) {
			p = new DubboxResult();
			p.setStatus("name" + i);
			p.setDataId(i + "");
			persons.add(p);
		}
		String str = gson.toJson(persons);
		System.out.println(str);
		List<DubboxResult> ps = gson.fromJson(str,
				new TypeToken<List<DubboxResult>>() {
				}.getType());
		for (DubboxResult person : ps) {
			System.out.println(person.getDataId());
		}

		String json = "{\"status\":\"怪盗kidou\",\"msg\":\"24\",\"dataId\":\"ikidou@example.com\"}";
		p = gson.fromJson(json, DubboxResult.class);
//		json = null;
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(json);
		System.out.println(jsonObject.has("status"));
		System.out.println(jsonObject.get("status").getAsString());
		System.out.println(p.getStatus());
		String jsonPerson = gson.toJson(p);
		System.out.println(jsonPerson);
		
		JsonArray jsonArray = (JsonArray) new JsonParser().parse(str);
		System.out.println(jsonArray.size());
		
		
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
		 list.add(i+"");
		}
		   // json转为带泛型的list  
        List<DubboxResult> retList = gson.fromJson(str,  
                new TypeToken<List<DubboxResult>>() {  
                }.getType());
        for (DubboxResult dubboxResult : retList) {
			System.out.println(dubboxResult.getMsg());
		}
		String liststrString  = gson.toJson(list);
		JsonArray jsonArrays = (JsonArray) new JsonParser().parse(liststrString);
		System.out.println(jsonArrays.size());
		Gson googleJson = new Gson();
		List<String> gets = googleJson.fromJson(jsonArrays, List.class);
		for (String string : gets) {
			System.out.println(string);
		}
		

	}

}
