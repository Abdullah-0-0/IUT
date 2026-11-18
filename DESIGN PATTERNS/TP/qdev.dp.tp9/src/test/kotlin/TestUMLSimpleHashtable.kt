import hashtables.SimpleHashtable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.GenericType
import kotlin.reflect.KTypeParameter
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.valueParameters

/***** UMLChecker Test Cases for SimpleHashtable (plantuml/hashtable.plantuml) *****/

class TestUMLSimpleHashtable {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(SimpleHashtable::class)
    }

    @Test
    fun `test0 - SimpleHashtable is a concrete class`() {
        uml.isInterface(false)
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - SimpleHashtable is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'SimpleHashtable' extends HashtableA`() {
        uml.extend("HashtableA")
    }

    @Test
    fun `test3 - 'SimpleHashtable' has attribute table`() {
        // UMLChecker cannot build Array<Any?>, so the attribute is checked by reflection
        val p = SimpleHashtable::class.declaredMemberProperties.single { it.name == "table" }
        Assertions.assertEquals(KVisibility.PRIVATE, p.visibility)
        Assertions.assertEquals(Array<Any?>::class, p.returnType.classifier)
    }

    @Test
    fun `test4 - 'SimpleHashtable' constructeur`() {
        // UMLChecker cannot build the function type (E) -> Int, so the constructor is checked by reflection
        val c = SimpleHashtable::class.constructors.single()
        Assertions.assertEquals(listOf("size", "hash"), c.parameters.map { it.name })
        Assertions.assertEquals(Int::class, c.parameters[0].type.classifier)
        Assertions.assertEquals(Function1::class, c.parameters[1].type.classifier)
    }

    @Test
    fun `test5 - 'SimpleHashtable' has method get(index)`() {
        // UMLChecker cannot check a generic return type (E), so the signature is checked by reflection
        val m =
            SimpleHashtable::class.declaredFunctions.single { it.name == "get" && it.valueParameters.single().name == "index" }
        Assertions.assertEquals(KVisibility.PRIVATE, m.visibility)
        Assertions.assertEquals(Int::class, m.valueParameters.single().type.classifier)
        Assertions.assertTrue(m.returnType.classifier is KTypeParameter)
        Assertions.assertFalse(m.returnType.isMarkedNullable)
    }

    @Test
    fun `test6 - 'SimpleHashtable' overrides add`() {
        uml.methodCheck("add", Boolean::class, "element" to GenericType::class, isOpenOrOverride = true)
    }

    @Test
    fun `test7 - 'SimpleHashtable' overrides contains`() {
        uml.methodCheck("contains", Boolean::class, "element" to GenericType::class, isOpenOrOverride = true)
    }

    @Test
    fun `test8 - 'SimpleHashtable' overrides get(element)`() {
        val m =
            SimpleHashtable::class.declaredFunctions.single { it.name == "get" && it.valueParameters.single().name == "element" }
        Assertions.assertEquals(KVisibility.PUBLIC, m.visibility)
        Assertions.assertTrue(m.returnType.classifier is KTypeParameter)
        Assertions.assertTrue(m.returnType.isMarkedNullable)
        Assertions.assertTrue(m.isOpen)
    }

    @Test
    fun `test9 - 'SimpleHashtable' overrides remove`() {
        uml.methodCheck("remove", Boolean::class, "element" to GenericType::class, isOpenOrOverride = true)
    }
}
