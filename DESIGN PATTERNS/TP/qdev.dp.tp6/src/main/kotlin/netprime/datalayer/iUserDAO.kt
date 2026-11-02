package netprime.datalayer

import netprime.datalayer.datamodel.users.User
import netprime.exceptions.JsonParserException
import netprime.exceptions.UserException
import java.io.FileNotFoundException

interface iUserDAO {

    /**
     * Ajoute à ceux déjà chargés les utilisateurs de la ressource [ressource].
     *
     * @param ressource le chemin vers un fichier .json contenant des
     *     utilisateurs (ou une URL, selon l'implémentation).
     * @return l'ensemble de tous les [User] chargés jusqu'ici par le DAO.
     * @exception FileNotFoundException si le fichier n'est pas trouvé.
     * @exception JsonParserException si le fichier ne contient pas un JSON correctement formaté.
     * @exception UserException si un utilisateur est en doublon, dans le fichier
     *     ou avec un utilisateur déjà chargé. Dans ce cas, aucun utilisateur
     *     du fichier n'est ajouté.
     */
    fun addFrom(ressource: String): Set<User>


}
