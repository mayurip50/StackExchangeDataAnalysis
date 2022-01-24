import org.apache.spark.sql.SparkSession

import java.text.SimpleDateFormat
import scala.xml.XML

object Kpi2_MonthlyQuestions {

  def main(args:Array[String]): Unit ={
    val format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    val format2=new SimpleDateFormat("yyyy-MM")

    val spark=SparkSession.builder().master("local").appName("Monthly Questions").getOrCreate();
   val data= spark.read.textFile("C:\\Users\\MayuriPatole\\Downloads\\Posts.xml").rdd
    val posts=data.filter(line=>line.contains("PostTypeId=\"1\"")).map{
      line=>{
        val xml=XML.loadString(line)
        var date="";
        if(xml.attribute("CreationDate")!=None){
val creationDate=xml.attribute("CreationDate").get.toString()
    date=format2.format(format.parse(creationDate))
        }
      //  (date,line)
        (date,1)
      }
    }.filter(data=>data._1.length>0).reduceByKey(_+_)

    posts.foreach(println)
    spark.stop()




  }
}
