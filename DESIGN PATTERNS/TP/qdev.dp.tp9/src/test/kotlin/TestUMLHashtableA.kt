import hashtables.HashtableA
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.GenericType
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredMemberProperties

/***** UMLChecker Test Cases for HashtableA (plantuml/hashtable.plantuml) *****/

class TestUMLHashtableA {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(HashtableA::class)
    }

    @Test
    fun `test0 - HashtableA is an abstract class`() {
        uml.isInterface(false)
        uml.isAbstract(true)
    }

    @Test
    fun `test1 - 'HashtableA' realizes IHashtable`() {
        uml.extend("IHashtable")
    }

    @Test
    fun `test2 - 'HashtableA' has attribute size`() {
        uml.attributeCheck("size", Int::class, attIsNullable = false, attVisibility = KVisibility.PROTECTED)
    }

    @Test
    fun `test3 - 'HashtableA' has attribute hash`() {
        // UMLChecker cannot build the function type (E) -> Int, so the attribute is checked by reflection
        val p = HashtableA::class.declaredMemberProperties.single { it.name == "hash" }
        Assertions.assertEquals(KVisibility.PROTECTED, p.visibility)
        Assertions.assertEquals(Function1::class, p.returnType.classifier)
        Assertions.assertFalse(p.returnType.isMarkedNullable)
    }

    @Test
    fun `test4 - 'HashtableA' has attribute nbElements`() {
        uml.attributeCheck("nbElements", Int::class, attIsNullable = false, attVisibility = KVisibility.PROTECTED)
        val p = HashtableA::class.declaredMemberProperties.single { it.name == "nbElements" }
        Assertions.assertTrue(p is KMutableProperty<*>)
    }

    @Test
    fun `test5 - 'HashtableA' constructeur`() {
        // UMLChecker cannot build the function type (E) -> Int, so the constructor is checked by reflection
        val c = HashtableA::class.constructors.single()
        Assertions.assertEquals(listOf("size", "hash"), c.parameters.map { it.name })
        Assertions.assertEquals(Int::class, c.parameters[0].type.classifier)
        Assertions.assertEquals(Function1::class, c.parameters[1].type.classifier)
    }

    @Test
    fun `test6 - 'HashtableA' overrides nbElements`() {
        uml.methodCheck("nbElements", Int::class, false, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test7 - 'HashtableA' has method index`() {
        uml.methodCheck(
            "index", Int::class, false,
            Triple("element", GenericType::class, false), methVisibility = KVisibility.PROTECTED
        )
    }
}
