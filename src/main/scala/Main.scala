import model.Ejecutor
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
    Ejecutor.execute(spark,inputPath)

    spark.stop()
  }

}
