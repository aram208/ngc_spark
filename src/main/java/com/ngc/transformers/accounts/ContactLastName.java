package com.ngc.transformers.accounts;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.concat_ws;
import static org.apache.spark.sql.functions.when;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class ContactLastName {

	public static void main(String[] args){
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark SQL Examples")
				.master("local")
				.getOrCreate();
		
		Dataset<Row> data = spark.read().csv("C:\\Users\\Alien\\Downloads\\Accounts\\accounts_ngc_small.csv");
		data.show();
		
		data = data.withColumn("_c46", when(col("_c46").isNull(), col("_c44")).otherwise(col("_c46")));
		data.show();
		data.write().csv("C:\\Users\\Alien\\Downloads\\Accounts\\accounts_ngc_small_processed.csv");
		
	}
	
}
