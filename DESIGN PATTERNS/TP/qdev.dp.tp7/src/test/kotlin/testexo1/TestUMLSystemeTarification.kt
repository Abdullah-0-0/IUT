package testexo1

import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for SystemeTarification *****/

class TestUMLSystemeTarification {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(SystemeTarification::class)
	}

	@Test
	fun `test0 - class SystemeTarification is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class SystemeTarification is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'SystemeTarification' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'SystemeTarification' has a constructor`() {
		uml.constructorCheck(paramsTypesAndNullability=arrayOf())
	}

	@Test
	fun `test4 - 'SystemeTarification' has attribute(s)`() {
		uml.attributeNumber(2, Conformity.STRONG)
	}

	@Test
	fun `test5 - 'SystemeTarification' has an attribute 'contexte'`() {
		uml.attributeCheck("contexte", Contexte::class, attVisibility=KVisibility.PUBLIC, attIsNullable=true, attIsPrivateSet=false, attIsMutable=true, conformity=Conformity.STRONG)
	}

	@Test
	fun `test6 - 'SystemeTarification' has an attribute 'tarif'`() {
		uml.attributeCheck("tarif", TarifA::class, attVisibility=KVisibility.PUBLIC, attIsNullable=true, attIsPrivateSet=false, attIsMutable=true, conformity=Conformity.STRONG)
	}

	@Test
	fun `test7 - 'SystemeTarification' has method(s)`() {
		uml.methodNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test8 - 'SystemeTarification' has a method 'calculPrix'`() {
		uml.methodCheck("calculPrix", Double::class, methParamTypesAndNullability=arrayOf(Triple("prix", Double::class, false), Triple("contexte", Contexte::class, false)))
	}


}
