package com.ngc.transformers.accounts;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;

import static org.apache.spark.sql.functions.*;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.spark.sql.Column;

public class ParentAccountsTransformer {

	public static void main(String[] args){
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark SQL Examples")
				.master("local")
				.getOrCreate();
		
		Dataset<Row> parentData = spark.read().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_all_parents.csv");
		parentData.show();
		
		spark.sqlContext().udf().register("escapeCsv", new UDF1<String, String>(){
			public String call(String nameToEscape){
				return StringEscapeUtils.escapeCsv(nameToEscape);
			}
		}, DataTypes.StringType);
		
		Column accounType = when(col("accountType").equalTo("PARENT"), lit("01241000000RoOs"));
		
		Column name = when(col("primaryContactPerson").contains("\"").or(col("primaryContactPerson").contains(",")), 
				callUDF("escapeCsv",col("primaryContactPerson"))).otherwise(col("primaryContactPerson"));
		
		parentData = parentData.withColumn("accountType", accounType);
		parentData = parentData.withColumn("primaryContactPerson", name);
		parentData.show();
		

		
		Dataset<Row> mutatedParentData = parentData;
		mutatedParentData.createOrReplaceTempView("mutatedParentData");
		
		Dataset<Row> cleanedData = spark.sql("select a.couch_id, a.couch_rev, a.company, a.accountType, a.primaryContactPerson from mutatedParentData as a");
		cleanedData.write().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_all_parents_ready.csv");
		
		spark.stop();
	}
	
}
