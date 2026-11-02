import java.sql.DriverManager
import java.sql.SQLException

fun main() {

    try {
        // On se connecte à une BDD H2, ie au fichier data/persons.h2.mv.db


        val prefix = "jdbc:h2"
        val fullPath = "./data/persons.h2"
        val table = "Person"
        val url = prefix + ":" + fullPath

        val connection = DriverManager.getConnection(url)
        val statement = connection.createStatement()

        lateinit var sqlRequest: String

        // On efface la table Person
        sqlRequest = "DROP TABLE " + table
        kotlin.io.println(sqlRequest)
        statement.executeUpdate(sqlRequest)

        // On recréé une table Person
        sqlRequest = "CREATE TABLE " + table + "(" +
                "    Id INT PRIMARY KEY NOT NULL, " +
                "    Name TEXT NOT NULL," +
                "    Salary REAL NOT NULL," +
                "    Age INT NOT NULL" +
                ")"
        kotlin.io.println(sqlRequest)
        statement.executeUpdate(sqlRequest)

        // On insère des personnes
        sqlRequest = "INSERT INTO " + table + " VALUES " +
                "(31, 'Arnaud', 1045.42, 23), " +
                "(26, 'JFR', 2659.6, 20), " +
                "(34, 'JFB', 2456.6, 19), " +
                "(32, 'JFB', 2456.6, 19), " +
                "(30, 'TBLF', 2343.00, 22)," +
                "(33, 'EB', 2342.99, 22) ;"
        kotlin.io.println(sqlRequest)
        statement.executeUpdate(sqlRequest)

        // On consulte la table Person
        sqlRequest = "SELECT * FROM " + table + " WHERE Salary > 2000.0 ORDER BY Age ;"
        kotlin.io.println(sqlRequest)
        var result = statement.executeQuery(sqlRequest)
        while (result.next()) {
            kotlin.io.println("${result.getInt("Id")} : ${result.getString("Name")}")
        }

        sqlRequest = "SELECT * FROM " + table + " WHERE Name = 'JFB' ; "
        kotlin.io.println(sqlRequest)
        result = statement.executeQuery(sqlRequest)
        while (result.next()) {
            kotlin.io.println("${result.getString("Name")} (${result.getInt("Salary")})")
        }

        sqlRequest = "SELECT * FROM " + table + " WHERE Name = 'JFR' ; "
        kotlin.io.println(sqlRequest)
        result = statement.executeQuery(sqlRequest)
        while (result.next()) {
            kotlin.io.println("${result.getString("Name")} (${result.getInt("Salary")})")
        }

        statement.close()
        connection.close()

    } catch (e: SQLException) {
        kotlin.io.println(e.message)
    }


}