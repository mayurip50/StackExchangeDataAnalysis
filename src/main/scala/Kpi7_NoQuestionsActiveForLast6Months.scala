import org.apache.spark.sql.SparkSession

import java.text.SimpleDateFormat
import scala.xml.XML
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter;
object Kpi7_NoQuestionsActiveForLast6Months {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").appName("Active Questions").getOrCreate()
    val data = spark.read.textFile("C:\\Users\\MayuriPatole\\Downloads\\Posts.xml").rdd
    val format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    val posts = data.filter(line => line.trim.startsWith("<row"))
      .filter(line => line.contains("PostTypeId=\"1\""))
      .map(line => {
        val xml=XML.loadString(line)
        (xml.attribute("LastActivityDate").get,xml.attribute("CreationDate").get,line)
      }).map(rec => {
            val cdate=format.parse(rec._2.text)
            val ctime=cdate.getTime
            val edate=format.parse(rec._1.text)
            val etime=edate.getTime

            val timediff:Long=etime-ctime
            (cdate,edate,timediff,rec._3)

    })
     .filter(x=>{ x._3 / (1000 * 60 * 60 * 24 ) > 30*6 })


    posts.foreach(println)
    println(posts.count())
    spark.stop()
  }
}