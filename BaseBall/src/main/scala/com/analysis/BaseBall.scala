package com.analysis

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession


// Believe it or not,
// YOU HAVE TO MANUALLY CREATE main/scala FOLDER STRUCTURE TO ADD PACKAGE AND CODE
// And this has to be object to run it
object BaseBall extends App {

  override def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val spark = SparkSession.builder().appName("BaseBall").master("local[*]").getOrCreate()
    val sc = spark.sparkContext
    sc.setLogLevel("ERROR")

    val numbers = sc.parallelize(10 to 50 by 10)
    println(numbers)  // It's an RDD
    numbers.foreach(number => println(number))
    val squaredNums = numbers.map(number => number*number)
    squaredNums.foreach(n => print(s"$n "))

    println("\n---------------------------------------------------------")

    sc.stop()
    spark.stop()
  }

}
