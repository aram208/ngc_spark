package com.ngc.transformers.accounts;

import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.concat_ws;
import static org.apache.spark.sql.functions.isnull;
import static org.apache.spark.sql.functions.length;
import static org.apache.spark.sql.functions.lit;
import static org.apache.spark.sql.functions.not;
import static org.apache.spark.sql.functions.substring;
import static org.apache.spark.sql.functions.when;
import static org.apache.spark.sql.functions.rtrim;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;

import com.ngc.transformers.utils.EmailValidator;

public class ChildrenAccountsPreprocessor {
	
	public static void main(String[] args){
		
		EmailValidator emailValidator = EmailValidator.INSTANCE;
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark SQL Examples")
				.master("local")
				.getOrCreate();
		
		Dataset<Row> childrenData = spark.read().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_children_all.csv");
		childrenData.show();
		
//		Dataset<Row> parentData = spark.read().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_all_parents.csv");
//		parentData.show();
		
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
		
		spark.sqlContext().udf().register("isValidEmail", new UDF1<String, Boolean>(){
			public Boolean call(String email){
				boolean v = emailValidator.validate(email);
				return v;
			}
		}, DataTypes.BooleanType);
		
		//childrenData.createOrReplaceTempView("childrenData");
		//parentData.createOrReplaceTempView("parentData");
		
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
				when(col("accountType").equalTo("DEALER"), "01241000000Qkuv")  // dealer
				.when(col("accountType").equalTo("INACTIVE"), "INACTIVE")
				.otherwise("01241000000k3qx");
		
		Column calculatedCSTier = 
				 when(col("accountType").equalTo("DEALER"), lit("Dealer"))
				.when(col("accountType").equalTo("ACTIVE"), lit("None"))
				.when(col("accountType").equalTo("ANA"), lit("None"))
				.when(col("accountType").equalTo("ASSOC"), lit("Associate"))
				.when(col("accountType").equalTo("CANCEL"), lit("None"))
				.when(col("accountType").equalTo("CHARTER"), lit("None"))
				.when(col("accountType").equalTo("ELITE"), lit("Elite"))
				.when(col("accountType").equalTo("FREE"), lit("Free"))
				.when(col("accountType").equalTo("PREM"), lit("Premium"))
				.when(col("accountType").equalTo("SUB"), lit("None"))
				.when(col("accountType").equalTo("UNKNOWN"), lit("None"))
				.otherwise(lit("None"));
		
		Column emailC = when(callUDF("isValidEmail", col("contact_email")), col("contact_email")).otherwise(lit("email@unkown.com")); 
		
		Column cityName = when(length(col("contact_address_city")).gt(40), substring(col("contact_address_city"), 0, 39)).otherwise(col("contact_address_city"));
		
		Column accountName = when(isnull(col("contact_name")), lit("NAME MISSING")).otherwise(col("contact_name"));
		
		Column countryTitleCased = when(not(isnull(col("contact_address_country"))).and(length(col("contact_address_country")).gt(2)), callUDF("titleCase", col("contact_address_country")) ).otherwise(col("contact_address_country"));
		
		Column imagePreferences = when(col("pref_imaging").equalTo("1"), lit("Low Resolution")).otherwise("None");
		
		childrenData = childrenData.withColumn("contact_email", emailC);
		childrenData = childrenData.withColumn("contact_address_city", cityName);
		childrenData = childrenData.withColumn("contact_name", accountName);
		childrenData = childrenData.withColumn("contact_address_country", countryTitleCased);
		childrenData = childrenData.withColumn("pref_imaging", imagePreferences);
		
		childrenData = childrenData.withColumn("composite_street", rtrim(concat_ws(" ", col("contact_address_address1"), col("contact_address_address2"), col("contact_address_address3"))));
		childrenData = childrenData.withColumn("composite_notes", rtrim(concat_ws(" ", col("notes_note1"), col("notes_note2"))));
		childrenData = childrenData.withColumn("record_type_id", recordTypeSFId);
		childrenData = childrenData.withColumn("CalculatedCSTier", calculatedCSTier);
		
		childrenData.show();
		
		Dataset<Row> mutatedChildrenData = childrenData;
		mutatedChildrenData.createOrReplaceTempView("mutatedChildrenData");
		
		Dataset<Row> joinedData = spark.sql("select a.* from mutatedChildrenData as a where a.accountType <> 'INACTIVE' order by a.company");
		
		/*
		Dataset<Row> joinedData = spark.sql("select a.*, b._c0 as parent_id from mutatedChildrenData as a left join parentData as b on a._c3 = getCustomerCode(b._c0) where a._c4 <> 'INACTIVE' "
				+ "and (a._c2 = 'NGC' or (a._c2 = 'NCS' or a._c2 = 'PMG')) order by a._c2");
		
		joinedData.show();
		*/
		joinedData.write().option("header", "true").csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_children_processed_all.csv");
		
		spark.stop();
	}
	

}
