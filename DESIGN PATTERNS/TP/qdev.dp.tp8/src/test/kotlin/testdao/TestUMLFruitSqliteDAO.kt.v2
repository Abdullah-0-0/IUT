package testdao

import fruits.dao.FruitSqliteDAO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** UMLChecker Test Cases for FruitSqliteDAO (the 5 FruitDAO methods are inherited from FruitDatabaseDAO) *****/

class TestUMLFruitSqliteDAO {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(FruitSqliteDAO::class)
    }

    @Test
    fun `test0 - FruitSqliteDAO is a concrete class`() {
        uml.isInterface(false)
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - FruitSqliteDAO is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'FruitSqliteDAO' extends FruitDatabaseDAO`() {
        uml.extend("FruitDatabaseDAO")
    }

    @Test
    fun `test3 - 'FruitSqliteDAO' constructeur`() {
        uml.constructorCheck(
            Triple("fullPath", String::class, false),
            Triple("table", String::class, false)
        )
    }
}
