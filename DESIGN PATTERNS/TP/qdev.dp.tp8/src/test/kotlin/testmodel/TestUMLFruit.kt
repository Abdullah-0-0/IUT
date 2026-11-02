package testmodel

import fruits.exceptions.FruitDejaPresentException
import fruits.exceptions.ImportFruitsException
import fruits.model.Fruit
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

/***** UMLChecker Test Cases for Fruit and the exceptions (plantuml/fruits-dao.plantuml) *****/

class TestUMLFruit {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.Companion.create(Fruit::class)
    }

    @Test
    fun `Fruit has attribute nom`() {
        uml.attributeCheck("nom", String::class, attIsNullable = false, attVisibility = KVisibility.PUBLIC)
    }

    @Test
    fun `Fruit has attribute prix`() {
        uml.attributeCheck("prix", Double::class, attIsNullable = false, attVisibility = KVisibility.PUBLIC)
    }

    @Test
    fun `Fruit has attribute quantite`() {
        uml.attributeCheck("quantite", Int::class, attIsNullable = false, attVisibility = KVisibility.PUBLIC)
    }

    @Test
    fun `Fruit constructor`() {
        uml.constructorCheck(
            Triple("nom", String::class, false),
            Triple("prix", Double::class, false),
            Triple(
                "quantite", Int::class, false
            )
        )
    }

    @Test
    fun `FruitDejaPresentException exists and is an Exception`() {
        val uml = UMLChecker.Companion.create(FruitDejaPresentException::class)
        uml.isAbstract(false)
        uml.extend("Exception")
    }

    @Test
    fun `ImportFruitsException exists and is an Exception`() {
        val uml = UMLChecker.Companion.create(ImportFruitsException::class)
        uml.isAbstract(false)
        uml.extend("Exception")
    }
}