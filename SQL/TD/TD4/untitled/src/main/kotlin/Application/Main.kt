package Application.Main

import Bean.Projet
import Bean.Travail
import DAO.DAO
import DAO.DAOProjet
import Main.BD.SessionOracle

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val tableTravail = Travail()
    val tableProjet = Projet()
    //print(tableProjet.selectAll())
    //println(tableProjet.requete("select nuproj,nomproj from projet where nuproj = 103"))
    //println(Projet().selectAll())
    try {
        println(DAOProjet().read())
        //println(DAOProjet().select(tableProjet))
    }
    catch (e: Exception){
        println("Error: ${e.message}")
    }
}