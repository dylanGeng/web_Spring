package hiveReadTest;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;
import scala.Function1;
import scala.runtime.BoxedUnit;
/**
 * 读取数据的时候出席那内存溢出，可能是犹豫自己搭建的集群的问题，在本机上资源太少了
 * @author rf
 *
 */
public class ReadHive {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("hiveTEST").setMaster("spark://hadoop:7077");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		SQLContext sql = new HiveContext(jsc);
		DataFrame sqlDF = sql.sql("show tables");
		//sqlDF.foreach(new Function1<Row, BoxedUnit>1);
		jsc.close();
	}

}
