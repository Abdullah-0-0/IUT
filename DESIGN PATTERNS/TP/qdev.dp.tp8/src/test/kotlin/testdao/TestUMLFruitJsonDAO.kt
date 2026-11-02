package testdao

import fruits.dao.FruitJsonDAO
import fruits.model.Fruit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import java.io.File
import kotlin.reflect.KVisibility
import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.valueParameters

/***** UMLChecker Test Cases for FruitJsonDAO (plantuml/fruits-dao.plantuml) *****/

class TestUMLFruitJsonDAO {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.Companion.create(FruitJsonDAO::class)
    }

    @Test
    fun `test0 - FruitJsonDAO is a concrete class`() {
        uml.isInterface(false)
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - FruitJsonDAO is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'FruitJsonDAO' realizes FruitDAO`() {
        uml.extend("FruitDAO")
    }

    @Test
    fun `test3 - 'FruitJsonDAO' has attribute fichier`() {
        uml.attributeCheck("fichier", File::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test4 - 'FruitJsonDAO' constructeur`() {
        uml.constructorCheck(
            Triple("fichier", File::class, false)
        )
    }

    @Test
    fun `test5 - 'FruitJsonDAO' overrides donneLesFruits`() {
        uml.methodCheck("donneLesFruits", List::class, false, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test6 - 'FruitJsonDAO' overrides donneLesFruitsCoutantMoinsQue`() {
        uml.methodCheck(
            "donneLesFruitsCoutantMoinsQue", List::class, false,
            Triple("prix", Double::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test7 - 'FruitJsonDAO' overrides donneLeFruit`() {
        uml.methodCheck(
            "donneLeFruit", Fruit::class, true,
            Triple("nom", String::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test8 - 'FruitJsonDAO' overrides enregistreLesFruits`() {
        // UMLChecker cannot build the parameterized type List<Fruit>, so the signature is checked by reflection
        val m = FruitJsonDAO::class.declaredMemberFunctions.single { it.name == "enregistreLesFruits" }
        Assertions.assertEquals("fruits", m.valueParameters.single().name)
        Assertions.assertEquals(Unit::class.createType(), m.returnType)
        Assertions.assertTrue(m.isOpen)
    }

    @Test
    fun `test9 - 'FruitJsonDAO' overrides ajouteUnFruit`() {
        uml.methodCheck(
            "ajouteUnFruit", Unit::class, false,
            Triple("fruit", Fruit::class, false), methIsOpen = true, methIsOverride = true
        )
    }
}