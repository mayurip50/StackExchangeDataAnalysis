import org.apache.spark.sql.SparkSession

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.xml.XML

object Kpi8_QuestionsMarkedClosed {
  def main(args:Array[String]): Unit ={
    val spark=SparkSession.builder().master("local").appName("Questions marked as closed").getOrCreate()
    val data=spark.read.textFile("C:\\Users\\MayuriPatole\\Downloads\\Posts.xml").rdd
    val format2 = new SimpleDateFormat("yyyy-MM");
    val format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
   // var count=0
    val posts=data.filter(line=>line.trim.startsWith("<row"))
      .filter(line=>line.contains("PostTypeId=\"1\""))
      .map(line=>{
       val xml= XML.loadString(line)
        var Ldate=""
if(xml.attribute("ClosedDate")!=None){
  var date=xml.attribute("ClosedDate").get.toString()
   Ldate=format2.format(format.parse(date))
 // count=count+1
}
        (Ldate,1)

      }).filter(data=>data._1.length>0).reduceByKey(_+_).sortByKey()


    posts.foreach(println)
   // println(posts.count())
    spark.stop()
  }

}
