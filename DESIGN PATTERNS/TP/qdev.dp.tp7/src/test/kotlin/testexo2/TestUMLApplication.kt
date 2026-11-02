package testexo2

import exo2.*
import org.junit.jupiter.api.*
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

	/***** Generated JUnit/UMLChecker Test Cases for Application *****/

class TestUMLApplication {

	lateinit var uml : UMLChecker

	@BeforeEach
	fun init() {
		uml = UMLChecker.create(Application::class)
	}

	@Test
	fun `test0 - class Application is abstract or not`() {
		uml.isAbstract(false)
	}

	@Test
	fun `test1 - class Application is open or not`() {
		uml.isOpen(false)
	}

	@Test
	fun `test2 - 'Application' extends other classe(s)`() {
		uml.extendNothing()
	}

	@Test
	fun `test3 - 'Application' has a constructor`() {
		uml.constructorCheck(paramsTypesAndNullability=arrayOf())
	}

	@Test
	fun `test4 - 'Application' has attribute(s)`() {
		uml.attributeNumber(1, Conformity.STRONG)
	}

	@Test
	fun `test5 - 'Application' has an attribute 'cocktails'`() {
		uml.attributeCheck("cocktails", MutableList::class, attIsNullable=false, attIsPrivateSet=null, attIsMutable=false, conformity=Conformity.STRONG)
	}

	@Test
	fun `test6 - 'Application' has method(s)`() {
		uml.methodNumber(2, Conformity.STRONG)
	}

	@Test
	fun `test7 - 'Application' has a method 'ajouterAlaCarte'`() {
		uml.methodCheck("ajouterAlaCarte", methParamTypesAndNullability=arrayOf(Triple("c", Cocktail::class, false)))
	}

	@Test
	fun `test8 - 'Application' has a method 'toString'`() {
		uml.methodCheck("toString", String::class, methIsOpen=true, methIsOverride=true)
	}


}
