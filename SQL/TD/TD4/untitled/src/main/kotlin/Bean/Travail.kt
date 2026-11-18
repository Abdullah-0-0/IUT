package Bean

import Main.BD.SessionOracle

class Travail() {
    private val bd = SessionOracle("s3a02a", "1968")

    fun selectAll(complement : String =" ") : String {
        var resultat = ""
        bd.getConnectionOracle().use { conn ->
            conn?.prepareStatement("select * from travail " + complement).use { res ->
                res?.executeQuery().use { rs ->
                    resultat += rs!!.metaData.getColumnName(1) + " | " +
                            rs.metaData.getColumnName(2) +" | " +
                            rs.metaData.getColumnName(3) +"\n"
                    while (rs!!.next()){
                        val nbColumns = rs.metaData.columnCount
                        for (i in 1 until nbColumns+1){
                            resultat +=  rs.getString(i) +" | "
                        }
                        resultat += "\n"
                    }

                }
            }
        }
        return resultat
    }
    fun requete(requete : String) : String {
        var result = ""
        bd.getConnectionOracle().use { conn ->
            conn?.prepareStatement(requete).use { requet ->
                requet?.executeQuery().use { res ->
                    for (i in 1 until res!!.metaData.getColumnCount()+1) { result+= res.metaData.getColumnName(i)+" | "}
                    result+="\n"
                    while (res!!.next()){
                        val nbColumns = res.metaData.columnCount
                        for (i in 1 until nbColumns+1){
                            result +=  res.getString(i) +" | "
                        }
                        result+="\n"
                    }
                }
            }
        }
        return result
    }

    fun insert(requete : String) : String {
        return  ""
    }


}