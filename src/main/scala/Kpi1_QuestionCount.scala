import org.apache.spark.sql.SparkSession

object Kpi1_QuestionCount {

  def main( args:Array[String]): Unit ={

    //System.setProperty()
    val spark=SparkSession.builder().master("local").appName("stack data").getOrCreate()
//    val data=spark.read.textFile("/home/mayuri/spark/stack_analysis/Posts.xml").rdd
val data=spark.read.textFile("C:\\Users\\MayuriPatole\\Downloads\\Posts.xml").rdd
    val posts=data.filter(line=>line.trim.startsWith("<row")).filter(line=>line.contains("PostTypeId=\"1\""))
posts.foreach(println)

println("Total count: "+posts.count())
spark.stop()

  }
}
