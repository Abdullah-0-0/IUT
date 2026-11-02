package testdao

import fruits.dao.FruitMariaDbDAO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker

/***** UMLChecker Test Cases for FruitMariaDbDAO (the 5 FruitDAO methods are inherited from FruitDatabaseDAO) *****/

class TestUMLFruitMariaDbDAO {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(FruitMariaDbDAO::class)
    }

    @Test
    fun `test0 - FruitMariaDbDAO is a concrete class`() {
        uml.isInterface(false)
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - FruitMariaDbDAO is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'FruitMariaDbDAO' extends FruitDatabaseDAO`() {
        uml.extend("FruitDatabaseDAO")
    }

    @Test
    fun `test3 - 'FruitMariaDbDAO' constructeur`() {
        uml.constructorCheck(
            Triple("host", String::class, false),
            Triple("database", String::class, false),
            Triple("user", String::class, false),
            Triple("password", String::class, false),
            Triple("table", String::class, false)
        )
    }
}
