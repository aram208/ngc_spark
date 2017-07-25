package com.ngc.transformers;

import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.concat_ws;
import static org.apache.spark.sql.functions.isnull;
import static org.apache.spark.sql.functions.length;
import static org.apache.spark.sql.functions.lit;
import static org.apache.spark.sql.functions.not;
import static org.apache.spark.sql.functions.substring;
import static org.apache.spark.sql.functions.when;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;

public class ChildrenAccountsPreprocessor {

	public static void main(String[] args){
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark SQL Examples")
				.master("local")
				.getOrCreate();
		
		Dataset<Row> childrenData = spark.read().csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_bulk_children.csv");
		childrenData.show();
		
		Dataset<Row> parentData = spark.read().csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_bulk_parents.csv");
		parentData.show();
		
		spark.sqlContext().udf().register("getCustomerCode", new UDF1<String, String>(){
			public String call(String outputData){
				return outputData.substring(4);
			}
		}, DataTypes.StringType);
		
		spark.sqlContext().udf().register("titleCase", new UDF1<String, String>(){
			public String call(String countryName){
				return countryName.substring(0,1).toUpperCase().concat(countryName.substring(1).toLowerCase());
			}
		}, DataTypes.StringType);
		
		//childrenData.createOrReplaceTempView("childrenData");
		parentData.createOrReplaceTempView("parentData");
		
		/**
		Column recordTypeSFId = 
				when(col("_c2").equalTo("PMG"), "01241000000RIqT")
				.when(col("_c2").equalTo("NGC"), "01241000000RIqO")
				.when(col("_c2").equalTo("NCS"), "01241000000RYED")
				.when(col("_c2").equalTo("CGC"), "01241000000RIqd")
				.when(col("_c2").equalTo("CSS"), "01241000000k1aV")
				.otherwise("01241000000Qkuv");
		*/
		
		Column recordTypeSFId = 
				when(col("_c4").equalTo("DEALER"), "01241000000Qkuv")  // dealer
				.when(col("_c4").equalTo("INACTIVE"), "INACTIVE")
				.otherwise("01241000000k3qx");
		
		Column calculatedCSTier = when(col("_c4").equalTo("DEALER"), "")
				.otherwise(callUDF("titleCase", col("_c4")));
		
		Column cityName = when(length(col("_c52")).gt(40), substring(col("_c52"), 0, 39)).otherwise(col("_c52"));
		
		Column accountName = when(isnull(col("_c44")), lit("NAME MISSING")).otherwise(col("_c44"));
		
		Column countryTitleCased = when(not(isnull(col("_c55"))).and(length(col("_c55")).gt(2)), callUDF("titleCase", col("_c55")) ).otherwise(col("_c55"));
		
		Column imagePreferences = when(col("_c73").equalTo("1"), lit("Low Resolution")).otherwise("None");
		
		childrenData = childrenData.withColumn("_c52", cityName);
		childrenData = childrenData.withColumn("_c44", accountName);
		childrenData = childrenData.withColumn("_c55", countryTitleCased);
		childrenData = childrenData.withColumn("_c73", imagePreferences);
		
		childrenData = childrenData.withColumn("composite_street", concat_ws(" ", col("_c49"), col("_c50"), col("_c51")));
		childrenData = childrenData.withColumn("composite_notes", concat_ws(" ", col("_c56"), col("_c57")));
		childrenData = childrenData.withColumn("record_type_id", recordTypeSFId);
		childrenData = childrenData.withColumn("CalculatedCSTier", calculatedCSTier);
		
		
		childrenData.show();
		
		Dataset<Row> mutatedChildrenData = childrenData;
		mutatedChildrenData.createOrReplaceTempView("mutatedChildrenData");
				
		Dataset<Row> joinedData = spark.sql("select a.*, b._c0 as parent_id from mutatedChildrenData as a left join parentData as b on a._c3 = getCustomerCode(b._c0) where a._c4 <> 'INACTIVE' "
				+ "and (a._c2 = 'NGC' or (a._c2 = 'NCS' or a._c2 = 'PMG'))");
		
		joinedData.show();
		
		joinedData.write().csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_bulk_children_processed.csv");
		
		spark.stop();
	}
	

}
