package com.ngc.transformers.accounts;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

import org.apache.spark.sql.Column;

public class ParentAccountsTransformer {

	public static void main(String[] args){
		
		SparkSession spark = SparkSession
				.builder()
				.appName("Spark SQL Examples")
				.master("local")
				.getOrCreate();
		
		Dataset<Row> data = spark.read().csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_bulk_parents.csv");
		data.show();
		
		// Add new column
		//data = data.withColumn("composite_id", concat_ws("_", col("_c2"), col("_c3")));
		data = data. withColumn("composite_id", concat_ws("", lit("CCG_"), col("_c3"))); // CCG is for parent accounts
//		data = data. withColumn("composite_street", concat_ws(" ", col("_c49"), col("_c50"), col("_c51")));
//		data = data. withColumn("composite_notes", concat_ws(" ", col("_c56"), col("_c57")));
//		
//		Column newCol = when(col("_c2").equalTo("CCG"), "X")
//		.when(col("C").equalTo("B"), "Y")
//		.otherwise("Z");
//		
		data.show();
		data.write().csv("C:\\Users\\Alien\\Downloads\\Accounts\\bulk_loading\\accounts_bulk_children_processed.csv");
		
		// Replace column value 
//		Column newCol = when(col("C").equalTo("A"), "X")
//				.when(col("C").equalTo("B"), "Y")
//				.otherwise("Z");
//		DataFrame df = df1.withColumn("C", newCol);
				
		
		// Group By
		// Dataset<Row> companyDS = data.groupBy("_c2").count();
		// companyDS.show();
		
//		Dataset<Row> parentAccounts = data
//				.filter(data.col("_c4").equalTo("DEALER"))
//				.groupBy("_c3")
//				.count(); 
		
		// Filter NGCs only end export
        /* 
         * Dataset<Row> ngcAccountsOnly = data.filter(data.col("_c2").eqNullSafe("NGC"));
         * ngcAccountsOnly.show();
         * ngcAccountsOnly.write().csv("C:\\Users\\Alien\\Downloads\\Accounts\\accounts_ngc.csv");
		 */

		
		
		spark.stop();
	}
	
}
