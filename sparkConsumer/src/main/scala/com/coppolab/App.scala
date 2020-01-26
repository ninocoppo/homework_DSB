package com.coppolab



import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable.ListBuffer

object App {

  def main(args: Array[String]): Unit = {
    // Create context with 30 second batch interval
    val conf = new SparkConf().setAppName("spark-kafka").setMaster("local[2]").set("spark.executor.memory","1g");
    val ssc = new StreamingContext(conf, Seconds(30))
    //Env variable from kubernetes deployment yaml
    val URL = sys.env("URL");
    println("URL :"+URL)
    /* Configure Kafka */
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> URL,
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test_group",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("metrics")
    //Create an input stream that directly pulls messages from Kafka Brokers without using any receiver

    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent, //Location strategy
      Subscribe[String, String](topics, kafkaParams)
    )


    val lines = stream.map(_.value());

    lines.print()




    val lista = new ListBuffer[String]()
    lines.foreachRDD{ rdd => val allFlights = rdd.collect().map(_.toString).mkString(",")
      println("DENTRO IL CICLO"+allFlights) // prints to the stdout of the driver

      if (allFlights!="") {
        lista.append(allFlights)
      }

    }

    //val dio_benedetto = lines.foreachRDD (rdd =>  (rdd.collect.map(_.toString).mkString(",")))








    //string.saveAsTextFiles("./tmp/tmp");

    // Start the computation

    ssc.start()
    ssc.awaitTermination()


  }
}
