package testexo1

import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for TarifCombine *****/

class TestUMLTarifCombine {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(TarifCombine::class)
	}

	@Test
	fun `test0 - class TarifCombine is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class TarifCombine is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'TarifCombine' extends other classe(s)`() {
		uml.extend("TarifA")
	}

	@Test
	fun `test3 - 'TarifCombine' has a constructor`() {
		uml.constructorCheck(paramsTypesAndNullability=arrayOf(Triple("t1", TarifA::class, false), Triple("t2", TarifA::class, false)), visibility=KVisibility.PUBLIC)
	}

	@Test
	fun `test4 - 'TarifCombine' has attribute(s)`() {
		uml.attributeNumber(2, Conformity.STRONG)
	}

	@Test
	fun `test5 - 'TarifCombine' has an attribute 'tarif1'`() {
		uml.attributeCheck("tarif1", TarifA::class, attIsNullable=false, attVisibility=KVisibility.PRIVATE, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.WEAK)
	}

	@Test
	fun `test6 - 'TarifCombine' has an attribute 'tarif2'`() {
		uml.attributeCheck("tarif2", TarifA::class, attIsNullable=false, attVisibility=KVisibility.PRIVATE, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.WEAK)
	}

	@Test
	fun `test7 - 'TarifCombine' has method(s)`() {
		uml.methodNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test8 - 'TarifCombine' has a method 'calculPrix(prix'`() {
		uml.methodCheck("calculPrix", Double::class, methParamTypesAndNullability=arrayOf(Triple("prix", Double::class, false)), methIsOpen=true, methIsOverride=true)
	}
}
