package testexo1

import exo1.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Contexte *****/

class TestUMLContexte {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Contexte::class)
	}

	@Test
	fun `test0 - class Contexte is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Contexte is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Contexte' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Contexte' has a constructor`() {
		uml.constructorCheck(paramsTypesAndNullability=arrayOf(Triple("c", Classe::class, true), Triple("s", Saison::class, true), Triple("d", Demande::class, true)))
	}

	@Test
	fun `test4 - 'Contexte' has attribute(s)`() {
		uml.attributeNumber(3, Conformity.STRONG)
	}

	@Test
	fun `test5 - 'Contexte' has an attribute 'classe'`() {
		uml.attributeCheck("classe", Classe::class, attVisibility=KVisibility.PUBLIC, attIsNullable=true, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.STRONG)
	}

	@Test
	fun `test6 - 'Contexte' has an attribute 'saison'`() {
		uml.attributeCheck("saison", Saison::class, attVisibility=KVisibility.PUBLIC, attIsNullable=true, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.STRONG)
	}

	@Test
	fun `test7 - 'Contexte' has an attribute 'demande'`() {
		uml.attributeCheck("demande", Demande::class, attVisibility=KVisibility.PUBLIC, attIsNullable=true, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.STRONG)
	}

	@Test
	fun `test8 - 'Contexte' has method(s)`() {
		uml.methodNumber(2, Conformity.STRONG)
	}

	@Test
	fun `test9 - 'Contexte' has a method 'equals(other'`() {
		uml.methodCheck("equals", Boolean::class, methParamTypesAndNullability=arrayOf(Triple("other", Any::class, true)), methIsOpen=true, methIsOverride=true)
	}

		@Test
		fun `test10 - 'Contexte' has a method 'hashCode'`() {
			uml.methodCheck("hashCode", Int::class, methIsOpen=true, methIsOverride=true)
		}


}
