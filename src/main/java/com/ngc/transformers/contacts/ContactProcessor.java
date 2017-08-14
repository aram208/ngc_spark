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
		
		Dataset<Row> childrenData = spark.read().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Contacts\\bulk_loading\\contacts_all_small.csv");
		childrenData.show();
		
		Dataset<Row> parentAccounts = spark.read().csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_bulk_all.csv");
		//parentAccounts.show();
		
		Column parentAccountID = concat_ws("_", col("company"), col("customerCode"));
		Column futureCouchID = concat_ws("_", col("company"), col("customerCode"));
		Column compositeStreetAddress = concat_ws(" ", col("address1"), col("address2"), col("address3"));
		Column contactType = when(col("contactType").equalTo("M"), lit("MC")).when(col("contactType").equalTo("S"), lit("SH")).otherwise(col("contactType"));
		Column emailAddress = when(callUDF("isValidEmail", col("email")), col("email")).otherwise(lit("email@unkown.com"));
		
		Column recapMC = when(col("contactType").equalTo("M").or(col("contactType").equalTo("MC")), lit("1")).otherwise(lit("0"));
		Column recapSH = when(col("contactType").equalTo("S").or(col("contactType").equalTo("SH")), lit("1")).otherwise(lit("0"));
		Column recapAC = when(col("contactType").equalTo("AC"), lit("1")).otherwise(lit("0"));
		Column recapGR = when(col("contactType").equalTo("GR"), lit("1")).otherwise(lit("0"));
		Column recapRC = when(col("contactType").equalTo("RC"), lit("1")).otherwise(lit("0"));
		
		Column recapMCCouchID = when(col("contactType").equalTo("M").or(col("contactType").equalTo("MC")), col("couch_id")).otherwise(lit(""));
		Column recapSHCouchID = when(col("contactType").equalTo("S").or(col("contactType").equalTo("SH")), col("couch_id")).otherwise(lit(""));
		Column recapRCCouchID = when(col("contactType").equalTo("RC"), col("couch_id")).otherwise(lit(""));
		Column recapACCouchID = when(col("contactType").equalTo("AC"), col("couch_id")).otherwise(lit(""));
		Column recapGRCouchID = when(col("contactType").equalTo("GR"), col("couch_id")).otherwise(lit(""));
		
//		Column recapMCCouchRV = when(col("contactType").equalTo("M").or(col("contactType").equalTo("MC")), col("_rev")).otherwise(lit(""));
//		Column recapSHCouchRV = when(col("contactType").equalTo("S").or(col("contactType").equalTo("SH")), col("_rev")).otherwise(lit(""));
//		Column recapRCCouchRV = when(col("contactType").equalTo("RC"), col("_rev")).otherwise(lit(""));
//		Column recapACCouchRV = when(col("contactType").equalTo("AC"), col("_rev")).otherwise(lit(""));
//		Column recapGRCouchRV = when(col("contactType").equalTo("GR"), col("_rev")).otherwise(lit(""));
		
		childrenData = childrenData.withColumn("recapMC", recapMC);
		childrenData = childrenData.withColumn("recapSH", recapSH);
		childrenData = childrenData.withColumn("recapAC", recapAC);
		childrenData = childrenData.withColumn("recapGR", recapGR);
		childrenData = childrenData.withColumn("recapRC", recapRC);
		
		childrenData = childrenData.withColumn("recapMCCouchID", recapMCCouchID);
		//childrenData = childrenData.withColumn("recapMCCouchRV", recapMCCouchRV);
		
		childrenData = childrenData.withColumn("recapSHCouchID", recapSHCouchID);
		//childrenData = childrenData.withColumn("recapSHCouchRV", recapSHCouchRV);
		
		childrenData = childrenData.withColumn("recapRCCouchID", recapRCCouchID);
		//childrenData = childrenData.withColumn("recapRCCouchRV", recapRCCouchRV);
		
		childrenData = childrenData.withColumn("recapACCouchID", recapACCouchID);
		//childrenData = childrenData.withColumn("recapACCouchRV", recapACCouchRV);
		
		childrenData = childrenData.withColumn("recapGRCouchID", recapGRCouchID);
		//childrenData = childrenData.withColumn("recapGRCouchRV", recapGRCouchRV);
		
		childrenData = childrenData.withColumn("composite_street", compositeStreetAddress);
		childrenData = childrenData.withColumn("parentAccountID", parentAccountID);
		childrenData = childrenData.withColumn("futureCouchID", futureCouchID);
		childrenData = childrenData.withColumn("contactType", contactType);
		childrenData = childrenData.withColumn("email", emailAddress);
		childrenData.show();
		
		Dataset<Row> mutatedChildrenData = childrenData;
		mutatedChildrenData.createOrReplaceTempView("mutatedChildrenData");
		/**
		parentAccounts.createOrReplaceTempView("parentAccounts");
		Dataset<Row> joinedData = spark.sql("select a.* from mutatedChildrenData as a inner join parentAccounts as b on a.parentAccountID = b._c0");
		*/
		
		// Min(Case DBColumnName When 'MiddleName' Then Data End) MiddleName,
		/*
		SELECT
		main.CustomerID,
		f.Data AS FirstName,
		m.Data AS MiddleName,
		l.Data AS LastName,
		d.Data AS Date
		FROM table main
		INNER JOIN table f on f.CustomerID = main.CustomerID
		INNER JOIN table m on m.CustomerID = main.CustomerID
		INNER JOIN table l on l.CustomerID = main.CustomerID
		INNER JOIN table d on d.CustomerID = main.CustomerID
		WHERE f.DBColumnName = 'FirstName' 
		AND m.DBColumnName = 'MiddleName' 
		AND l.DBColumnName = 'LastName' 
		AND d.DBColumnName = 'Date' 
		*/
		/**
		   Select CustomerID,
		     Min(Case DBColumnName When 'FirstName' Then Data End) FirstName,
		     Min(Case DBColumnName When 'MiddleName' Then Data End) MiddleName,
		     Min(Case DBColumnName When 'LastName' Then Data End) LastName,
		     Min(Case DBColumnName When 'Date' Then Data End) Date
		   From table
		   Group By CustomerId
		   */
		
		Dataset<Row> joinedData = spark.sql("select a.company, a.customerCode, first(a.name) as name, "
				+ "max(case when a.recapRCCouchID is not null then a.recapRCCouchID else 0 end) couch_id_RC, "
				+ "max(case when a.recapACCouchID is not null then a.recapACCouchID else 0 end) couch_id_AC, "
				+ "max(case when a.recapSHCouchID is not null then a.recapSHCouchID else 0 end) couch_id_SH, "
				+ "max(case when a.recapGRCouchID is not null then a.recapGRCouchID else 0 end) couch_id_GR, "
				+ "max(case when a.recapRC = 1 then 1 else 0 end) recapRC, "
				+ "max(case when a.recapAC = 1 then 1 else 0 end) recapAC, "
				+ "max(case when a.recapSH = 1 then 1 else 0 end) recapSH, "
				+ "max(case when a.recapGR = 1 then 1 else 0 end) recapGR "
				+ "from mutatedChildrenData as a group by a.company, a.customerCode");
		joinedData.show();
		//joinedData.write().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Contacts\\bulk_loading\\contacts_all_processed_small.csv");
		
		spark.stop();
	}
	
}
