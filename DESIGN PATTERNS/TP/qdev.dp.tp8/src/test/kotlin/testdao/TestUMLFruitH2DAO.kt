package testdao

import fruits.dao.FruitH2DAO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** UMLChecker Test Cases for FruitH2DAO (the 5 FruitDAO methods are inherited from FruitDatabaseDAO) *****/

class TestUMLFruitH2DAO {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(FruitH2DAO::class)
    }

    @Test
    fun `test0 - FruitH2DAO is a concrete class`() {
        uml.isInterface(false)
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - FruitH2DAO is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'FruitH2DAO' extends FruitDatabaseDAO`() {
        uml.extend("FruitDatabaseDAO")
    }

    @Test
    fun `test3 - 'FruitH2DAO' constructeur`() {
        uml.constructorCheck(
            Triple("fullPath", String::class, false),
            Triple("table", String::class, false)
        )
    }
}
