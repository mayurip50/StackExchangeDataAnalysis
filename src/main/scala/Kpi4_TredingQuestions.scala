import org.apache.spark.sql.SparkSession

import scala.xml.XML

object Kpi4_TredingQuestions {

  def main(args:Array[String]): Unit ={
    val spark=SparkSession.builder().master("local").appName("TrendingQuestions").getOrCreate()
    val data=spark.read.textFile("C:\\Users\\MayuriPatole\\Downloads\\Posts.xml").rdd

    val posts= data.filter(line=>line.trim.startsWith("<row"))
      .filter(line=>line.contains("PostTypeId=\"1\""))
      .map{line=>{
        val xml=XML.loadString(line)
        (xml.attribute("Tags").get.toString(),Integer.parseInt(xml.attribute("Score").get.toString()),xml.attribute("Title").get.toString())
        }
        }.sortBy(x=>x._2,false).take(10)
    posts.foreach(println)

    val posts1= data.filter(line=>line.trim.startsWith("<row"))
      .filter(line=>line.contains("PostTypeId=\"1\""))
      .map{line=>{
        val xml=XML.loadString(line)
        (xml.attribute("Tags").get.toString(),Integer.parseInt(xml.attribute("ViewCount").get.toString()),xml.attribute("Title").get.toString())
      }
      }.sortBy(x=>x._2,false).take(10)
    posts1.foreach(println)

    spark.stop()
    }
}
