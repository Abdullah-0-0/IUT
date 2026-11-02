package netprime.datalayer

import netprime.datalayer.datamodel.medias.Episode
import netprime.datalayer.datamodel.medias.Movie
import netprime.datalayer.datamodel.medias.Serie
import netprime.exceptions.EpisodeException
import netprime.exceptions.JsonParserException
import netprime.exceptions.MediaException
import java.io.FileNotFoundException

interface iMediaDAO {

    /**
     * Ajoute des séries depuis le fichier JSON [ressource] à celles déjà
     * chargées.
     *
     * @param ressource le chemin vers un fichier .json contenant des séries.
     * @return la liste de toutes les [Serie] chargées jusqu'ici par le DAO.
     * @exception FileNotFoundException si le fichier n'est pas trouvé.
     * @exception JsonParserException si le fichier ne contient pas un JSON correct.
     * @exception MediaException si une série est en doublon (même id), dans le
     *     fichier ou avec une série déjà chargée. Dans ce cas, aucune série du
     *     fichier n'est ajoutée.
     */
    fun addSeriesFrom(ressource: String): List<Serie>

    /**
     * Ajoute des films depuis le fichier JSON [ressource] à ceux déjà chargés.
     *
     * @param ressource le chemin vers un fichier .json contenant des films.
     * @return la liste de tous les [Movie] chargés jusqu'ici par le DAO.
     * @exception FileNotFoundException si le fichier n'est pas trouvé.
     * @exception JsonParserException si le fichier ne contient pas un JSON correct.
     * @exception MediaException si un film est en doublon (mêmes titre, année,
     *     équipe et note), dans le fichier ou avec un film déjà chargé. Dans ce
     *     cas, aucun film du fichier n'est ajouté.
     */
    fun addMoviesFrom(ressource: String): List<Movie>

    /**
     * Ajoute des épisodes depuis le fichier JSON [ressource] à ceux déjà
     * chargés. Chaque épisode est aussi ajouté à la liste `episodes` de sa
     * [Serie].
     *
     * @param ressource le chemin vers un fichier .json contenant des
     *     épisodes.
     * @return la liste de tous les [Episode] chargés jusqu'ici par le DAO.
     * @exception FileNotFoundException si le fichier n'est pas trouvé.
     * @exception JsonParserException si le fichier ne contient pas un JSON correct.
     * @exception EpisodeException si l'épisode référence une série qui n'est
     *     pas déjà présente, ou si l'épisode est en doublon (même série, même
     *     saison, même numéro), dans le fichier ou avec un épisode déjà chargé.
     *     Dans ce cas, aucun épisode du fichier n'est ajouté.
     */
    fun addEpisodesFrom(ressource: String): List<Episode>

}
