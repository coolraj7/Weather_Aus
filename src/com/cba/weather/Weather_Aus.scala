package com.cba.weather

import org.json4s._
import org.json4s.jackson.Serialization.{ read, write }
import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.rdd._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.flume._
import scala.collection.immutable.StringOps

case class RSSItem(category: String, title: String, summary: String)

object Weather_Aus {

  def main(args: Array[String]) = {
    val conf = new SparkConf().setAppName("Weather_AUs")
    val ssc = new StreamingContext(conf, Seconds(30))

    val flumeStream = FlumeUtils.createStream(ssc, "192.168.197.148", 44444)

    val sc = new SparkContext()
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val hqlContext = new org.apache.spark.sql.hive.HiveContext(sc)
    val JsonData = sqlContext.jsonFile("/CBA_Weather/DATA_Weather")
    val CityCode = sc.textFile("/CBA_Weather/City_Codes")
    JsonData.registerTempTable("jt")
    hqlContext.sql("CREATE EXTERNAL TABLE IF NOT EXISTS  Code_AUS (city string, code string) ROW FORMAT DELIMITED fields terminated by '\t' lines terminated by '\n' STORED AS TextFile")
    hqlContext.sql("LOAD DATA INPATH '/CBA_Weather/Codes.csv' OVERWRITE INTO TABLE Code")
    val city = hqlContext.sql("SELECT * from Code_AUS").collect().foreach(println)
    val op = sqlContext.sql("select list.name, list.coord.lon, list.coord.lat, list.dt, list.weather, list.main.temp, list.main.pressure, list.main.humidity from jt").first()
    for (i <- 0 to op.length - 1) {
     var result = op.getList(0).get(i).toString() + "|" + op.getList(1).get(i) + "|" + op.getList(2).get(i) + "|" + op.getList(3).get(i)+ "|" + op.getList(4).get(i)+ "|" + op.getList(5).get(i)+ "|" + op.getList(6).get(i)++ "|" + op.getList(7).get(i)
      println(result) 
    }
    ssc.start()
    ssc.awaitTermination()
  }
}