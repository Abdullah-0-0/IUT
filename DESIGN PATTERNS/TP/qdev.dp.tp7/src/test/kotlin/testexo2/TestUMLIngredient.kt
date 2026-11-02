package testexo2

import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Ingredient *****/

class TestUMLIngredient {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Ingredient::class)
	}

	@Test
	fun `test0 - class Ingredient is abstract or not`() {
		uml.isAbstract(true)
	}

	@Test
	fun `test1 - class Ingredient is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Ingredient' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Ingredient' has a constructor`() {
		uml.constructorCheck(paramsTypesAndNullability=arrayOf(Triple("nom", String::class, false)))
	}

	@Test
	fun `test4 - 'Ingredient' has attribute(s)`() {
		uml.attributeNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test5 - 'Ingredient' has an attribute 'nom'`() {
		uml.attributeCheck("nom", String::class, attIsNullable=false, attVisibility=KVisibility.PROTECTED, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.STRONG)
	}

	@Test
	fun `test6 - 'Ingredient' has method(s)`() {
		uml.methodNumber(0, Conformity.STRONG)
	}


}
