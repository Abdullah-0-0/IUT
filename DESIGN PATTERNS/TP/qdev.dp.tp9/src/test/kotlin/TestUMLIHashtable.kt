import hashtables.IHashtable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import univ.nantes.umlchecker.GenericType
import kotlin.reflect.KTypeParameter
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.valueParameters

/***** UMLChecker Test Cases for IHashtable (plantuml/hashtable.plantuml) *****/

class TestUMLIHashtable {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(IHashtable::class)
    }

    @Test
    fun `test0 - IHashtable is an interface`() {
        uml.isInterface(true)
    }

    @Test
    fun `test1 - IHashtable has 6 methods`() {
        uml.methodNumber(6, conformity = Conformity.STRONG)
    }

    @Test
    fun `test2 - IHashtable has method add`() {
        uml.methodCheck(
            "add",
            Boolean::class,
            false,
            Triple("element", GenericType::class, false),
            methIsAbstract = true
        )
    }

    @Test
    fun `test3 - IHashtable has method contains`() {
        uml.methodCheck(
            "contains",
            Boolean::class,
            false,
            Triple("element", GenericType::class, false),
            methIsAbstract = true
        )
    }

    @Test
    fun `test4 - IHashtable has method get`() {
        // UMLChecker cannot check a generic return type (E?), so the signature is checked by reflection
        val m = IHashtable::class.declaredFunctions.single { it.name == "get" }
        Assertions.assertEquals("element", m.valueParameters.single().name)
        Assertions.assertTrue(m.valueParameters.single().type.classifier is KTypeParameter)
        Assertions.assertTrue(m.returnType.classifier is KTypeParameter)
        Assertions.assertTrue(m.returnType.isMarkedNullable)
        Assertions.assertTrue(m.isAbstract)
    }

    @Test
    fun `test5 - IHashtable has method remove`() {
        uml.methodCheck(
            "remove",
            Boolean::class,
            false,
            Triple("element", GenericType::class, false),
            methIsAbstract = true
        )
    }

    @Test
    fun `test6 - IHashtable has method nbElements`() {
        uml.methodCheck("nbElements", Int::class, false, methIsAbstract = true)
    }

    @Test
    fun `test7 - IHashtable has method avoidedCollisions with a default implementation`() {
        uml.methodCheck("avoidedCollisions", Int::class, false, methIsOpen = true, methIsAbstract = false)
    }
}
