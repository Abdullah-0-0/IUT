package testdao

import fruits.dao.FruitDatabaseDAO
import fruits.model.Fruit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility
import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.valueParameters

/***** UMLChecker Test Cases for FruitDatabaseDAO (shared by the SQL implementations) *****/

class TestUMLFruitDatabaseDAO {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(FruitDatabaseDAO::class)
    }

    @Test
    fun `test0 - FruitDatabaseDAO is an abstract class`() {
        uml.isInterface(false)
        uml.isAbstract(true)
    }

    @Test
    fun `test1 - 'FruitDatabaseDAO' realizes FruitDAO`() {
        uml.extend("FruitDAO")
    }

    @Test
    fun `test2 - 'FruitDatabaseDAO' constructeur`() {
        uml.constructorCheck(
            Triple("url", String::class, false),
            Triple("table", String::class, false)
        )
    }

    @Test
    fun `test3 - 'FruitDatabaseDAO' has attribute table`() {
        uml.attributeCheck("table", String::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test4 - 'FruitDatabaseDAO' has attribute url`() {
        uml.attributeCheck("url", String::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test5 - 'FruitDatabaseDAO' overrides donneLesFruits`() {
        uml.methodCheck("donneLesFruits", List::class, false, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test6 - 'FruitDatabaseDAO' overrides donneLesFruitsCoutantMoinsQue`() {
        uml.methodCheck(
            "donneLesFruitsCoutantMoinsQue", List::class, false,
            Triple("prix", Double::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test7 - 'FruitDatabaseDAO' overrides donneLeFruit`() {
        uml.methodCheck(
            "donneLeFruit", Fruit::class, true,
            Triple("nom", String::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test8 - 'FruitDatabaseDAO' overrides enregistreLesFruits`() {
        val m = FruitDatabaseDAO::class.declaredMemberFunctions.single { it.name == "enregistreLesFruits" }
        assertEquals("fruits", m.valueParameters.single().name)
        assertEquals(Unit::class.createType(), m.returnType)
    }

    @Test
    fun `test9 - 'FruitDatabaseDAO' overrides ajouteUnFruit`() {
        uml.methodCheck(
            "ajouteUnFruit", Unit::class, false,
            Triple("fruit", Fruit::class, false), methIsOpen = true, methIsOverride = true
        )
    }
}
