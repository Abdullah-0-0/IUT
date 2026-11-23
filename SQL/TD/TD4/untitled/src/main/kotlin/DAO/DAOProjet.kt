package DAO

import Bean.Projet
import Bean.Travail
import Main.BD.SessionOracle
import java.sql.Connection
import java.sql.*

class DAOProjet(val ss: SessionOracle) {
    var session: SessionOracle? = null
    init {
        this.session=ss
    }

    fun select(complement : String,table: Any) {
        var res =""
        if (table !is Projet){
            throw Exception("Le type de table n'est pas Projet")
        }
        else{
            ss.getConnectionOracle().use { connection ->
                connection!!.prepareStatement("SELECT * FROM PROJET " + complement).use { statement ->
                    statement.executeQuery().use { resultSet ->
                        for ( i in 1 until resultSet.metaData.columnCount+1){
                            res += resultSet.metaData.getColumnName(i) + " | "
                        }
                        res+= "\n"
                        while (resultSet.next()){
                            for ( i in 1 until resultSet.metaData.columnCount+1){
                                res += resultSet.getString(i) + " | "
                            }
                            res += "\n"
                        }
                    }
                }
            }
            println(res)
        }

    }

     fun create(p: Projet, tr: Travail) {
        val requete1 = "INSERT INTO projet VALUES(${p.nuproj}, '${p.nomproj}', ${p.resp})"
        val requete2 = "INSERT INTO travail VALUES(${tr.nuempl}, ${tr.nuproj}, ${tr.duree})"
         try {
             session!!.conn!!.createStatement().use { st ->
                 st.executeUpdate(requete1)   /
                         st.executeUpdate(requete2)
             }
             println("creation du projet :" +
                     p.toString() +
                     " reussi\nrelie avec le travail : " +
                     tr.toString())
         }
         catch (e: SQLException){
             throw (e)
             println(e)
         }

    }


     fun update(p: Projet) {
         var res = ""
         val requete = "UPDATE projet SET nomproj='${p.nomproj}', resp=${p.resp} WHERE nuproj=${p.nuproj}"
         try {
             session!!.conn!!.createStatement().use { res+= it.executeUpdate(requete) }
             res+= " ligne modifie"
             println("modification reussi : " + res )
         }
         catch (e: SQLException){
             println(e)
         }

    }



    fun delete(p: Projet) {
        // Selon les contraintes, supprimer d'abord les lignes de travail
        var res =""
        try {
            session!!.conn!!.createStatement().use {
                res += it.executeUpdate("DELETE FROM travail WHERE nuproj=${p.nuproj}")
                res += " ligne modifier sur la table travail\n"
                res += it.executeUpdate("DELETE FROM projet WHERE nuproj=${p.nuproj}")
                res += " ligne modifier sur la table projet\n"
            }
            println("deletion du projet :" + p.toString() +"\n" + "resultat :\n" + res )
        }
        catch (e: SQLException){
            println(e)
        }

    }

    fun read(){

        //var essai = SessionOracle();
        var conn: Connection? = null
        conn= session?.getConnectionOracle()
        val requete: String="SELECT * FROM projet"
        try {
            val stmt: Statement = conn!!.createStatement()// Création d'une requete de type Statemen
            val result: ResultSet= stmt.executeQuery(requete) //Le contenu du select est dans ResultSet

            /* Parcourir le résultat du select avec la fonction next();*/
            while (result!!.next()) {

                // getting the value of the id column
                val nuproj = result.getInt("nuproj")
                val nomproj=result.getString("nomproj")
                val resp=result.getInt("resp")
                println("$nuproj | $nomproj | $resp")

            }
            result.close()
        }

        catch(e: SQLException){
            println(e.errorCode)//numéro d'erreur
            println(e.message)// message d'erreur qui provient d'oracle, trigger ou procédure
        }
    }


}
