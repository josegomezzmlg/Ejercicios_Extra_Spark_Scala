package model
import functions.functions.cleanConcert
import variable.variable._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Ejecutor {
  def execute(spark: SparkSession,inputPath: String): Unit = {

    val pathConciertos = s"$inputPath/$conciertos"
    val pathPokemonTCG = s"$inputPath/$pokemon_tcg"
    val pathPokemonUpdate = s"$inputPath/$pokemon_update"

    val dfConcierto =spark.read
      .option("header", "true")
      .option("encoding", "UTF-8")
      .option("inferSchema", "true")
      .csv(pathConciertos)

    val dfConcertclean = cleanConcert(dfConcierto)

    println("Informacion ordenada por Rank de manera ascendente")
    dfConcertclean.orderBy(asc("Rank")).show()

    println("Información ordenada por Total actual gross de manera descendente")
    dfConcertclean.orderBy(desc("Total actual gross")).show()


  }
}
