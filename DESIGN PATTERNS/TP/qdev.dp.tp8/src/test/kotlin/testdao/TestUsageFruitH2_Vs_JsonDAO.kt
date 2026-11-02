package testdao

import fruits.dao.FruitDAO
import fruits.dao.FruitH2DAO
import fruits.dao.FruitJsonDAO
import fruits.model.Fruit
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class TestUsageFruitH2_Vs_JsonDAO {

    lateinit var mesFruits: List<Fruit>
    lateinit var daoJson: FruitDAO
    lateinit var daoH2: FruitDAO

    val basedir = "./data/"

    val testdir = basedir + "test/"
    val filename = "fruits.json"

    val prefix = "jdbc:h2:"
    val basename = "fruits.h2"
    val originTable = "DB_Fruits"

    @BeforeEach
    fun initH2() {
        val targetTable = tools.Tools.copyDatatable(prefix, basedir + basename, originTable)
        daoH2 = FruitH2DAO(basedir + basename, targetTable)
    }


    @BeforeEach
    fun initJSON() {
        File(basedir + filename).copyTo(File(testdir + filename), overwrite = true)
        daoJson = FruitJsonDAO(File(testdir + filename))
    }

    @AfterEach
    fun cleanJSON() {
        File(testdir + filename).delete()
    }

    @Test
    fun readAndWritejsonH2() {
        val fruitsJ = daoJson.donneLesFruits()
        daoH2.enregistreLesFruits(fruitsJ)
        assertEquals(fruitsJ, daoH2.donneLesFruits())
    }

    @Test
    fun readAndWriteH2json() {
        val fruitsJ = daoH2.donneLesFruits()
        daoJson.enregistreLesFruits(fruitsJ)
        assertEquals(fruitsJ, daoJson.donneLesFruits())
    }

    @Test
    fun testFruitsCoutantMoinsQue10() {
        val fruitsJ = daoJson.donneLesFruitsCoutantMoinsQue(10.0)
        val fruitsS = daoH2.donneLesFruitsCoutantMoinsQue(10.0)
        assertEquals(fruitsJ, fruitsS)
    }

    @Test
    fun testEnregistrement() {
        val mesFruits = daoJson.donneLesFruitsCoutantMoinsQue(10.0)
        daoH2.enregistreLesFruits(mesFruits)
        assertEquals(mesFruits, daoH2.donneLesFruitsCoutantMoinsQue(10.0))
    }


}