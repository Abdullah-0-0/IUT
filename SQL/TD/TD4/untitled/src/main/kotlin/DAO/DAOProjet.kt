package DAO

import Bean.Projet
import Main.BD.SessionOracle

class DAOProjet(private val bd : SessionOracle = SessionOracle()) : DAO(){
    override fun select(requete : String,table: Any) {
        var res =""
        if (table !is Projet){
            throw Exception("Le type de table n'est pas Projet")
        }
        else{
            bd.getConnectionOracle().use { connection ->
                connection!!.prepareStatement("SELECT * FROM PROJET").use { statement ->
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

    override fun insert(requete : String,table: Any) {
        if (table !is Projet){
            throw Exception("Le type de table n'est pas Projet")
        }
        else{
            bd.getConnectionOracle().use { connection ->
                connection!!.prepareStatement("SELECT * FROM $table").use { statement ->
                    statement.executeQuery().use { resultSet ->
                        while (resultSet.next()){}
                    }
                }
            }
        }

    }

    override fun update(requete : String,table: Any) {
        if (table !is Projet){
            throw Exception("Le type de table n'est pas Projet")
        }
        else{
            bd.getConnectionOracle().use { connection ->
                connection!!.prepareStatement("SELECT * FROM $table").use { statement ->
                    statement.executeQuery().use { resultSet ->
                        while (resultSet.next()){}
                    }
                }
            }
        }

    }

    override fun delete(requete : String,table: Any) {
        if (table !is Projet){
            throw Exception("Le type de table n'est pas Projet")
        }
        else{
            bd.getConnectionOracle().use { connection ->
                connection!!.prepareStatement("SELECT * FROM $table").use { statement ->
                    statement.executeQuery().use { resultSet ->
                        while (resultSet.next()){}
                    }
                }
            }
        }

    }

    override fun read() {
        select("Select * from projet", Projet())
    }
}