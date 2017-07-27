package com.ngc.transformers.contacts;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;

import com.ngc.transformers.utils.EmailValidator;

import static org.apache.spark.sql.functions.*;

import org.apache.spark.sql.Column;

public class ContactProcessor {

	@SuppressWarnings("serial")
	public static void main(String[] args){
		
		EmailValidator emailValidator = EmailValidator.INSTANCE;
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark Contacts processor")
				.master("local")
				.getOrCreate();
		
		spark.sqlContext().udf().register("isValidEmail", new UDF1<String, Boolean>(){
			public Boolean call(String email){
				boolean v = emailValidator.validate(email);
				return v;
			}
		}, DataTypes.BooleanType);
		
		Dataset<Row> childrenData = spark.read().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Contacts\\bulk_loading\\contacts_test_filtered.csv");
		//childrenData.show();
		
		Dataset<Row> parentAccounts = spark.read().csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_bulk_all.csv");
		//parentAccounts.show();
		
		Column parentAccountID = concat_ws("_", col("company"), col("customerCode"));
		Column compositeStreetAddress = concat_ws(" ", col("address1"), col("address2"), col("address3"));
		Column contactType = when(col("contactType").equalTo("M"), lit("MC")).when(col("contactType").equalTo("S"), lit("SH")).otherwise(col("contactType"));
		Column emailAddress = when(callUDF("isValidEmail", col("emailAddress")), col("emailAddress")).otherwise(lit("email@unkown.com"));
		
		Column recapMC = when(col("contactType").equalTo("M").or(col("contactType").equalTo("MC")), lit("1")).otherwise(lit("0"));
		Column recapSH = when(col("contactType").equalTo("S").or(col("contactType").equalTo("SH")), lit("1")).otherwise(lit("0"));
		Column recapAC = when(col("contactType").equalTo("AC"), lit("1")).otherwise(lit("0"));
		Column recapGR = when(col("contactType").equalTo("GR"), lit("1")).otherwise(lit("0"));
		Column recapRC = when(col("contactType").equalTo("RC"), lit("1")).otherwise(lit("0"));
		
		Column recapMCCouchID = when(col("contactType").equalTo("M").or(col("contactType").equalTo("MC")), col("_id")).otherwise(lit(""));
		Column recapSHCouchID = when(col("contactType").equalTo("S").or(col("contactType").equalTo("SH")), col("_id")).otherwise(lit(""));
		Column recapRCCouchID = when(col("contactType").equalTo("RC"), col("_id")).otherwise(lit(""));
		Column recapACCouchID = when(col("contactType").equalTo("AC"), col("_id")).otherwise(lit(""));
		Column recapGRCouchID = when(col("contactType").equalTo("GR"), col("_id")).otherwise(lit(""));
		
		Column recapMCCouchRV = when(col("contactType").equalTo("M").or(col("contactType").equalTo("MC")), col("_rev")).otherwise(lit(""));
		Column recapSHCouchRV = when(col("contactType").equalTo("S").or(col("contactType").equalTo("SH")), col("_rev")).otherwise(lit(""));
		Column recapRCCouchRV = when(col("contactType").equalTo("RC"), col("_rev")).otherwise(lit(""));
		Column recapACCouchRV = when(col("contactType").equalTo("AC"), col("_rev")).otherwise(lit(""));
		Column recapGRCouchRV = when(col("contactType").equalTo("GR"), col("_rev")).otherwise(lit(""));
		
		childrenData = childrenData.withColumn("recapMC", recapMC);
		childrenData = childrenData.withColumn("recapSH", recapSH);
		childrenData = childrenData.withColumn("recapAC", recapAC);
		childrenData = childrenData.withColumn("recapGR", recapGR);
		childrenData = childrenData.withColumn("recapRC", recapRC);
		
		childrenData = childrenData.withColumn("recapMCCouchID", recapMCCouchID);
		childrenData = childrenData.withColumn("recapMCCouchRV", recapMCCouchRV);
		
		childrenData = childrenData.withColumn("recapSHCouchID", recapSHCouchID);
		childrenData = childrenData.withColumn("recapSHCouchRV", recapSHCouchRV);
		
		childrenData = childrenData.withColumn("recapRCCouchID", recapRCCouchID);
		childrenData = childrenData.withColumn("recapRCCouchRV", recapRCCouchRV);
		
		childrenData = childrenData.withColumn("recapACCouchID", recapACCouchID);
		childrenData = childrenData.withColumn("recapACCouchRV", recapACCouchRV);
		
		childrenData = childrenData.withColumn("recapGRCouchID", recapGRCouchID);
		childrenData = childrenData.withColumn("recapGRCouchRV", recapGRCouchRV);
		
		childrenData = childrenData.withColumn("composite_street", compositeStreetAddress);
		childrenData = childrenData.withColumn("parentAccountID", parentAccountID);
		childrenData = childrenData.withColumn("contactType", contactType);
		childrenData = childrenData.withColumn("emailAddress", emailAddress);
		childrenData.show();
		
		Dataset<Row> mutatedChildrenData = childrenData;
		mutatedChildrenData.createOrReplaceTempView("mutatedChildrenData");
		parentAccounts.createOrReplaceTempView("parentAccounts");
		
		Dataset<Row> joinedData = spark.sql("select a.* from mutatedChildrenData as a inner join parentAccounts as b on a.parentAccountID = b._c0");
		joinedData.show();
		joinedData.write().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Contacts\\bulk_loading\\contacts_test_filtered_processed.csv");
		
		spark.stop();
	}
	
}
