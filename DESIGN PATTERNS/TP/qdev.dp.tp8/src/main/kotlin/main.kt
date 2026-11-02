import fruits.dao.FruitJsonDAO
import fruits.model.Fruit
import java.io.File

fun main(){
    val fichier = File("data/fruits.json")
    var listeFruit = mutableListOf<Fruit>()
    val dao = FruitJsonDAO(fichier)
    listeFruit.add(dao.donneLeFruit("Kiwi")!!)
    listeFruit.add(dao.donneLeFruit("Poire")!!)
    listeFruit.add(dao.donneLeFruit("Banane")!!)
    println("_______________________________________________")
    println(fichier.name)


}