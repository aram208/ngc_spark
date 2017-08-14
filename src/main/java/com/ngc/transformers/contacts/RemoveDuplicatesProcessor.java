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
		
		Dataset<Row> childrenData = spark.read().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Contacts\\bulk_loading\\contacts_all_small.csv");
		childrenData.show();
	
		childrenData.createOrReplaceTempView("childrenData");
		
		// Group the contacts by company, code and email 
		
		// couch_id,couch_rev,company,customerCode,sequence,contactType,name,firstName,lastName,phone,fax,dear,title,phoneExt,
		// email,zip,city,state,address1,address2,address3,country,internatonal,primary
		
		// THIS WAS WRONG THING TO DO!!!!!!!!!
		
		Dataset<Row> joinedData = spark.sql("select first(a.couch_id), first(a.couch_rev),first(a.company),first(a.customerCode),first(a.sequence),"
				+ "first(a.contactType),first(a.name),first(a.firstName),first(a.lastName),first(a.phone),"
				+ "first(a.fax),first(a.dear),first(a.title),first(a.phoneExt),first(a.email),first(a.zip),first(a.city),first(a.state),first(a.address1),"
				+ "first(a.address2),first(a.address3),first(a.country),first(a.international),first(a.primary) "
				+ "from childrenData as a group by a.company, a.customerCode, a.email");
		
		
		joinedData.show();
		joinedData.write().option("header", true).csv("C:\\Users\\Alien\\Downloads\\Contacts\\bulk_loading\\contacts_all_filtered.csv");
		
		spark.stop();
	}
	
}
