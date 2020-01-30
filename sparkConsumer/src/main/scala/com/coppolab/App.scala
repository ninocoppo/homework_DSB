package com.coppolab
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable.ListBuffer
import scala.util.parsing.json.JSON



/**
 * Hello world!
 *
 */
object App {
  def main(args: Array[String]): Unit = {
    // Create context with 30 second batch interval
    val conf = new SparkConf().setAppName("spark-kafka").setMaster("local[2]").set("spark.executor.memory","1g");
    val ssc = new StreamingContext(conf, Seconds(5))
    //Env variable from kubernetes deployment yaml
    val URL = sys.env("URL");
    println("URL :"+URL)


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

    val list_GET  = ListBuffer[Double]()
    val list_POST  = ListBuffer[Double]()
    val mc = new MetricsContext()
    //val avgClass_1 = new AvgClass()

    lines.foreachRDD { rdd => val RDD = rdd.collect().map(_.toString).mkString(",")
      val my_stream = RDD.split("]},")
      println("DIMENSIONE RDD = "+my_stream.size)
      //repair Json String
      for (w <- 0 until my_stream.size-1){
        my_stream(w)=my_stream(w)+"]}"
      }
      for (w <- 0 until my_stream.size) {
        println("Elemento: " + my_stream(w))
      }

      val avgClass = new AvgClass()


      println("DENTRO IL CICLO RDD NORMAL :" + RDD) // prints to the stdout of the driver
      println("DIMENSIONE ="+my_stream.size)

      for(w <- 0 until my_stream.size) {
        val parsed = JSON.parseFull(my_stream.apply(w))
        try {
          val jsonString = parsed.get.asInstanceOf[Map[String, Any]]
          val request_type = jsonString.get("http_method").get.asInstanceOf[String]
          val value = jsonString.get("value").get.asInstanceOf[List[String]].apply(1)
          mc.setValue(value.toDouble)
          mc.setRequest_type(request_type)
          if (mc.getRequest_type.equals("POST")||mc.getRequest_type.equals("DELETE")||mc.getRequest_type.equals("PUT")) {
            avgClass.setCounter_POST(avgClass.getCounter_POST+1)
            avgClass.setTime_POST(avgClass.getTime_POST+mc.getValue)
          }
          if (mc.getRequest_type.equals("GET")) {
            avgClass.setCounter_GET(avgClass.getCounter_GET+1)
            avgClass.setTime_GET(avgClass.getTime_GET+mc.getValue)

          }
          println("\n\nRequest TYPE " + mc.getRequest_type + "\n\n\n")
          println("\n\nVALUE: " + mc.getValue + "\n\n\n")


        } catch {
          case exception: Exception => {
            println(exception)
          }
        }

        avgClass.setAvg_GET(avgClass.getTime_GET/avgClass.getCounter_GET);
        avgClass.setAvg_POST(avgClass.getTime_POST/avgClass.getCounter_POST)

        }
      try {
        println("PROVA CONTATORE RICHIESTE POST BATCH CORRENTE: " + avgClass.getCounter_POST)
        println("MEDIA POST BATCH CORRENTE: " + avgClass.getAvg_POST);
        println("PROVA CONTATORE RICHIESTE GET BATCH CORRENTE: " + avgClass.getCounter_GET)
        println("MEDIA GET BATCH CORRENTE: " + avgClass.getAvg_GET+"\n\n\n\n");
        //println("\n\nCounter dentro :"+counter)
        if (!(avgClass.getAvg_GET.isNaN)) {
          for(w <- 0 until list_GET.size){
            if(avgClass.getAvg_GET>((list_GET.apply(w)*20/100)+list_GET.apply(w))){
              println("MANDO ALERTTTTTTTTTTTTTT");
               val em = new EmailContext()
            }
          }
          if(list_GET.size==5){
            list_GET.remove(0)
          }
          list_GET.append(avgClass.getAvg_GET)

        }
        //((list_POST.apply(w)*20/100)+
        if (!(avgClass.getAvg_POST==0.0)) {
          for(w <- 0 until list_POST.size){
            if(avgClass.getAvg_POST>list_POST.apply(w)){
              println("MANDO ALERTTTTTTTTTTTTTT");
              val em = new EmailContext()
            }
          }
          if(list_POST.size==5){
            list_POST.remove(0)
          }
          list_POST.append(avgClass.getAvg_POST)


        }
      }catch{
        case exception: Exception => {
          println(exception)
        }
      }
      println("LISTA MEDIE POST"+list_POST)
      println("LISTA MEDIE GET"+list_GET)
      println("Dimensione lista GET : "+list_GET.size)
      println("Dimensione lista POST : "+list_POST.size)
    }

    //println("\n\nCounter fuori :"+counter)

    // Start the computation

    ssc.start()
    ssc.awaitTermination()


  }
}
