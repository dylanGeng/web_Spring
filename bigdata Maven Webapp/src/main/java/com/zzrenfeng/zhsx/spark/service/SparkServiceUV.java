package com.zzrenfeng.zhsx.spark.service;

import java.io.Serializable;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzrenfeng.zhsx.spark.conf.ApplicationConfiguration;
import com.zzrenfeng.zhsx.spark.domain.UVTask;

@Component
public class SparkServiceUV implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ApplicationConfiguration applicationConfiguration;
	public long doUVJob(final UVTask  task){
		
		JavaSparkContext jsc = applicationConfiguration.javaSparkContext();
		long count = 0;
		try {
			/*sys_log 字段说明
			 * id 主键
			 *operationname 操作人 （user 表主键）
			 *operationmehtod  操作方法
			 *issuccess 是否成功
			 *reason  失败原因
			 *operationdate 操作日期
			 *content 操作内容
			*/
			JavaRDD<String> sys_logFile = jsc.textFile("hdfs://hadoop:9000/mysql/zhsx/sys_log");
			//System.out.println(sys_logFile.take(2));
			//count = sys_logFile.count();
			//数据切分
			JavaRDD<String[]> sysLogMapFile = sys_logFile.map(new Function<String, String[]>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public String[] call(String arg0) throws Exception {
					// TODO Auto-generated method stub
					String[] str = arg0.split("\t");
					return str;
				}
			}).filter(new Function<String[], Boolean>() {

				@Override
				public Boolean call(String[] v1) throws Exception {
					if(v1.length>3){
						return true;
					}
					return false;
				}
			});
			
			
			//过滤登陆数据
			JavaRDD<String[]> sysLogFilterLogin = sysLogMapFile.filter(new Function<String[], Boolean>() {

				@Override
				public Boolean call(String[] arg0) throws Exception {
					// TODO Auto-generated method stub
					if(arg0==null || arg0.equals("")){
						return false;
					}
					if(arg0[2].equals("登录")){
						return true;
					}
					return false;
				}
			});
			
			List<String[]> list = sysLogFilterLogin.take(10);
			for (String[] strings : list) {
				for (int i = 0; i < strings.length; i++) {
					System.out.print(strings[i]);
				}
				System.out.println("");
			}
			
			count = sysLogFilterLogin.count();
			
		} finally{
			jsc.stop();
		}
		return count;
		
	}
	
	

}
