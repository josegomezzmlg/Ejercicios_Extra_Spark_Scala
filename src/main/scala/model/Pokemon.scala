package model

import functions.functions.{columnNull, countRowColumn, loadPokemon}
import org.apache.spark.sql.SparkSession
import variable.variable.{pokemon_tcg, pokemon_update}

object Pokemon {
  def execute(spark: SparkSession,inputPath: String): Unit = {

    val pathPokemonTCG = s"$inputPath/$pokemon_tcg"
    val pathPokemonUpdate = s"$inputPath/$pokemon_update"

    val (dfpokemonUpdate,dfpokemonTcg) = loadPokemon(spark,pathPokemonTCG,pathPokemonUpdate)

    dfpokemonTcg.show()
    dfpokemonUpdate.show()

    val (filasUpdate, columnasUpdate) = countRowColumn(dfpokemonUpdate)
    val (filasTcg, columnasTcg) = countRowColumn(dfpokemonTcg)

    println("Filas y Columnas de pokemon Update")
    println(s"Filas: $filasUpdate")
    println(s"Columnas: $columnasUpdate")

    println("Filas y Columnas de pokemon TCG")
    println(s"Filas: $filasTcg")
    println(s"Columnas: $columnasTcg")

    println("Las columnas con valores nulos son: ")
    val nulos = columnNull(dfpokemonTcg)
    nulos.show()

    println("Identificacion de columnas multivalor (Array/Json)")







  }
}
