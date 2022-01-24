import org.apache.spark.sql.SparkSession

import scala.xml.XML

object Kpi3_QuestionsWithTitle {

  def main(args:Array[String]): Unit ={
    val spark=SparkSession.builder().master("local").appName("QuestionswithTitle").getOrCreate()
    val data=spark.read.textFile("C:\\Users\\MayuriPatole\\Downloads\\Posts.xml").rdd

    val posts=data.filter(line=>line.trim.startsWith("<row")).filter(line=>line.contains("PostTypeId=\"1\""))
      .flatMap(line=>{
    val xml=XML.loadString(line)
    xml.attribute("Title")
      })
      .filter{
      title=>{
        (title.mkString.toLowerCase().contains("data")||title.mkString.toLowerCase().contains("science")||title.mkString.toLowerCase().contains("nosql")||title.mkString.toLowerCase().contains("hadoop")||title.mkString.contains("spark"))
      }
    }

    posts.foreach(println)
println(posts.count())
spark.stop()
  }
}
