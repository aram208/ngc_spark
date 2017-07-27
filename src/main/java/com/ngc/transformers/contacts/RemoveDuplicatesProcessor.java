package com.ngc.transformers.contacts;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.*;

import org.apache.spark.sql.Column;

public class RemoveDuplicatesProcessor {

	public static void main(String[] args){
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark Contacts processor")
				.master("local")
				.getOrCreate();
		
		Dataset<Row> childrenData = spark.read().csv("C:\\Users\\Alien\\Downloads\\Contacts\\bulk_loading\\contacts_test.csv");
		childrenData.show();
	
		childrenData.createOrReplaceTempView("childrenData");
		

		Dataset<Row> joinedData = spark.sql("select first(a._c0), first(a._c1),first(a._c2),first(a._c3),first(a._c4),"
				+ "first(a._c5),first(a._c6),first(a._c7),first(a._c8),first(a._c9),"
				+ "first(a._c10),first(a._c11),first(a._c12),first(a._c13),first(a._c14),first(a._c15),first(a._c16) "
				+ "from childrenData as a group by a._c2, a._c3, a._c5, a._c9");
		joinedData.show();
		joinedData.write().csv("C:\\Users\\Alien\\Downloads\\Contacts\\bulk_loading\\contacts_test_filtered.csv");
		
		spark.stop();
	}
	
}
