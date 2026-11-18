package Bean

import Main.BD.SessionOracle

class Projet {
    private val bd = SessionOracle()

    fun selectAll(complement : String ="") : String{
        var res =""
        bd.getConnectionOracle().use { connection ->
            connection!!.prepareStatement("SELECT * FROM projet "+ complement).use { statement ->
                statement.executeQuery().use { resultSet ->
                    for(i in 1 until resultSet.metaData.columnCount+1){
                        res+=resultSet.metaData.getColumnName(i) + " | "
                    }
                    res+="\n"
                    while (resultSet.next()){
                        for (i in 1 until resultSet.metaData.columnCount+1){
                            res+= resultSet.getString(i) + " | "
                        }
                        res+= "\n"
                    }
                }
            }
        }
        return  res
    }
    fun requete(requete : String) : String{
        var res = ""
        bd.getConnectionOracle().use { connection ->
            connection!!.prepareStatement(requete).use { statement ->
                statement.executeQuery().use { resultSet ->
                    for (i in 1 until resultSet.metaData.columnCount+1) {
                        res+=resultSet.getString(i) + " | "
                    }
                    res+="\n"
                    while (resultSet.next()){
                        for (i in 1 until resultSet.metaData.columnCount+1){
                            res+=resultSet.getString(i) + " | "
                        }
                        res+="\n"
                    }
                }
            }
        }
        return  res
    }

}