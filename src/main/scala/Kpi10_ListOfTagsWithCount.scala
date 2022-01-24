import org.apache.spark.sql.SparkSession

import scala.xml.XML

object Kpi10_ListOfTagsWithCount {
  def main(args:Array[String]) {

    val spark = SparkSession.builder().master("local").appName("TagsWithCount").getOrCreate()
    val data = spark.read.textFile("C:\\Users\\MayuriPatole\\Downloads\\Posts.xml").rdd

    val posts = data.filter(line => line.trim.startsWith("<row"))
                    .filter(line => line.contains("PostTypeId=\"1\""))
                    .map{ line => {XML.loadString(line).attribute("Tags").toString}}
                    .flatMap(data=>{data.replaceAll("&lt;"," ").replaceAll("&gt"," ").split(" ")})
                    .filter(tag=>tag.length>0)
                    .map(data=>(data,1)).reduceByKey(_+_).sortByKey(true)

    posts.foreach(println)
    spark.stop()
  }
  }
