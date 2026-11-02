package testexo2

import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Cocktail *****/

class TestUMLCocktail {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Cocktail::class)
	}

	@Test
	fun `test0 - class Cocktail is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Cocktail is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Cocktail' extends other classe(s)`() {
		uml.extend("Ingredient")
	}

	@Test
	fun `test3 - 'Cocktail' has a constructor`() {
		uml.constructorCheck(paramsTypesAndNullability=arrayOf(Triple("n", String::class, false), Triple("ingredients", MutableList::class, false)))
	}

	@Test
	fun `test4 - 'Cocktail' has attribute(s)`() {
		uml.attributeNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test5 - 'Cocktail' has an attribute 'ingredients'`() {
		uml.attributeCheck("ingredients", MutableList::class, attIsNullable=false, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.STRONG)
	}

	@Test
	fun `test6 - 'Cocktail' has method(s)`() {
		uml.methodNumber(2, Conformity.STRONG)
	}

	@Test
	fun `test7 - 'Cocktail' has a method 'ajouter'`() {
		uml.methodCheck("ajouter", methParamTypesAndNullability=arrayOf(Triple("ingredient", Ingredient::class, false)))
	}

	@Test
	fun `test8 - 'Cocktail' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, methIsOpen=true, methIsOverride=true)
	}


}
