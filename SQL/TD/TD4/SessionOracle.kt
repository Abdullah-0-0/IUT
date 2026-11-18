package BD

import java.sql.*
import java.util.*


class SessionOracle(username : String,password : String) {
    var conn: Connection? = null
    var username = username
    var password = password

    init {


        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance()
            conn = DriverManager.getConnection("jdbc:oracle:thin:@172.26.82.31:1521:xe",username, password)
            println("connexion réussie")
        } catch (ex: SQLException) {
            println(ex.errorCode)//numéro d'erreur
            println(ex.message)// message d'erreur qui provient d'oracle,
        }
    }

    fun getConnectionOracle(): Connection? = conn
}