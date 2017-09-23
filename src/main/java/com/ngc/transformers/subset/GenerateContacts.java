package com.ngc.transformers.subset;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class GenerateContacts {
	
	public static void main(String[] args){
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark SQL Examples")
				.master("local")
				.getOrCreate();
		
		Dataset<Row> accountsData = spark.read().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\subset\\dealers.csv");
		accountsData.show();
		
		Dataset<Row> contactsData = spark.read().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Contacts\\bulk_loading\\contacts_all_ready.csv");
		contactsData.show();

		accountsData.createOrReplaceTempView("accountsData");
		contactsData.createOrReplaceTempView("contactsData");
		
		Dataset<Row> joinedData = spark.sql("select c.* from contactsData as c inner join accountsData as a where "
				+ "c.parentAccountID = a.couch_id");
		
		joinedData.write().option("header", "true").csv("C:\\Users\\Alien\\Downloads\\Contacts\\bulk_loading\\contacts_subset.csv");
		
		spark.stop();
		
	}

}
