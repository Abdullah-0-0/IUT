package fruits.dao

import fruits.exceptions.FruitDejaPresentException
import fruits.exceptions.ImportFruitsException
import fruits.model.Fruit
import org.h2.tools.Csv
import java.io.File

/**
 * Lit/écrit dans un fichier .CSV pour gérer les fruits
 * @param fichier le fichier .CSV contenant les fruits
 */
class FruitCsvDAO(private val fichier: File) : FruitDAO {
    override fun donneLesFruits(): List<Fruit> {
        val fruits = mutableListOf<Fruit>()

        if (!fichier.exists() || !fichier.name.endsWith(".csv")) {
            throw ImportFruitsException("Fichier introuvable")
        }

        val conv = fichier.readLines()

        for (i in conv.indices) {

            val separateur = conv[i].split(",")

            fruits.add(
                Fruit(
                    separateur[0].trim(),
                    separateur[2].trim().toDouble(),
                    separateur[1].trim().toInt()
                )
            )
        }

        return fruits
    }

    override fun donneLesFruitsCoutantMoinsQue(prix: Double): List<Fruit> {
        return donneLesFruits().filter { it.prix< prix}.sortedBy { it.prix }
    }

    override fun donneLeFruit(nom: String): Fruit? {
        return donneLesFruits().find { it.nom == nom }
    }

    override fun enregistreLesFruits(fruits: List<Fruit>) {
        val fruitsActu = donneLesFruits()
        val contenu = buildString {
            fruits.forEach {
                appendLine("${it.nom}, ${it.quantite} , ${it.prix}")
            }
        }
        fichier.writeText(contenu)
    }

    override fun ajouteUnFruit(fruit: Fruit) {
        if (donneLesFruits().contains(fruit)) {
            throw FruitDejaPresentException("${fruit.nom} est déjà present")
        }
        enregistreLesFruits(donneLesFruits() + fruit)
    }

}