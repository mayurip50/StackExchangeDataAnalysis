import org.apache.spark.sql.SparkSession

import java.text.SimpleDateFormat
import scala.xml.XML
import java.time.LocalDateTime

object Kpi11_QuestionsAskedInRangeOfDate {
  def main(args: Array[String]) {

    val spark = SparkSession.builder().master("local").appName("QuestionsAskedInRange").getOrCreate()
    val data = spark.read.textFile("C:\\Users\\MayuriPatole\\Downloads\\Posts.xml").rdd

    val format2 = new SimpleDateFormat("yyyy-MM-dd");
    val format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    val start = format2.parse("2015-01-01").getTime
    val end = format2.parse("2015-12-31").getTime
    val posts = data.filter(line => line.trim.startsWith("<row"))
      .filter(line => line.contains("PostTypeId=\"1\""))
      .filter { line => {
        val xml = XML.loadString(line)
        val tag = xml.attribute("Tags").toString
        val cdate = format1.parse(xml.attribute("CreationDate").get.toString()).getTime
        (cdate >start && cdate < end) && (tag.toLowerCase().contains("nosql") || tag.toLowerCase().contains("bigdata"))
      }
      }


    posts.foreach(println)
    spark.stop()
  }
}