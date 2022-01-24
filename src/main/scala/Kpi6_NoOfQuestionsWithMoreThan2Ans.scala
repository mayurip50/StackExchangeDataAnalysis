import org.apache.spark.sql.SparkSession

import scala.xml.XML

object Kpi6_NoOfQuestionsWithMoreThan2Ans {
  def main(args:Array[String]): Unit ={
    val spark=SparkSession.builder().master("local").appName("Questions with no ans").getOrCreate()
    val data=spark.read.textFile("C:\\Users\\MayuriPatole\\Downloads\\Posts.xml").rdd
    val posts=data.filter(line=>line.trim.startsWith("<row"))
      .filter(line=>line.contains("PostTypeId=\"1\""))
      .filter(line=>{
        XML.loadString(line).attribute("AnswerCount").get.toString().toInt>2
      })
    posts.foreach(println)
    println(posts.count())
    spark.stop()
  }

}
