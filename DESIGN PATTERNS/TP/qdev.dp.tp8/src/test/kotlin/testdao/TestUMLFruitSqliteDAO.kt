import fruits.dao.FruitSqliteDAO
import fruits.model.Fruit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.valueParameters

/***** UMLChecker Test Cases for FruitSqliteDAO (plantuml/fruits-dao.plantuml) *****/

class TestUMLFruitSqliteDAO {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.Companion.create(FruitSqliteDAO::class)
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
    fun `test2 - 'FruitSqliteDAO' realizes FruitDAO`() {
        uml.extend("FruitDAO")
    }

    @Test
    fun `test4 - 'FruitSqliteDAO' constructeur`() {
        uml.constructorCheck(
            Triple("fullPath", String::class, false),
            Triple("table", String::class, false)
        )
    }

    @Test
    fun `test5 - 'FruitSqliteDAO' overrides donneLesFruits`() {
        uml.methodCheck("donneLesFruits", List::class, false, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test6 - 'FruitSqliteDAO' overrides donneLesFruitsCoutantMoinsQue`() {
        uml.methodCheck(
            "donneLesFruitsCoutantMoinsQue", List::class, false,
            Triple("prix", Double::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test7 - 'FruitSqliteDAO' overrides donneLeFruit`() {
        uml.methodCheck(
            "donneLeFruit", Fruit::class, true,
            Triple("nom", String::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test8 - 'FruitSqliteDAO' overrides enregistreLesFruits`() {
        // UMLChecker cannot build the parameterized type List<Fruit>, so the signature is checked by reflection
        val m = FruitSqliteDAO::class.declaredMemberFunctions.single { it.name == "enregistreLesFruits" }
        Assertions.assertEquals("fruits", m.valueParameters.single().name)
        Assertions.assertEquals(Unit::class.createType(), m.returnType)
        Assertions.assertTrue(m.isOpen)
    }

    @Test
    fun `test9 - 'FruitSqliteDAO' overrides ajouteUnFruit`() {
        uml.methodCheck(
            "ajouteUnFruit", Unit::class, false,
            Triple("fruit", Fruit::class, false), methIsOpen = true, methIsOverride = true
        )
    }
}