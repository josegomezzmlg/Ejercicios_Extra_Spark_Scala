package model
import functions.functions.{cleanConcert, loadConcert}
import variable.variable._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Concert {
  def execute(spark: SparkSession,inputPath: String): Unit = {

    val pathConciertos = s"$inputPath/$conciertos"

    val dfConcierto = loadConcert(spark,pathConciertos)
    val dfConcertclean = cleanConcert(dfConcierto)

    println("Informacion ordenada por Rank de manera ascendente")
    dfConcertclean.orderBy(asc("Rank")).show()

    println("Información ordenada por Total actual gross de manera descendente")
    dfConcertclean.orderBy(desc("Total actual gross")).show()
  }
}
