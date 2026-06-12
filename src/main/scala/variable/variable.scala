package variable
import org.apache.spark.sql.types._
import org.apache.spark.sql.DataFrame

object variable {

  val conciertos = "conciertos.csv"
  val pokemon_tcg = "pokemon_tcg.csv"
  val pokemon_update = "pokemon_updated.csv"

  val pokemonUpdateSchema = StructType(Array(
    StructField("#", IntegerType, true),
    StructField("Name", StringType, true),
    StructField("Type 1", StringType, true),
    StructField("Type 2", StringType, true),
    StructField("HP", IntegerType, true),
    StructField("Attack", IntegerType, true),
    StructField("Defense", IntegerType, true),
    StructField("Sp. Atk", IntegerType, true),
    StructField("Sp. Def", IntegerType, true),
    StructField("Speed", IntegerType, true),
    StructField("Generation", IntegerType, true),
    StructField("Legendary", BooleanType, true),
    StructField("Total", IntegerType, true)
  ))

  val pokemonTCGSchema = StructType(Array(
    StructField("id", StringType, true),
    StructField("set", StringType, true),
    StructField("series", StringType, true),
    StructField("publisher", StringType, true),
    StructField("generation", StringType, true),
    StructField("release_date", StringType, true),
    StructField("artist", StringType, true),
    StructField("name", StringType, true),
    StructField("set_num", IntegerType, true),
    StructField("types", StringType, true),
    StructField("supertype", StringType, true),
    StructField("subtypes", StringType, true),
    StructField("level", IntegerType, true),
    StructField("hp", IntegerType, true),
    StructField("evolvesFrom", StringType, true),
    StructField("evolvesTo", StringType, true),
    StructField("abilities", StringType, true),
    StructField("attacks", StringType, true),
    StructField("weaknesses", StringType, true),
    StructField("retreatCost", StringType, true),
    StructField("convertedRetreatCost", IntegerType, true),
    StructField("rarity", StringType, true),
    StructField("flavorText", StringType, true),
    StructField("nationalPokedexNumbers", StringType, true),
    StructField("legalities", StringType, true),
    StructField("resistances", StringType, true),
    StructField("rules", StringType, true),
    StructField("regulationMark", StringType, true),
    StructField("ancientTrait", StringType, true)
  ))


}
