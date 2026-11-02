package testdao

import fruits.dao.FruitDAO
import fruits.model.Fruit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.valueParameters

/***** UMLChecker Test Cases for FruitDAO (plantuml/fruits-dao.plantuml) *****/

class TestUMLFruitDAO {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.Companion.create(FruitDAO::class)
    }

    @Test
    fun `test0 - FruitDAO is an interface`() {
        uml.isInterface(true)
    }

    @Test
    fun `test1 - FruitDAO has 5 methods`() {
        uml.methodNumber(5, conformity = Conformity.STRONG)
    }

    @Test
    fun `test2 - FruitDAO has method donneLesFruits`() {
        uml.methodCheck("donneLesFruits", List::class, false, methIsAbstract = true)
    }

    @Test
    fun `test3 - FruitDAO has method donneLesFruitsCoutantMoinsQue`() {
        uml.methodCheck(
            "donneLesFruitsCoutantMoinsQue",
            List::class,
            false,
            Triple("prix", Double::class, false),
            methIsAbstract = true
        )
    }

    @Test
    fun `test4 - FruitDAO has method donneLeFruit`() {
        uml.methodCheck("donneLeFruit", Fruit::class, true, Triple("nom", String::class, false), methIsAbstract = true)
    }

    @Test
    fun `test5 - FruitDAO has method enregistreLesFruits`() {
        // UMLChecker cannot build the parameterized type List<Fruit>, so the signature is checked by reflection
        val m = FruitDAO::class.declaredFunctions.single { it.name == "enregistreLesFruits" }
        Assertions.assertEquals(1, m.valueParameters.size)
        Assertions.assertEquals("fruits", m.valueParameters[0].name)
        Assertions.assertEquals(
            List::class.createType(listOf(KTypeProjection.Companion.invariant(Fruit::class.createType()))),
            m.valueParameters[0].type
        )
        Assertions.assertEquals(Unit::class.createType(), m.returnType)
        Assertions.assertTrue(m.isAbstract)
    }

    @Test
    fun `test6 - FruitDAO has method ajouteUnFruit`() {
        uml.methodCheck(
            "ajouteUnFruit",
            Unit::class,
            false,
            Triple("fruit", Fruit::class, false),
            methIsAbstract = true
        )
    }
}