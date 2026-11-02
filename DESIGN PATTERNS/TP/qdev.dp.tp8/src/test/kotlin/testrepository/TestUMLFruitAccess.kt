package testrepository

import fruits.FruitAccess
import fruits.model.Fruit
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity

/***** UMLChecker Test Cases for FruitAccess (plantuml/fruits-repository.plantuml) *****/

class TestUMLFruitAccess {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(FruitAccess::class)
    }

    @Test
    fun `test0 - FruitAccess is an interface`() {
        uml.isInterface(true)
    }

    @Test
    fun `test1 - FruitAccess has 6 methods`() {
        uml.methodNumber(6, conformity = Conformity.STRONG)
    }

    @Test
    fun `test2 - FruitAccess has method recharge`() {
        uml.methodCheck("recharge", Unit::class, false, methIsAbstract = true)
    }

    @Test
    fun `test3 - FruitAccess has method ajouteUnFruit`() {
        uml.methodCheck(
            "ajouteUnFruit",
            Unit::class,
            false,
            Triple("fruit", Fruit::class, false),
            methIsAbstract = true
        )
    }

    @Test
    fun `test4 - FruitAccess has method supprimeUnFruit`() {
        uml.methodCheck(
            "supprimeUnFruit",
            Boolean::class,
            false,
            Triple("nom", String::class, false),
            methIsAbstract = true
        )
    }

    @Test
    fun `test5 - FruitAccess has method donneLesFruitsQuantiteSupp`() {
        uml.methodCheck(
            "donneLesFruitsQuantiteSupp",
            List::class,
            false,
            Triple("quantite", Int::class, false),
            methIsAbstract = true
        )
    }

    @Test
    fun `test6 - FruitAccess has method iterateurFruitsAlphabetique`() {
        uml.methodCheck("iterateurFruitsAlphabetique", Iterator::class, false, methIsAbstract = true)
    }

    @Test
    fun `test7 - FruitAccess has method iterateurFruitsPlusAuMoinCher`() {
        uml.methodCheck("iterateurFruitsPlusAuMoinCher", Iterator::class, false, methIsAbstract = true)
    }
}
