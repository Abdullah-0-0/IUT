package testexo1

import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for TarifA *****/

class TestUMLTarifA {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(TarifA::class)
	}

	@Test
	fun `test0 - class TarifA is abstract or not`() {
		uml.isAbstract(true)
	}

	@Test
	fun `test1 - class TarifA is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'TarifA' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'TarifA' has a constructor`() {
		uml.constructorCheck(paramsTypesAndNullability=arrayOf(), visibility=KVisibility.PROTECTED)
	}

	@Test
	fun `test4 - 'TarifA' has attribute(s)`() {
		uml.attributeNumber(0, Conformity.STRONG)
	}

	@Test
	fun `test6 - 'TarifA' has method(s)`() {
		uml.methodNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test7 - 'TarifA' has a method 'calculPrix'`() {
		uml.methodCheck("calculPrix", Double::class, methParamTypesAndNullability=arrayOf(Triple("prix", Double::class, false)), methIsAbstract=true)
	}


}
