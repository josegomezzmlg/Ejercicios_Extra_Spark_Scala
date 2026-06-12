import model.{Concert, Pokemon}
import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Modulo6")
      .master("local[*]")
      .getOrCreate()

    val inputPath = args(0)

    spark.sparkContext.setLogLevel("ERROR")

    println("- - - Reto Big Data - - - - ")
    println("===================================")
    println("Ejercicios Extra Spark Scala")
    println("===================================")
    //Concert.execute(spark,inputPath)
    Pokemon.execute(spark, inputPath)

    spark.stop()
  }

}
