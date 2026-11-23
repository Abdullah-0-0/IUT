package DAO

import Bean.*
import Main.BD.SessionOracle
import java.sql.SQLException

class DAOProjetBis(private val session: SessionOracle) {

    fun create(p: Projet, tr: Travail) {
        println("\ncreation avec la methode prepareStatement")
        try {
            session.conn!!.prepareStatement("INSERT INTO projet VALUES(?,?,?)").use { statement ->
                statement.setInt(1, p.nuproj)
                statement.setString(2, p.nomproj)
                statement.setInt(3, p.resp)
                statement.executeUpdate()
                println("insertion succes dans la table projet avec les valeurs " +
                        "nuproj = " +p.nuproj +","+
                        "nomproj = " + p.nomproj+","+
                        "resp = " + p.resp
                )
            }
            session.conn!!.prepareStatement("INSERT INTO travail VALUES(?,?,?)").use { statement ->
                statement.setInt(1, tr.nuempl)
                statement.setInt(2, tr.nuproj)
                statement.setInt(3, tr.duree)
                statement.executeUpdate()
                println("insertion succes dans la table travail avec les valeurs "+
                        "nuproj = " + tr.nuproj +" , "+
                        "nuempl = " + tr.nuempl +" , " +
                        "duree = " + tr.duree
                )
            }
        }
        catch(e: SQLException) {
            println("erreur lors de la  creation des tables")
            println(e)
        }

    }

    fun update(p: Projet) {
        println("\nupdate avec la methode prepareStatement")
        try {
            session.conn!!.prepareStatement("UPDATE projet SET nomproj=?, resp=? WHERE nuproj=?").use { statement ->
                statement.setString(1, p.nomproj)
                statement.setInt(2, p.resp)
                statement.setInt(3, p.nuproj)
                statement.executeUpdate()
                println("insertion succes dans la table projet avec les valeurs "+
                        "nuproj = " + p.nuproj +" , "+
                        "nomproj = " + p.nomproj +" , " +
                        "resp = " + p.resp
                )
            }
        }
        catch(e: SQLException) {
            println("erreur lors de la MAJ")
            println(e)
        }

    }

    fun delete(p: Projet) {
        println("\ndeletion avec la table projet avec la methode prepareStatement")
        try {
            session.conn!!.prepareStatement("DELETE FROM travail WHERE nuproj=?").use { statement ->
                statement.setInt(1, p.nuproj); statement.executeUpdate()
                println("delete succes de la table travail avec le nuproj : "+p.nuproj );
            }
            session.conn!!.prepareStatement("DELETE FROM projet WHERE nuproj=?").use { statement ->
                statement.setInt(1, p.nuproj); statement.executeUpdate()
                println("delete succes de la table projet avec le nuproj : "+p.nuproj );
            }
        }
        catch (e : SQLException) {
            println("erreur lors de la suppression")
            println(e)
        }

    }

    fun read() {
        var res = ""
        session.conn!!.prepareStatement("SELECT * FROM PROJET").use { statement ->
            statement.executeQuery().use { resultSet ->
                for (i in 1 until resultSet.metaData.columnCount+1){
                    res+= resultSet.metaData.getColumnName(i) + " | "
                }
                res+="\n"
                while (resultSet.next()){
                    for (i in 1 until resultSet.metaData.columnCount+1){
                        res+= resultSet.getString(i) + " | "
                    }
                    res+="\n"
                }
            }
        }
        println(res)
    }
}