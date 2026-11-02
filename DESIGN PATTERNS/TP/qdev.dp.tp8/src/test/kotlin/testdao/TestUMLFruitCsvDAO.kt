package testdao

import fruits.dao.FruitCsvDAO
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

/***** UMLChecker Test Cases for FruitCsvDAO (plantuml/fruits-dao.plantuml) *****/

class TestUMLFruitCsvDAO {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.Companion.create(FruitCsvDAO::class)
    }

    @Test
    fun `test0 - FruitCsvDAO is a concrete class`() {
        uml.isInterface(false)
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - FruitCsvDAO is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'FruitCsvDAO' realizes FruitDAO`() {
        uml.extend("FruitDAO")
    }

    @Test
    fun `test3 - 'FruitCsvDAO' has attribute fichier`() {
        uml.attributeCheck("fichier", File::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test4 - 'FruitCsvDAO' constructeur`() {
        uml.constructorCheck(
            Triple("fichier", File::class, false)
        )
    }

    @Test
    fun `test5 - 'FruitCsvDAO' overrides donneLesFruits`() {
        uml.methodCheck("donneLesFruits", List::class, false, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test6 - 'FruitCsvDAO' overrides donneLesFruitsCoutantMoinsQue`() {
        uml.methodCheck(
            "donneLesFruitsCoutantMoinsQue", List::class, false,
            Triple("prix", Double::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test7 - 'FruitCsvDAO' overrides donneLeFruit`() {
        uml.methodCheck(
            "donneLeFruit", Fruit::class, true,
            Triple("nom", String::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test8 - 'FruitCsvDAO' overrides enregistreLesFruits`() {
        // UMLChecker cannot build the parameterized type List<Fruit>, so the signature is checked by reflection
        val m = FruitCsvDAO::class.declaredMemberFunctions.single { it.name == "enregistreLesFruits" }
        Assertions.assertEquals("fruits", m.valueParameters.single().name)
        Assertions.assertEquals(Unit::class.createType(), m.returnType)
        Assertions.assertTrue(m.isOpen)
    }

    @Test
    fun `test9 - 'FruitCsvDAO' overrides ajouteUnFruit`() {
        uml.methodCheck(
            "ajouteUnFruit", Unit::class, false,
            Triple("fruit", Fruit::class, false), methIsOpen = true, methIsOverride = true
        )
    }
}