package fruits.dao

import fruits.exceptions.ImportFruitsException
import fruits.model.Fruit
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import java.io.File


/**
 * Lit/écrit dans un fichier JSON pour gérer les fruits
 * @param fichier le fichier JSON contenant les fruits
 */

@OptIn(ExperimentalSerializationApi::class)
class FruitJsonDAO(private val fichier: File) : FruitDAO {
    override fun donneLesFruits(): List<Fruit> {
        if (!fichier.exists()){
            throw ImportFruitsException("le fichier n'existe pas")
        }
        val conString = fichier.readText()
        val fruits : List<Fruit> = Json.decodeFromString(conString)
        return fruits
    }

    override fun donneLesFruitsCoutantMoinsQue(prix: Double): List<Fruit> {
        val fruits = donneLesFruits()
        for (i in fruits){
            if (i.prix < prix){
                
            }
        }
    }

    override fun donneLeFruit(nom: String): Fruit? {
        TODO("Not yet implemented")
    }

    override fun enregistreLesFruits(fruits: List<Fruit>) {
        TODO("Not yet implemented")
    }

    override fun ajouteUnFruit(fruit: Fruit) {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }


}