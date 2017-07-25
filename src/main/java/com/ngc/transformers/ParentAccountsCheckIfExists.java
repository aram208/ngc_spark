package com.ngc.transformers;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

import static org.apache.spark.sql.functions.*;

import org.apache.spark.sql.Column;

public class ParentAccountsCheckIfExists {

	public static void main(String[] args){
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark SQL Examples")
				.master("local")
				.getOrCreate();
		
		Dataset<Row> data = spark.read().csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_bulk_parents.csv");
		data.show();

		Dataset<Row> dataIds = data.filter(col("_c0").equalTo("CCG_0573"));
		dataIds.show();
		
/**
		data.createOrReplaceTempView("data");
		Dataset<Row> dataRow = spark.sql("select * from data where _c0 = 'CCG_0573'");
		dataRow.show();
*/
		
		if(dataIds.count() > 0){
			System.out.println("value: " + dataIds.first().getString(0));
		}else{
			System.out.println("no data found");
		}
		
		
		spark.stop();
	}
	
}
