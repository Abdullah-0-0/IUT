package testrepository

import fruits.FruitRepository
import fruits.dao.FruitCsvDAO
import fruits.dao.FruitH2DAO
import fruits.dao.FruitJsonDAO
import fruits.dao.FruitSqliteDAO
import fruits.exceptions.FruitDejaPresentException
import fruits.exceptions.ImportFruitsException
import fruits.model.Fruit
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import tools.Tools
import java.io.File

/***** Test Cases for FruitRepository, using the real DAOs on the files of data/ *****/

class TestUsageFruitRepository {

    val basedir = "./data/"
    val jsonFile = File(basedir + "fruits.json")
    val csvFile = File(basedir + "fruits.csv")
    val originTable = "DB_Fruits"

    val nbFruits = 15
    val nomsAlphabetiques = listOf(
        "Abricot", "Ananas", "Banane", "Fraise", "Framboise", "Goyave", "Kiwi", "Melon",
        "Mûre", "Nectarine", "Orange", "Poire", "Pomme", "Pêche", "Raisin"
    )
    val nomsPlusAuMoinsCher = listOf(
        "Ananas", "Melon", "Orange", "Goyave", "Nectarine", "Raisin", "Abricot", "Pêche",
        "Mûre", "Framboise", "Poire", "Fraise", "Banane", "Pomme", "Kiwi"
    )

    /* ---------- chargement des fruits par le DAO ---------- */

    @Test
    fun `test0 - fruits are loaded from a JSON file`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        assertEquals(nbFruits, repo.fruits.size)
        assertEquals(Fruit("Kiwi", 4.5, 10), repo.fruits.find { it.nom == "Kiwi" })
    }

    @Test
    fun `test1 - fruits are loaded from a CSV file`() {
        val repo = FruitRepository(FruitCsvDAO(csvFile))
        assertEquals(nbFruits, repo.fruits.size)
        assertEquals(Fruit("Ananas", 30.4, 140), repo.fruits.find { it.nom == "Ananas" })
    }

    @Test
    fun `test2 - JSON and CSV give the same fruits`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        val repoCsv = FruitRepository(FruitCsvDAO(csvFile))
        assertEquals(repoJson.fruits, repoCsv.fruits)
    }

    @Test
    fun `test3 - loading a CSV file with the JSON DAO throws ImportFruitsException`() {
        assertThrows<ImportFruitsException> { FruitRepository(FruitJsonDAO(csvFile)) }
    }

    @Test
    fun `test4 - loading a missing file throws ImportFruitsException`() {
        val missing = File(basedir + "inexistant.json")
        assertThrows<ImportFruitsException> { FruitRepository(FruitJsonDAO(missing)) }
    }

    @Test
    fun `test5 - fruits are loaded from a SQLite database`() {
        val path = basedir + "fruits.sqlite"
        val table = Tools.copyDatatable("jdbc:sqlite:", path, originTable)
        val repo = FruitRepository(FruitSqliteDAO(path, table))
        assertEquals(nbFruits, repo.fruits.size)
    }

    @Test
    fun `test6 - fruits are loaded from an H2 database`() {
        val path = basedir + "fruits.h2"
        val table = Tools.copyDatatable("jdbc:h2:", path, originTable)
        val repo = FruitRepository(FruitH2DAO(path, table))
        assertEquals(nbFruits, repo.fruits.size)
    }

    @Test
    fun `test7 - loading an unknown table throws ImportFruitsException`() {
        val path = basedir + "fruits.sqlite"
        assertThrows<ImportFruitsException> { FruitRepository(FruitSqliteDAO(path, "TableInconnue")) }
    }

    /* ---------- fruits ---------- */

    @Test
    fun `test8 - fruits gives all the fruits`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        assertEquals(nbFruits, repoJson.fruits.size)
    }

    /* ---------- donneLesFruitsQuantiteSupp (strictly greater) ---------- */

    @Test
    fun `test9 - donneLesFruitsQuantiteSupp keeps the fruits with a quantity strictly above`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        val noms = repoJson.donneLesFruitsQuantiteSupp(100).map { it.nom }
        assertEquals(setOf("Ananas", "Melon", "Orange"), noms.toSet())
    }

    @Test
    fun `test10 - donneLesFruitsQuantiteSupp above every quantity gives an empty list`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        assertTrue(repoJson.donneLesFruitsQuantiteSupp(140).isEmpty())
        assertTrue(repoJson.donneLesFruitsQuantiteSupp(1000).isEmpty())
    }

    @Test
    fun `test11 - donneLesFruitsQuantiteSupp with 0 excludes only Goyave`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        val fruits = repoJson.donneLesFruitsQuantiteSupp(0)
        assertEquals(nbFruits - 1, fruits.size)
        assertTrue(fruits.none { it.nom == "Goyave" })
    }

    @Test
    fun `test12 - donneLesFruitsQuantiteSupp with a negative quantity gives all the fruits`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        assertEquals(nbFruits, repoJson.donneLesFruitsQuantiteSupp(-1).size)
    }

    /* ---------- iterateurFruitsAlphabetique ---------- */

    @Test
    fun `test13 - iterateurFruitsAlphabetique goes through the fruits by name`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        val iterateurAttendu = nomsAlphabetiques.iterator()
        val iterateurTeste = repoJson.iterateurFruitsAlphabetique()
        while (iterateurTeste.hasNext() && iterateurAttendu.hasNext()) {
            val nomAttendu = iterateurAttendu.next()
            val nomResultat = iterateurTeste.next().nom
            assertEquals(nomAttendu, nomResultat)
        }
        assertFalse(iterateurTeste.hasNext())
        assertFalse(iterateurAttendu.hasNext())
    }

    @Test
    fun `test14 - iterateurFruitsAlphabetique is the same for JSON and CSV`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        val repoCsv = FruitRepository(FruitCsvDAO(csvFile))
        val iterateurJson = repoJson.iterateurFruitsAlphabetique()
        val iterateurCsv = repoCsv.iterateurFruitsAlphabetique()
        while (iterateurJson.hasNext() && iterateurCsv.hasNext()) {
            assertEquals(iterateurJson.next(), iterateurCsv.next())
        }
        assertFalse(iterateurJson.hasNext())
        assertFalse(iterateurCsv.hasNext())
    }

    @Test
    fun `test15 - iterateurFruitsAlphabetique throws NoSuchElementException when exhausted`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        val iterateur = repoJson.iterateurFruitsAlphabetique()
        repeat(nbFruits) { iterateur.next() }
        assertFalse(iterateur.hasNext())
        assertThrows<NoSuchElementException> { iterateur.next() }
    }

    /* ---------- iterateurFruitsPlusAuMoinCher ---------- */

    @Test
    fun `test16 - iterateurFruitsPlusAuMoinCher goes from the most to the least expensive`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        val iterateurAttendu = nomsPlusAuMoinsCher.iterator()
        val iterateurTeste = repoJson.iterateurFruitsPlusAuMoinCher()
        while (iterateurTeste.hasNext() && iterateurAttendu.hasNext()) {
            val nomAttendu = iterateurAttendu.next()
            val nomResultat = iterateurTeste.next().nom
            assertEquals(nomAttendu, nomResultat)
        }
        assertFalse(iterateurTeste.hasNext())
        assertFalse(iterateurAttendu.hasNext())
    }

    @Test
    fun `test17 - iterateurFruitsPlusAuMoinCher is the same for JSON and CSV`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        val repoCsv = FruitRepository(FruitCsvDAO(csvFile))
        val iterateurJson = repoJson.iterateurFruitsPlusAuMoinCher()
        val iterateurCsv = repoCsv.iterateurFruitsPlusAuMoinCher()
        while (iterateurJson.hasNext() && iterateurCsv.hasNext()) {
            assertEquals(iterateurJson.next(), iterateurCsv.next())
        }
        assertFalse(iterateurJson.hasNext())
        assertFalse(iterateurCsv.hasNext())
    }

    @Test
    fun `test18 - iterateurFruitsPlusAuMoinCher throws NoSuchElementException when exhausted`() {
        val repoJson = FruitRepository(FruitJsonDAO(jsonFile))
        val iterateur = repoJson.iterateurFruitsPlusAuMoinCher()
        repeat(nbFruits) { iterateur.next() }
        assertFalse(iterateur.hasNext())
        assertThrows<NoSuchElementException> { iterateur.next() }
    }

    @Test
    fun `test19 - iterateurFruitsAlphabetique does not modify the repository`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        val avant = repo.fruits.toList()
        val iterateur = repo.iterateurFruitsAlphabetique()
        while (iterateur.hasNext()) iterateur.next()
        val iterateurAvant = avant.iterator()
        val iterateurApres = repo.fruits.iterator()
        while (iterateurAvant.hasNext() && iterateurApres.hasNext()) {
            assertEquals(iterateurAvant.next(), iterateurApres.next())
        }
        assertFalse(iterateurAvant.hasNext())
        assertFalse(iterateurApres.hasNext())
    }

    @Test
    fun `test20 - iterateurFruitsPlusAuMoinCher does not modify the repository`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        val avant = repo.fruits.toList()
        val iterateur = repo.iterateurFruitsPlusAuMoinCher()
        while (iterateur.hasNext()) iterateur.next()
        val iterateurAvant = avant.iterator()
        val iterateurApres = repo.fruits.iterator()
        while (iterateurAvant.hasNext() && iterateurApres.hasNext()) {
            assertEquals(iterateurAvant.next(), iterateurApres.next())
        }
        assertFalse(iterateurAvant.hasNext())
        assertFalse(iterateurApres.hasNext())
    }

    /* ---------- ajouteUnFruit ---------- */

    @Test
    fun `test21 - ajouteUnFruit adds the fruit to the repository`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        val cerise = Fruit("Cerise", 7.5, 40)
        repo.ajouteUnFruit(cerise)
        assertEquals(nbFruits + 1, repo.fruits.size)
        assertEquals(cerise, repo.fruits.find { it.nom == "Cerise" })
    }

    @Test
    fun `test22 - an added fruit is visible in the iterators`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        repo.ajouteUnFruit(Fruit("Cerise", 100.0, 40))
        val attendus = (nomsAlphabetiques + "Cerise").sorted().iterator()
        val iterateur = repo.iterateurFruitsAlphabetique()
        while (iterateur.hasNext() && attendus.hasNext()) {
            assertEquals(attendus.next(), iterateur.next().nom)
        }
        assertFalse(iterateur.hasNext())
        assertFalse(attendus.hasNext())
        assertEquals("Cerise", repo.iterateurFruitsPlusAuMoinCher().next().nom)
    }

    @Test
    fun `test23 - ajouteUnFruit throws FruitDejaPresentException for a known name`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        assertThrows<FruitDejaPresentException> { repo.ajouteUnFruit(Fruit("Kiwi", 1.0, 1)) }
        assertEquals(nbFruits, repo.fruits.size)
    }

    @Test
    fun `test24 - ajouteUnFruit does not modify the data of the DAO`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        repo.ajouteUnFruit(Fruit("Cerise", 7.5, 40))
        val autreRepo = FruitRepository(FruitJsonDAO(jsonFile))
        assertEquals(nbFruits, autreRepo.fruits.size)
    }

    /* ---------- supprimeUnFruit ---------- */

    @Test
    fun `test25 - supprimeUnFruit removes the fruit from the repository`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        assertTrue(repo.supprimeUnFruit("Kiwi"))
        assertEquals(nbFruits - 1, repo.fruits.size)
        assertTrue(repo.fruits.none { it.nom == "Kiwi" })
    }

    @Test
    fun `test26 - supprimeUnFruit gives false for an unknown name`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        assertFalse(repo.supprimeUnFruit("Licorne"))
        assertEquals(nbFruits, repo.fruits.size)
    }

    @Test
    fun `test27 - supprimeUnFruit gives false the second time`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        assertTrue(repo.supprimeUnFruit("Kiwi"))
        assertFalse(repo.supprimeUnFruit("Kiwi"))
    }

    @Test
    fun `test28 - a removed fruit can be added again`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        repo.supprimeUnFruit("Kiwi")
        repo.ajouteUnFruit(Fruit("Kiwi", 5.0, 1))
        assertEquals(nbFruits, repo.fruits.size)
    }

    /* ---------- recharge ---------- */

    @Test
    fun `test29 - recharge cancels the added fruits`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        repo.ajouteUnFruit(Fruit("Cerise", 7.5, 40))
        repo.recharge()
        assertEquals(nbFruits, repo.fruits.size)
    }

    @Test
    fun `test30 - recharge cancels the removed fruits`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        repo.supprimeUnFruit("Kiwi")
        repo.recharge()
        assertEquals(nbFruits, repo.fruits.size)
        assertTrue(repo.fruits.any { it.nom == "Kiwi" })
    }

    @Test
    fun `test31 - recharge right after creation keeps the fruits`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        repo.recharge()
        assertEquals(nbFruits, repo.fruits.size)
    }

    @Test
    fun `test32 - recharge sees the new data of the DAO`() {
        val copie = File(basedir + "test/fruits.json")
        jsonFile.copyTo(copie, overwrite = true)
        try {
            val repo = FruitRepository(FruitJsonDAO(copie))
            assertEquals(nbFruits, repo.fruits.size)
            FruitJsonDAO(copie).ajouteUnFruit(Fruit("Cerise", 7.5, 40))
            assertEquals(nbFruits, repo.fruits.size)
            repo.recharge()
            assertEquals(nbFruits + 1, repo.fruits.size)
        } finally {
            copie.delete()
        }
    }

    @Test
    fun `test33 - a failing recharge throws ImportFruitsException and keeps the fruits`() {
        val copie = File(basedir + "test/fruits.json")
        jsonFile.copyTo(copie, overwrite = true)
        try {
            val repo = FruitRepository(FruitJsonDAO(copie))
            assertEquals(nbFruits, repo.fruits.size)
            copie.delete()
            assertThrows<ImportFruitsException> { repo.recharge() }
            assertEquals(nbFruits, repo.fruits.size)
        } finally {
            copie.delete()
        }
    }

    /* ---------- fruits est une copie instantanée ---------- */

    @Test
    fun `test34 - fruits gives a new list at each call`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        assertNotSame(repo.fruits, repo.fruits)
        assertEquals(repo.fruits, repo.fruits)
    }

    @Test
    fun `test35 - modifying the list given by fruits does not modify the repository`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        val copie = repo.fruits
        if (copie is MutableList<Fruit>) {
            copie.clear()
            assertEquals(0, copie.size)
        }
        assertEquals(nbFruits, repo.fruits.size)
    }

    @Test
    fun `test36 - a list taken before ajouteUnFruit does not contain the added fruit`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        val avant = repo.fruits
        repo.ajouteUnFruit(Fruit("Cerise", 7.5, 40))
        assertEquals(nbFruits, avant.size)
        assertTrue(avant.none { it.nom == "Cerise" })
        assertEquals(nbFruits + 1, repo.fruits.size)
    }

    @Test
    fun `test37 - a list taken before supprimeUnFruit still contains the removed fruit`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        val avant = repo.fruits
        repo.supprimeUnFruit("Kiwi")
        assertEquals(nbFruits, avant.size)
        assertTrue(avant.any { it.nom == "Kiwi" })
        assertEquals(nbFruits - 1, repo.fruits.size)
    }

    @Test
    fun `test38 - a list taken before recharge is not modified by recharge`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        repo.ajouteUnFruit(Fruit("Cerise", 7.5, 40))
        val avant = repo.fruits
        repo.recharge()
        assertEquals(nbFruits + 1, avant.size)
        assertEquals(nbFruits, repo.fruits.size)
    }

    /* ---------- changement de DAO ---------- */

    @Test
    fun `test39 - changeLeDAO alone does not modify the fruits`() {
        val copie = File(basedir + "test/fruits.json")
        jsonFile.copyTo(copie, overwrite = true)
        try {
            FruitJsonDAO(copie).ajouteUnFruit(Fruit("Cerise", 7.5, 40))
            val repo = FruitRepository(FruitJsonDAO(jsonFile))
            repo.changeLeDAO(FruitJsonDAO(copie))
            assertEquals(nbFruits, repo.fruits.size)
        } finally {
            copie.delete()
        }
    }

    @Test
    fun `test40 - recharge after changeLeDAO reads the fruits of the new DAO`() {
        val copie = File(basedir + "test/fruits.json")
        jsonFile.copyTo(copie, overwrite = true)
        try {
            FruitJsonDAO(copie).ajouteUnFruit(Fruit("Cerise", 7.5, 40))
            val repo = FruitRepository(FruitJsonDAO(jsonFile))
            repo.changeLeDAO(FruitJsonDAO(copie))
            repo.recharge()
            assertEquals(nbFruits + 1, repo.fruits.size)
            assertTrue(repo.fruits.any { it.nom == "Cerise" })
        } finally {
            copie.delete()
        }
    }

    @Test
    fun `test41 - recharge after changeLeDAO to another kind of DAO gives the same fruits`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        val avant = repo.fruits
        repo.changeLeDAO(FruitCsvDAO(csvFile))
        repo.recharge()
        assertEquals(avant, repo.fruits)
    }

    @Test
    fun `test42 - recharge after changeLeDAO forgets the in-memory changes`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        repo.ajouteUnFruit(Fruit("Cerise", 7.5, 40))
        repo.supprimeUnFruit("Kiwi")
        repo.changeLeDAO(FruitCsvDAO(csvFile))
        repo.recharge()
        assertEquals(nbFruits, repo.fruits.size)
        assertTrue(repo.fruits.none { it.nom == "Cerise" })
        assertTrue(repo.fruits.any { it.nom == "Kiwi" })
    }

    @Test
    fun `test43 - recharge with a failing new DAO throws ImportFruitsException and keeps the fruits`() {
        val repo = FruitRepository(FruitJsonDAO(jsonFile))
        repo.changeLeDAO(FruitJsonDAO(File(basedir + "inexistant.json")))
        assertThrows<ImportFruitsException> { repo.recharge() }
        assertEquals(nbFruits, repo.fruits.size)
    }

    @Test
    fun `test44 - the old DAO is no longer used after changeLeDAO`() {
        val copie = File(basedir + "test/fruits.json")
        jsonFile.copyTo(copie, overwrite = true)
        try {
            val repo = FruitRepository(FruitJsonDAO(copie))
            repo.changeLeDAO(FruitCsvDAO(csvFile))
            FruitJsonDAO(copie).ajouteUnFruit(Fruit("Cerise", 7.5, 40))
            repo.recharge()
            assertEquals(nbFruits, repo.fruits.size)
        } finally {
            copie.delete()
        }
    }
}
