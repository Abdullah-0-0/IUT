import fruits.dao.FruitMariaDbDAO
import fruits.model.Fruit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.valueParameters

/***** UMLChecker Test Cases for FruitMariaDbDAO (plantuml/fruits-dao.plantuml) *****/

class TestUMLFruitMariaDbDAO {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.Companion.create(FruitMariaDbDAO::class)
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
    fun `test2 - 'FruitMariaDbDAO' realizes FruitDAO`() {
        uml.extend("FruitDAO")
    }

    @Test
    fun `test4 - 'FruitMariaDbDAO' constructeur`() {
        uml.constructorCheck(
            Triple("host", String::class, false),
            Triple("database", String::class, false),
            Triple("user", String::class, false),
            Triple("password", String::class, false),
            Triple("table", String::class, false)
        )
    }

    @Test
    fun `test5 - 'FruitMariaDbDAO' overrides donneLesFruits`() {
        uml.methodCheck("donneLesFruits", List::class, false, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test6 - 'FruitMariaDbDAO' overrides donneLesFruitsCoutantMoinsQue`() {
        uml.methodCheck(
            "donneLesFruitsCoutantMoinsQue", List::class, false,
            Triple("prix", Double::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test7 - 'FruitMariaDbDAO' overrides donneLeFruit`() {
        uml.methodCheck(
            "donneLeFruit", Fruit::class, true,
            Triple("nom", String::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test8 - 'FruitMariaDbDAO' overrides enregistreLesFruits`() {
        // UMLChecker cannot build the parameterized type List<Fruit>, so the signature is checked by reflection
        val m = FruitMariaDbDAO::class.declaredMemberFunctions.single { it.name == "enregistreLesFruits" }
        Assertions.assertEquals("fruits", m.valueParameters.single().name)
        Assertions.assertEquals(Unit::class.createType(), m.returnType)
        Assertions.assertTrue(m.isOpen)
    }

    @Test
    fun `test9 - 'FruitMariaDbDAO' overrides ajouteUnFruit`() {
        uml.methodCheck(
            "ajouteUnFruit", Unit::class, false,
            Triple("fruit", Fruit::class, false), methIsOpen = true, methIsOverride = true
        )
    }
}