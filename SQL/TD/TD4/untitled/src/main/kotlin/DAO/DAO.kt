package DAO

import Main.BD.SessionOracle

abstract class  DAO() {
    private val bd = SessionOracle()

    abstract fun select(requete : String,table : Any)
    abstract fun insert(requete : String,table : Any)
    abstract fun update(requete : String,table : Any)
    abstract fun delete(requete : String,table : Any)
    abstract fun read()
}