import iut.but2.tp1.FileChainee
import kotlin.math.ln

fun main(args: Array<String>) {
    println("Hello World!")
    var file = FileChainee<Int>()
    file.insererEnQueue(12)
    file.insererEnQueue(11)
    file.insererEnQueue(10)

    println("liste : ${file.listerDepuisDebut()}")

    println("l'element a l'indice 2 : ${file.consulter(2)}")

    println("taille : ${file.taille()}")

    println("liste : ${file.listerDepuisDebut()}")
    println("listeFin : ${file.listerDepuisFin()}")

}