import org.apache.spark.sql.SparkSession

import java.text.SimpleDateFormat
import scala.xml.XML

object Kpi12_AvgTimeToGetAnswer {
  def main(args:Array[String]) {
    val format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")

    val spark = SparkSession.builder().master("local").appName("Avg time to get answer").getOrCreate()
    val data = spark.read.textFile("C:\\Users\\MayuriPatole\\Downloads\\Posts1.xml").rdd

    val posts = data.filter(line => line.trim.startsWith("<row"))
      .map{ line => {
       val xml=XML.loadString(line)
        var aid = ""
        if(xml.attribute("AcceptedAnswerId")!=None){
          aid=xml.attribute("AcceptedAnswerId").get.toString()
        }
        val creationDate=xml.attribute("CreationDate").get.toString()
        val id=xml.attribute("Id").get.toString()
        (id,aid,creationDate)
      }}


val aadata=posts.map(rec=>(rec._2,rec._3)).filter(data=>data._1.length>0)
val radata=posts.map(rec=>(rec._1,rec._3))



val joindata=radata.join(aadata)
        .map(rec=>{
          val qdate=format.parse(rec._2._2).getTime
           val adate=format.parse(rec._2._1).getTime
           val diff:Float=adate-qdate
        val time=diff/(1000*60*60)
            time
        })

        val count=joindata.count()
            val result=joindata.sum()/count
        println(result)

    //joindata.foreach(println)
println(result)
    spark.stop()
  }
}
