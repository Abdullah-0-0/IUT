import netprime.businesslayer.NetPrime
import netprime.datalayer.datamodel.medias.Media
import netprime.datalayer.datamodel.users.GENDER
import netprime.datalayer.datamodel.users.NAT
import netprime.datalayer.datamodel.users.Name
import netprime.datalayer.datamodel.users.User
import netprime.datalayer.iMediaDAO
import netprime.datalayer.iUserDAO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import univ.nantes.UMLChecker
import univ.nantes.umlchecker.Conformity
import kotlin.reflect.KVisibility

/***** Generated JUnit/UMLChecker Test Cases for NetPrime *****/

class TestUMLNetPrime {

    lateinit var uml: UMLChecker

    @BeforeEach
    fun init() {
        uml = UMLChecker.create(NetPrime::class)
    }

    @Test
    fun `test0 - class NetPrime is abstract or not`() {
        uml.isAbstract(false)
    }

    @Test
    fun `test1 - class NetPrime is open or not`() {
        uml.isOpen(false)
    }

    @Test
    fun `test2 - 'NetPrime' extends other classe(s)`() {
        uml.extend("iUserRepository", "iMediaRepository")
    }

    @Test
    fun `test6 - 'Netprime' constructeur`() {
        uml.constructorCheck(
            paramsTypesAndNullability = arrayOf(
                Triple("userDao", iUserDAO::class, false),
                Triple("mediaDAO", iMediaDAO::class, false)
            )
        )
    }


    @Test
    fun `test3 - 'NetPrime' has attribute(s)`() {
        uml.attributeNumber(7, conformity = Conformity.STRONG)
    }

    @Test
    fun `test4 - 'NetPrime' has method(s)`() {
        uml.methodNumber(21, conformity = Conformity.STRONG)
    }


    @Test
    fun `test6 - 'NetPrime' has a method 'nbUsers'`() {
        uml.methodCheck("nbUsers", Int::class, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test7 - 'NetPrime' has a method 'listByNat'`() {
        uml.methodCheck(
            "listByNat",
            List::class,
            false,
            Triple("nat", NAT::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test8 - 'NetPrime' has a method 'contains'`() {
        uml.methodCheck(
            "contains",
            Boolean::class,
            false,
            Triple("user", User::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test9 - 'NetPrime' has a method 'delete'`() {
        uml.methodCheck(
            "delete",
            Unit::class,
            false,
            Triple("media", Media::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test8 - 'NetPrime' has a method 'containsByLastname'`() {
        uml.methodCheck(
            "containsByLastname",
            Boolean::class,
            false,
            Triple("lastname", String::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test9 - 'NetPrime' has a method 'deleteByEmail'`() {
        uml.methodCheck(
            "deleteByEmail",
            Unit::class,
            false,
            Triple("email", String::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }


    @Test
    fun `test10 - 'NetPrime' has a method 'listByGender'`() {
        uml.methodCheck(
            "listByGender",
            List::class,
            false,
            Triple("gender", GENDER::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test11 - 'NetPrime' has a method 'listByFirstname'`() {
        uml.methodCheck(
            "listByFirstname",
            List::class,
            false,
            Triple("firstname", String::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test12 - 'NetPrime' has a method 'giveLogin'`() {
        uml.methodCheck(
            "giveLogin",
            Pair::class,
            false,
            Triple("name", Name::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test16 - 'NetPrime' has a method 'nbTotalMedias'`() {
        uml.methodCheck("nbTotalMedias", Int::class, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test17 - 'NetPrime' has a method 'nbMovies'`() {
        uml.methodCheck("nbMovies", Int::class, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test18 - 'NetPrime' has a method 'nbSeries'`() {
        uml.methodCheck("nbSeries", Int::class, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test19 - 'NetPrime' has a method 'listAllMedias'`() {
        uml.methodCheck("listAllMedias", List::class, methIsOpen = true, methIsOverride = true)
    }

    @Test
    fun `test20 - 'NetPrime' has a method 'isSerieId'`() {
        uml.methodCheck(
            "isSerieId",
            Boolean::class,
            false,
            Triple("id", String::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test21 - 'NetPrime' has a method 'episodesFrom'`() {
        uml.methodCheck(
            "episodesFrom",
            List::class,
            false,
            Triple("title", String::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test22 - 'NetPrime' has a method 'listByTitleKeyword'`() {
        uml.methodCheck(
            "listByTitleKeyword",
            List::class,
            false,
            Triple("keyword", String::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test23 - 'NetPrime' has a method 'listByCrew'`() {
        uml.methodCheck(
            "listByCrew",
            List::class,
            false,
            Triple("person", String::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test24 - 'NetPrime' has a method 'loadUsersFrom'`() {
        uml.methodCheck(
            "loadUsersFrom",
            Unit::class,
            false,
            Triple("ressource", String::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test24 - 'NetPrime' has a method 'loadSeriesFrom'`() {
        uml.methodCheck(
            "loadSeriesFrom",
            Unit::class,
            false,
            Triple("ressource", String::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test24 - 'NetPrime' has a method 'loadMoviesFrom'`() {
        uml.methodCheck(
            "loadMoviesFrom",
            Unit::class,
            false,
            Triple("ressource", String::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test24 - 'NetPrime' has a method 'loadEpisodesFrom'`() {
        uml.methodCheck(
            "loadEpisodesFrom",
            Unit::class,
            false,
            Triple("ressource", String::class, false),
            methIsOpen = true,
            methIsOverride = true
        )
    }

    @Test
    fun `test4 - 'NetPrime' has attribute medias`() {
        uml.attributeCheck("medias", Set::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test4 - 'NetPrime' has attribute userDao`() {
        uml.attributeCheck("userDao", iUserDAO::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test4 - 'NetPrime' has attribute mediaDAO`() {
        uml.attributeCheck("mediaDAO", iMediaDAO::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test4 - 'NetPrime' has attribute movies`() {
        uml.attributeCheck("movies", List::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test4 - 'NetPrime' has attribute series`() {
        uml.attributeCheck("series", List::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test4 - 'NetPrime' has attribute episodes`() {
        uml.attributeCheck("episodes", List::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

    @Test
    fun `test4 - 'NetPrime' has attribute users`() {
        uml.attributeCheck("users", Set::class, attIsNullable = false, attVisibility = KVisibility.PRIVATE)
    }

}
