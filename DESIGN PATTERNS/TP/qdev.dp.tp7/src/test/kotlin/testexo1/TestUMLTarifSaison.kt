package testexo1

import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for TarifSaison *****/

class TestUMLTarifSaison {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(TarifSaison::class)
	}

	@Test
	fun `test0 - class TarifSaison is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class TarifSaison is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'TarifSaison' extends other classe(s)`() {
		uml.extend("TarifA")
	}

	@Test
	fun `test3 - 'TarifSaison' has a constructor`() {
		uml.constructorCheck(paramsTypesAndNullability=arrayOf(Triple("s", Saison::class, false)))
	}

	@Test
	fun `test4 - 'TarifSaison' has attribute(s)`() {
		uml.attributeNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test5 - 'TarifSaison' has an attribute 'saison'`() {
		uml.attributeCheck("saison", Saison::class, attIsNullable=false, attVisibility=KVisibility.PRIVATE, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.WEAK)
	}

	@Test
	fun `test6 - 'TarifSaison' has method(s)`() {
		uml.methodNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test7 - 'TarifSaison' has a method 'calculPrix'`() {
		uml.methodCheck("calculPrix", Double::class, methParamTypesAndNullability=arrayOf(Triple("prix", Double::class, false)), methIsOpen=true, methIsOverride=true)
	}


}
