package testexo1

import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for TarifDemande *****/

class TestUMLTarifDemande {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(TarifDemande::class)
	}

	@Test
	fun `test0 - class TarifDemande is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class TarifDemande is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'TarifDemande' extends other classe(s)`() {
		uml.extend("TarifA")
	}

	@Test
	fun `test3 - 'TarifDemande' has a constructor`() {
		uml.constructorCheck(paramsTypesAndNullability=arrayOf(Triple("d", Demande::class, false)))
	}

	@Test
	fun `test4 - 'TarifDemande' has attribute(s)`() {
		uml.attributeNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test5 - 'TarifDemande' has an attribute 'demande'`() {
		uml.attributeCheck("demande", Demande::class, attIsNullable=false, attVisibility=KVisibility.PRIVATE, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.WEAK)
	}

	@Test
	fun `test6 - 'TarifDemande' has method(s)`() {
		uml.methodNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test7 - 'TarifDemande' has a method 'calculPrix'`() {
		uml.methodCheck("calculPrix", Double::class, methParamTypesAndNullability=arrayOf(Triple("prix", Double::class, false)), methIsOpen=true, methIsOverride=true)
	}


}
