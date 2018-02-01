package com.zzrenfeng.zhsx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.spark.service.SparkServiceUV;
import com.zzrenfeng.zhsx.util.JsonUtil;

@Controller
@RequestMapping("uv")
public class ControllerUV {
	@Autowired
	SparkServiceUV sparkServiceUV;
	
	@RequestMapping("doUVJob")
	@ResponseBody
	public String uv(){
		long count = sparkServiceUV.doUVJob(null);
		System.out.println(count);
		
		return "{\"count\":"+count+"}";
	}
}
