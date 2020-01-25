package com.coppolab


import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Hello world!
 *
 */
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

    val topics = Array("Metrics")
    //Create an input stream that directly pulls messages from Kafka Brokers without using any receiver
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent, //Location strategy
      Subscribe[String, String](topics, kafkaParams)
    )
  /*
    val lines = stream.map(_.value());
    val string = lines.flatMap(line => line.split(",")).toString

    val string1 = """{"status":"success","data":{"resultType":"vector","result":[{"metric":{"Routed_uri":"null/record/check/4","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"Accepted","response":"202 ACCEPTED"},"value":[1579100617.399,"0.450710869"]},{"metric":{"Routed_uri":"null/record/put","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"OK","response":"200 OK"},"value":[1579100617.399,"0.332547022"]},{"metric":{"Routed_uri":"null/record/put","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"Unauthorized","response":"401 UNAUTHORIZED"},"value":[1579100617.399,"0.526352693"]},{"metric":{"Routed_uri":"null/user/register","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"OK","response":"200 OK"},"value":[1579100617.399,"0"]}]}}"""

    val string2 = """(1,<200,{"status":"success","data":{"resultType":"vector","result":[{"metric":{"Routed_uri":"null/record/check/4","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"Accepted","response":"202 ACCEPTED"},"value":[1579100617.399,"0.450710869"]},{"metric":{"Routed_uri":"null/record/put","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"OK","response":"200 OK"},"value":[1579100617.399,"0.332547022"]},{"metric":{"Routed_uri":"null/record/put","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"Unauthorized","response":"401 UNAUTHORIZED"},"value":[1579100617.399,"0.526352693"]},{"metric":{"Routed_uri":"null/user/register","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"OK","response":"200 OK"},"value":[1579100617.399,"0"]}]}},[Content-Type:"application/json", Date:"Wed, 15 Jan 2020 15:03:37 GMT", Content-Length:"843"]>)
                    |(1,<200,{"status":"success","data":{"resultType":"vector","result":[{"metric":{"Routed_uri":"null/record/check/4","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"Accepted","response":"202 ACCEPTED"},"value":[1579100627.407,"0.450710869"]},{"metric":{"Routed_uri":"null/record/put","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"OK","response":"200 OK"},"value":[1579100627.407,"0.332547022"]},{"metric":{"Routed_uri":"null/record/put","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"Unauthorized","response":"401 UNAUTHORIZED"},"value":[1579100627.407,"0.526352693"]},{"metric":{"Routed_uri":"null/user/register","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"OK","response":"200 OK"},"value":[1579100627.407,"0"]}]}},[Content-Type:"application/json", Date:"Wed, 15 Jan 2020 15:03:47 GMT", Content-Length:"843"]>)
                    |(1,<200,{"status":"success","data":{"resultType":"vector","result":[{"metric":{"Routed_uri":"null/record/check/4","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"Accepted","response":"202 ACCEPTED"},"value":[1579100637.412,"0.450710869"]},{"metric":{"Routed_uri":"null/record/put","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"OK","response":"200 OK"},"value":[1579100637.412,"0"]},{"metric":{"Routed_uri":"null/record/put","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"Unauthorized","response":"401 UNAUTHORIZED"},"value":[1579100637.412,"0"]},{"metric":{"Routed_uri":"null/user/register","http_method":"POST","instance":"apigateway:8080","job":"api_gateway","outcome":"OK","response":"200 OK"},"value":[1579100637.412,"0"]}]}},[Content-Type:"application/json", Date:"Wed, 15 Jan 2020 15:03:57 GMT", Content-Length:"823"]>)""".stripMargin

    val parsed2 = JSON.parseFull(string2.split("\\[Content-Type").toString)


    println("PASRED2: "+parsed2)

    val parsed = JSON.parseFull(string1).get

    val metric = parsed
      .asInstanceOf[Map[String, Any]]
      .apply("data")
      .asInstanceOf[Map[String, Any]]
      .apply("result")
      .asInstanceOf[List[Map[String, Any]]]
      .apply(1)("metric")
    //.asInstanceOf[Map[String, String]]
    //.apply("http_method")

    val time = parsed.asInstanceOf[Map[String,Any]]
      .apply("data")
      .asInstanceOf[Map[String,Any]]
      .apply("result")
      .asInstanceOf[List[Map[String,Any]]]
      .apply(1)("value")

    println("TIME: "+time)



    println("METRIC: "+metric)
*/


    //string.saveAsTextFiles("./tmp/tmp");

    // Start the computation
    /*
        ssc.start()
        ssc.awaitTermination()

    */
  }
}
