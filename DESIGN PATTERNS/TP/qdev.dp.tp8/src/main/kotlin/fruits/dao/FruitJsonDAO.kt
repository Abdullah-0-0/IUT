package fruits.dao
import fruits.exceptions.FruitDejaPresentException
import fruits.exceptions.ImportFruitsException
import fruits.model.Fruit
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File


/**
 * Lit/écrit dans un fichier JSON pour gérer les fruits
 * @param fichier le fichier JSON contenant les fruits
 */

@OptIn(ExperimentalSerializationApi::class)
class FruitJsonDAO(private val fichier: File) : FruitDAO {
    override fun donneLesFruits(): List<Fruit> {
        if (!fichier.name.contains(".json")){
            throw ImportFruitsException("fichier introuvable")
        }
        val conv = fichier.readText()
        val fruits : List<Fruit> = Json.decodeFromString(conv)
        return fruits
    }

    override fun donneLesFruitsCoutantMoinsQue(prix: Double): List<Fruit> {
        val fruits = donneLesFruits()
        val res : MutableList<Fruit> = mutableListOf()
        for (i in fruits){
            if (i.prix< prix){
                res.add(i)
            }
        }
        return  res
    }

    override fun donneLeFruit(nom: String): Fruit? {
        val fruits = donneLesFruits()
        for (i in fruits){
            if (i.nom == nom){
                return i
            }
        }
        return null
    }

    override fun enregistreLesFruits(fruits: List<Fruit>) {
        var res : String = "[\n"
        for (i in 0 until fruits.size){
            if (i==fruits.size-1){
                res+="{\n" +
                        """ "nom" """ + " : "+""""${fruits[i].nom}"""" +",\n"+
                        """"quantite"""" + " : "+""""${fruits[i].quantite}"""" +",\n"+
                        """"prix"""" + " : "+""""${fruits[i].prix}"""" +"\n"+
                        "}\n"
            }
            else{
                res+="{\n" +
                        """"nom"""" + " : "+""""${fruits[i].nom}"""" +",\n"+
                        """"quantite"""" + " : "+""""${fruits[i].quantite}"""" +",\n"+
                        """"prix"""" + " : "+""""${fruits[i].prix}"""" +"\n"+
                        "},\n"
            }

        }
        res += "]"

        fichier.writeText(res)
//        for (i in fruits){
//            Json.encodeToJsonElement(i)
//        }
    }

    override fun ajouteUnFruit(fruit: Fruit) {
        if (donneLesFruits().contains(fruit)) {
            throw FruitDejaPresentException("fruit déjà present")
        }
        val listeFruitActu = donneLesFruits()
        val res: MutableList<Fruit> = mutableListOf()
        for (i in listeFruitActu) {
            res.add(i)
        }
        res.add(fruit)
        println(res)
        enregistreLesFruits(res)
    }


}