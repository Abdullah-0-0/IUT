package testrepository

import fruits.FruitRepository
import fruits.dao.FruitDAO
import fruits.model.Fruit
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import kotlin.reflect.KVisibility

/***** UMLChecker Test Cases for FruitRepository (plantuml/fruits-repository.plantuml) *****/

class TestUMLFruitRepository {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(FruitRepository::class)
    }

    @Test
    fun `test0 - FruitRepository is a concrete class`() {
        uml.isInterface(false)
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - FruitRepository is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'FruitRepository' realizes FruitAccess`() {
        uml.extend("FruitAccess")
    }

    @Test
    fun `test3 - 'FruitRepository' constructeur`() {
        uml.constructorCheck(Triple("fruitDAO", FruitDAO::class, false))
    }

    @Test
    fun `test4 - 'FruitRepository' has attribute fruitDAO`() {
        uml.attributeCheck("fruitDAO", FruitDAO::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test5 - 'FruitRepository' has attribute fruits`() {
        uml.attributeCheck("fruits", List::class, attIsNullable = false, attVisibility = KVisibility.PUBLIC)
    }

    @Test
    fun `test6 - 'FruitRepository' overrides recharge`() {
        uml.methodCheck("recharge", Unit::class, false, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test7 - 'FruitRepository' overrides ajouteUnFruit`() {
        uml.methodCheck(
            "ajouteUnFruit", Unit::class, false,
            Triple("fruit", Fruit::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test8 - 'FruitRepository' overrides supprimeUnFruit`() {
        uml.methodCheck(
            "supprimeUnFruit", Boolean::class, false,
            Triple("nom", String::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test9 - 'FruitRepository' overrides donneLesFruitsQuantiteSupp`() {
        uml.methodCheck(
            "donneLesFruitsQuantiteSupp", List::class, false,
            Triple("quantite", Int::class, false), methIsOpen = true, methIsOverride = true
        )
    }

    @Test
    fun `test10 - 'FruitRepository' overrides iterateurFruitsAlphabetique`() {
        uml.methodCheck("iterateurFruitsAlphabetique", Iterator::class, false, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test11 - 'FruitRepository' overrides iterateurFruitsPlusAuMoinCher`() {
        uml.methodCheck(
            "iterateurFruitsPlusAuMoinCher",
            Iterator::class,
            false,
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test12 - 'FruitRepository' has method changeLeDAO`() {
        uml.methodCheck("changeLeDAO", Unit::class, false, Triple("nouveauDAO", FruitDAO::class, false))
    }
}
