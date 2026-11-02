import fruits.model.Fruit
import kotlinx.serialization.json.Json
import java.io.File

fun main(){
    val fichier = File("data/fruits.json")
    print(fichier.readText())
    val conv = fichier.readText()
    val contenu : List<Fruit> = Json.decodeFromString(conv)
    print(contenu)
}