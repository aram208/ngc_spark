package com.spark.examples;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSQLExample {

	public static void main(String[] args){
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark SQL Examples")
				.master("local")
				.getOrCreate();
		
		Dataset<Row> data = spark.read().json("C:\\Projects\\ngc_spark\\JsonToCSV\\src\\main\\resources\\data.json.txt");
		
		data.show();
		
		data.printSchema();
		
		// Get single column
		Dataset<Row> fnameDS = data.select("fname");
		fnameDS.show();
		
		// Get the few columns from the json file.
		Dataset<Row> nameDS = data.select(data.col("fname"), data.col("lname"));
		nameDS.show();
		
		// Group By
		Dataset<Row> genderDS = data.groupBy("gender").count();
		genderDS.show();
		
		// Filters
        Dataset<Row> maleGenederDS = data.filter(data.col("gender").eqNullSafe("Male"));
        maleGenederDS.show();
 
        // Concat two columns
        Dataset<String> fullNameDS = data.map(
        		(MapFunction<Row, String>) row -> row.getAs("fname").toString() + " " + row.getAs("lname").toString(),
                Encoders.STRING());
        fullNameDS.show();
        
 
        // Convert to the Object
        Dataset<Person> peopleDS = data.as(Encoders.bean(Person.class));
        peopleDS.show();
		
		spark.stop();
	}
	
}
