package Bean

data class Projet(val nuproj: Int, val nomproj: String, val resp: Int)
data class Travail(val nuempl: Int, val nuproj: Int, val duree: Int)
data class Employee(val nuempl: Int, val nomempl: String, val hebdo: Int,val affect : Int, val duree: Int)
data class Service(val nuserv : Int , val nomserv : String, val chef : Int)