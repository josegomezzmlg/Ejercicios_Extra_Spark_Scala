package functions
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, regexp_extract, regexp_replace, substring, sum, when}
import org.apache.spark.sql.{DataFrame, SparkSession}
import variable.variable.{pokemonTCGSchema, pokemonUpdateSchema}



object functions {
  /**
   * Realiza la limpieza y transformación del dataset de conciertos.
   *
   * Transformaciones aplicadas:
   * - Eliminación de símbolos y caracteres especiales en Artist y Tour title.
   * - Renombrado de Year(s) a Year.
   * - Obtención del año inicial de la gira.
   * - Eliminación de la columna Peak.
   * - Extracción del valor principal de All Time Peak.
   * - Renombrado de Adjusted gross (in 2022 dollars).
   * - Conversión de columnas monetarias a formato numérico.
   * - Cálculo de Diff gross.
   * - Limpieza de la columna Ref.
   * - Cálculo del Total actual gross.
   *
   * @param dfConcierto DataFrame original de conciertos.
   * @return DataFrame limpio y enriquecido.
   */

  def cleanConcert(dfConcierto:DataFrame): DataFrame = {

    val windowArtist = Window.partitionBy("Artist")

    val clean = dfConcierto
      .withColumnRenamed("Ref.", "Ref")
      .withColumnRenamed("Year(s)", "Year")
      .withColumnRenamed("Adjusted gross (in 2022 dollars)","Adjusted gross")
      .withColumnRenamed("Actual gross", "Actual gross")
      .withColumn("Artist",regexp_replace(col("Artist"), "[^a-zA-Z0-9 ]", ""))
      .withColumn("Tour title",regexp_replace(col("Tour title"), "[^a-zA-Z0-9 ]", ""))
      .withColumn("Year", substring(col("Year"), 1, 4).cast("int"))
      .withColumn("All Time Peak",regexp_extract(col("All Time Peak"), "^([^\\[]+)", 1))
      .withColumn("Adjusted gross",regexp_replace(col("Adjusted gross"),"[^0-9]","").cast("long"))
      .withColumn("Actual gross",regexp_replace(col("Actual gross"),"[^0-9]","").cast("long"))
      .withColumn("Diff gross",col("Adjusted gross") - col("Actual gross"))
      .withColumn("Ref",regexp_replace(col("Ref"),"[^0-9]",""))
      .withColumn("Average gross",regexp_replace(col("Average gross"),"[^0-9]","").cast("long"))
      .withColumn("Total actual gross", sum(col("Actual gross")).over(windowArtist))
      .drop(col("Peak"))
    clean
  }

  def loadConcert(spark: SparkSession, pathConciertos: String): DataFrame = {
    val dfConcierto = spark.read
      .option("header", "true")
      .option("encoding", "UTF-8")
      .option("inferSchema", "true")
      .csv(pathConciertos)

    dfConcierto
  }

  def loadPokemon(spark:SparkSession,pathPokemonTCG:String,pathPokemonUpdate:String):(DataFrame, DataFrame)={

    val dfPokemonUpdate = spark.read
      .option("header", "true")
      .schema(pokemonUpdateSchema)
      .csv(pathPokemonUpdate)

    val dfPokemonTCG = spark.read
      .option("header", "true")
      .option("multiLine", "true")
      .option("escape", "\"")
      .schema(pokemonTCGSchema)
      .csv(pathPokemonTCG)

    (dfPokemonUpdate,dfPokemonTCG)
  }

  def countRowColumn(dfPokemon: DataFrame): (Long, Int) = {

    val filas = dfPokemon.count()
    val columnas = dfPokemon.columns.length

    (filas, columnas)
  }

  def columnNull(dfpokemonTcg: DataFrame): DataFrame = {

    val nulos = dfpokemonTcg.select(
      dfpokemonTcg.columns.map(c =>
        sum(when(col(c).isNull, 1).otherwise(0)).alias(c)): _*
    )

    nulos
  }



}