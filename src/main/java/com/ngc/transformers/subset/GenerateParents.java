package com.ngc.transformers.subset;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class GenerateParents {
	
	public static void main(String[] args){
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark SQL Examples")
				.master("local")
				.getOrCreate();
		
		Dataset<Row> dealersData = spark.read().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\subset\\dealers.csv");
		dealersData.show();
		
		Dataset<Row> parentsData = spark.read().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_parents_all_ready.csv");
		parentsData.show();

		dealersData.createOrReplaceTempView("dealersData");
		parentsData.createOrReplaceTempView("parentsData");
		
		Dataset<Row> joinedData = spark.sql("select p.* from parentsData as p inner join dealersData as d where "
				+ "p.couch_id = d.parentAccountId");
		
		joinedData.write().option("header", "true").csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\parents_subset.csv");
		
		spark.stop();
		
	}

}
