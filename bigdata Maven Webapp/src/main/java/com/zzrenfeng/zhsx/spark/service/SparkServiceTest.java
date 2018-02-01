package com.zzrenfeng.zhsx.spark.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scala.Tuple2;

import com.zzrenfeng.zhsx.spark.conf.ApplicationConfiguration;
import com.zzrenfeng.zhsx.spark.domain.WordCount;
@Component
public class SparkServiceTest implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	ApplicationConfiguration applicationConfiguration;
	
	public List<WordCount> doWordCount(){
		JavaSparkContext javaSparkContext = applicationConfiguration.javaSparkContext();
		List<WordCount> list= new ArrayList<>();
		try {
			System.out.println(javaSparkContext);
			JavaRDD<String> file = javaSparkContext.textFile(applicationConfiguration.filePath());
			JavaRDD<String> worlds = file.flatMap(new FlatMapFunction<String, String>() {

				@Override
				public Iterable<String> call(String t) throws Exception {
		
					List<String> list = Arrays.asList(t.split(" "));
					return list;
				}
			});
			JavaRDD<WordCount> wordcount = worlds.map(new Function<String, WordCount>() {

				@Override
				public WordCount call(String v1) throws Exception {
					
					return new WordCount(v1,1);
				}
			});
			JavaPairRDD<String, Integer> pairwordCount = wordcount.mapToPair(new PairFunction<WordCount, String, Integer>() {

				@Override
				public Tuple2<String, Integer> call(WordCount t) throws Exception {
					// TODO Auto-generated method stub
					return new Tuple2<>(t.getWord() , new Integer(t.getCount()));
					
				}
			});
			
			JavaPairRDD<String, Integer> worldCounts = pairwordCount.reduceByKey(new Function2<Integer, Integer, Integer>() {

				@Override
				public Integer call(Integer v1, Integer v2) throws Exception {
					// TODO Auto-generated method stub
					return v1+v2;
				}
			});
			JavaRDD result = worldCounts.map(new Function<Tuple2<String,Integer>, WordCount>() {

				@Override
				public WordCount call(Tuple2<String, Integer> v1) throws Exception {
					// TODO Auto-generated method stub
					return new WordCount(v1._1,v1._2);
				}
			});
			list = result.collect();
			
		} finally {
			// TODO: handle exception
			javaSparkContext.close();
		}
		
		
		System.out.println(list.toString());
		return list;
	}

}
