import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe


object App {

  def main(args: Array[String]): Unit = {
    // Create context with 30 second batch interval
    val conf = new SparkConf().setAppName("spark-kafka").setMaster("local[2]").set("spark.executor.memory","1g");
    val ssc = new StreamingContext(conf, Seconds(30))

    /* Configure Kafka */
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test_group",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("Nappa")
    //Create an input stream that directly pulls messages from Kafka Brokers without using any receiver
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent, //Location strategy
      Subscribe[String, String](topics, kafkaParams)
    )

    val lines = stream.map(record => (1,record.value()));

    lines.saveAsTextFiles("tmp");
    //val string = ;
    //println("DIOOOOOOOOOO OOOOOO OOOO"+lines.print());
    // Start the computation
    ssc.start()
    ssc.awaitTermination()


  }

}
