import fruits.dao.FruitCsvDAO
import fruits.dao.FruitJsonDAO
import fruits.model.Fruit
import java.io.File

fun main(){
    val fichier = File("data/fruits.csv")

    if (fichier.exists()) {
        println("Nombre de lignes : ${fichier.readLines().size}")
        println("Contenu :")
        fichier.readLines().forEach { println(it) }
    }

    val dao = FruitCsvDAO(fichier)

    println("========== TEST DAO ==========")
    val fruits = dao.donneLesFruits()

    fruits.forEach { println(it) }


}