import hashtables.Hashtable
import hashtables.IHashtable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.GenericType
import java.util.List
import kotlin.reflect.KClass
import kotlin.reflect.KTypeParameter
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.valueParameters

/***** UMLChecker Test Cases for Hashtable (plantuml/hashtable.plantuml) *****/

class TestUMLHashtable {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(Hashtable::class)
    }

    @Test
    fun `test0 - Hashtable is a concrete class`() {
        uml.isInterface(false)
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - Hashtable is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'Hashtable' extends HashtableA`() {
        uml.extend("HashtableA")
    }

    @Test
    fun `test3 - 'Hashtable' has attribute table`() {
        // UMLChecker cannot build Array<MutableList<E>>, so the attribute is checked by reflection
        val p = Hashtable::class.declaredMemberProperties.single { it.name == "table" }
        Assertions.assertEquals(KVisibility.PRIVATE, p.visibility)
        val array = (p.returnType.classifier as KClass<*>).java
        Assertions.assertTrue(array.isArray && List::class.java.isAssignableFrom(array.componentType))
    }

    @Test
    fun `test4 - 'Hashtable' has attribute nbCollisions`() {
        uml.attributeCheck("nbCollisions", Int::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test5 - 'Hashtable' constructeur`() {
        // UMLChecker cannot build the function type (E) -> Int, so the constructor is checked by reflection
        val c = Hashtable::class.constructors.single()
        Assertions.assertEquals(listOf("size", "hash"), c.parameters.map { it.name })
        Assertions.assertEquals(Int::class, c.parameters[0].type.classifier)
        Assertions.assertEquals(Function1::class, c.parameters[1].type.classifier)
    }

    @Test
    fun `test6 - 'Hashtable' overrides add`() {
        uml.methodCheck("add", Boolean::class, "element" to GenericType::class, isOpenOrOverride = true)
    }

    @Test
    fun `test7 - 'Hashtable' overrides contains`() {
        uml.methodCheck("contains", Boolean::class, "element" to GenericType::class, isOpenOrOverride = true)
    }

    @Test
    fun `test8 - 'Hashtable' overrides get`() {
        // UMLChecker cannot check a generic return type (E?), so the signature is checked by reflection
        val m = Hashtable::class.declaredFunctions.single { it.name == "get" }
        Assertions.assertEquals("element", m.valueParameters.single().name)
        Assertions.assertEquals(KVisibility.PUBLIC, m.visibility)
        Assertions.assertTrue(m.returnType.classifier is KTypeParameter)
        Assertions.assertTrue(m.returnType.isMarkedNullable)
        Assertions.assertTrue(m.isOpen)
    }

    @Test
    fun `test9 - 'Hashtable' overrides remove`() {
        uml.methodCheck("remove", Boolean::class, "element" to GenericType::class, isOpenOrOverride = true)
    }

    @Test
    fun `test10 - 'Hashtable' overrides avoidedCollisions`() {
        uml.methodCheck("avoidedCollisions", Int::class, false, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test11 - 'Hashtable' has companion method resize`() {
        // UMLChecker does not handle companion objects nor generic methods, so the signature is checked by reflection
        val m = Hashtable.Companion::class.declaredFunctions.single { it.name == "resize" }
        Assertions.assertEquals(KVisibility.PUBLIC, m.visibility)
        Assertions.assertEquals(listOf("hashtable", "newsize"), m.valueParameters.map { it.name })
        Assertions.assertEquals(Hashtable::class, m.valueParameters[0].type.classifier)
        Assertions.assertEquals(Int::class, m.valueParameters[1].type.classifier)
        Assertions.assertEquals(IHashtable::class, m.returnType.classifier)
    }
}
