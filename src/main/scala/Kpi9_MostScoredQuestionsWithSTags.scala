import org.apache.spark.sql.SparkSession

import scala.xml.XML

object Kpi9_MostScoredQuestionsWithSTags {
 def main(args:Array[String]){

  val spark = SparkSession.builder().master("local").appName("MostScoredQuestions").getOrCreate()
  val data = spark.read.textFile("C:\\Users\\MayuriPatole\\Downloads\\Posts.xml").rdd

  val posts = data.filter(line => line.trim.startsWith("<row"))
    .filter(line => line.contains("PostTypeId=\"1\""))
    .filter{
     line=>{
       val tags= XML.loadString(line).attribute("Tags").toString
       tags.toLowerCase.contains("spark")&&tags.toLowerCase.contains("hadoop")
     }}
    .map { rec => {
      val xml = XML.loadString(rec)
      val score= Integer.parseInt(xml.attribute("Score").get.toString())
      (score,xml.attribute("Title").get.toString(),xml.attribute("Tags").get.toString())
    }
    }.sortBy(x => x._1, false).take(10)
  posts.foreach(println)
   spark.stop()
 }
}
