package testexo1

import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for TarifClasse *****/

class TestUMLTarifClasse {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(TarifClasse::class)
	}

	@Test
	fun `test0 - class TarifClasse is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class TarifClasse is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'TarifClasse' extends other classe(s)`() {
		uml.extend("TarifA")
	}

	@Test
	fun `test3 - 'TarifClasse' has a constructor`() {
		uml.constructorCheck(paramsTypesAndNullability=arrayOf(Triple("c", Classe::class, false)))
	}

	@Test
	fun `test4 - 'TarifClasse' has attribute(s)`() {
		uml.attributeNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test5 - 'TarifClasse' has an attribute 'classe'`() {
		uml.attributeCheck("classe", Classe::class, attIsNullable=false, attVisibility=KVisibility.PRIVATE, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.WEAK)
	}

	@Test
	fun `test6 - 'TarifClasse' has method(s)`() {
		uml.methodNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test7 - 'TarifClasse' has a method 'calculPrix'`() {
		uml.methodCheck("calculPrix", Double::class, methParamTypesAndNullability=arrayOf(Triple("prix", Double::class, false)), methIsOpen=true, methIsOverride=true)
	}


}
