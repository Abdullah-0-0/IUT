package Application.Main

import Bean.Projet
import Bean.Travail
import DAO.DAO
import DAO.DAOProjet
import DAO.DAOProjetBis
import ENUM.TypeDao
import Main.BD.SessionOracle

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val session = SessionOracle()

    val daoStatement = DAOProjet(session)
    val daoPrepaStatement = DAOProjetBis(session)

    val travail = Travail( 89 , 999,9)
    val projet = Projet(999,"test",89)
    val typeDao = TypeDao.CallableStatement
    try {
        if (typeDao == TypeDao.Statement) {
            println("Avant : ${daoStatement.read()}")

            daoStatement.create(projet,travail)
            println("Après create : ${daoStatement.read()}")

            daoStatement.update(Projet(999,"abdulah",99))
            println("Après update : ${daoStatement.read()}")


            daoStatement.delete(projet)
            println("Après delete : ${daoStatement.read()}")
        }
        if (typeDao == TypeDao.PrepareStatement) {

            println("Avant : ${daoPrepaStatement.read()}")

            daoPrepaStatement.create(projet,travail)
            println("Après create : ${daoPrepaStatement.read()}")

            daoPrepaStatement.update(Projet(999,"abdulah",99))
            println("Après update : ${daoPrepaStatement.read()}")


            daoPrepaStatement.delete(projet)
            println("Après delete : ${daoPrepaStatement.read()}")
        }
        if (typeDao == TypeDao.CallableStatement) {
            println("TODO")
        }


    }
    catch (e: Exception){
        println("Error: ${e.message}")
    }
}