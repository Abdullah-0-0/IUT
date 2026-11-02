package testexo2

import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for IngredientBasique *****/

class TestUMLIngredientBasique {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(IngredientBasique::class)
	}

	@Test
	fun `test0 - class IngredientBasique is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class IngredientBasique is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'IngredientBasique' extends other classe(s)`() {
		uml.extend("Ingredient")
	}

	@Test
	fun `test3 - 'IngredientBasique' has a constructor`() {
		uml.constructorCheck(paramsTypesAndNullability=arrayOf(Triple("n", String::class, false), Triple("q", Double::class, false)))
	}

	@Test
	fun `test4 - 'IngredientBasique' has attribute(s)`() {
		uml.attributeNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test5 - 'IngredientBasique' has an attribute 'quantite'`() {
		uml.attributeCheck("quantite", Double::class, attIsNullable=false, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.STRONG)
	}

	@Test
	fun `test6 - 'IngredientBasique' has method(s)`() {
		uml.methodNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test7 - 'IngredientBasique' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, methIsOpen=true, methIsOverride=true)
	}


}
