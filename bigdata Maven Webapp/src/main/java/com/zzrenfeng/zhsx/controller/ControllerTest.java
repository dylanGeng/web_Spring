package com.zzrenfeng.zhsx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.spark.domain.WordCount;
import com.zzrenfeng.zhsx.spark.service.SparkServiceTest;
import com.zzrenfeng.zhsx.util.JsonUtil;

@Controller
@RequestMapping("hello")
public class ControllerTest {
	@Autowired
	private SparkServiceTest sparkServiceTest;
	
	
	@RequestMapping("wc")
	@ResponseBody
	public String wordCount(){
		List<WordCount> list = sparkServiceTest.doWordCount();
		return JsonUtil.listToJson(list);
	}
	
	@RequestMapping("controller")
	@ResponseBody
	public String controller(String name){
		return "hello:"+name;
	}
	@RequestMapping(value="json",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String json(){
		List<String> list = new ArrayList<>();
		list.add("han");
		list.add("韩利鹏");
		String str = JsonUtil.listToJson(list);
		return str;
	}

}
