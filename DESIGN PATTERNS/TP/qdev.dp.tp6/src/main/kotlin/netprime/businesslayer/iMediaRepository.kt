package netprime.businesslayer

import netprime.datalayer.datamodel.medias.Media
import netprime.datalayer.datamodel.medias.MediaWithCrew
import netprime.exceptions.EpisodeException
import netprime.exceptions.JsonParserException
import netprime.exceptions.MediaException
import java.io.FileNotFoundException

interface iMediaRepository {


    /**
     * charge des séries depuis le fichier JSON [ressource] et les ajoute à
     * celles déjà enregistrées.
     *
     * @param ressource le chemin vers un fichier .json contenant des séries.
     * @exception FileNotFoundException si le fichier n'est pas trouvé.
     * @exception JsonParserException si le fichier ne contient pas un JSON correctement formaté.
     * @exception MediaException si une série est en doublon.
     */
    fun loadSeriesFrom(ressource: String)

    /**
     * charge des films depuis le fichier JSON [ressource] et les ajoute à
     * ceux déjà enregistrés.
     *
     * @param ressource le chemin vers un fichier .json contenant des films.
     * @exception FileNotFoundException si le fichier n'est pas trouvé.
     * @exception JsonParserException si le fichier ne contient pas un JSON correctement formaté.
     * @exception MediaException si un film est en doublon.
     */
    fun loadMoviesFrom(ressource: String)

    /**
     * charge des épisodes depuis le fichier JSON [ressource] et les ajoute à
     * ceux déjà enregistrés. Les séries dont dépendent les épisodes doivent
     * avoir été chargées au préalable (voir [loadSeriesFrom]).
     *
     * @param ressource le chemin vers un fichier .json contenant des épisodes.
     * @exception FileNotFoundException si le fichier n'est pas trouvé.
     * @exception JsonParserException si le fichier ne contient pas un JSON correctement formaté.
     * @exception EpisodeException si un épisode référence une série qui n'est
     *     pas déjà enregistrée, ou si un épisode est en doublon.
     */
    fun loadEpisodesFrom(ressource: String)

    /**
     * donne le nombre total de médias enregistrés : films, séries et épisodes.
     *
     * @return le nombre de médias.
     */
    fun nbTotalMedias(): Int

    /**
     * donne le nombre de films enregistrés.
     *
     * @return le nombre de films.
     */
    fun nbMovies(): Int

    /**
     * donne le nombre de séries enregistrées.
     *
     * @return le nombre de séries.
     */
    fun nbSeries(): Int


    /**
     * donne une liste de tous les titres des médias enregistrés (films, séries
     * et épisodes), triée par imDbRating croissant, puis par titre.
     *
     * @return la liste des titres des médias.
     */
    fun listAllMedias(): List<String>

    /**
     * Indique si [id] correspond à l'id d'une série enregistrée.
     *
     * @param id l'id à rechercher.
     * @return `true` si l'id est trouvé, `false` sinon.
     */
    fun isSerieId(id: String): Boolean

    /**
     * donne tous les titres des épisodes de la série ayant comme titre exact
     * [title], triés par seasonNumber puis par episodeNumber.
     *
     * @param title le titre exact (sensible à la casse) de la série à
     *     rechercher.
     * @return la liste de tous les titres des épisodes de la série, vide si la
     *     série n'existe pas ou n'a pas d'épisode.
     */
    fun episodesFrom(title: String): List<String>

    /**
     * Liste tous les médias (films, séries et épisodes) dont le titre contient
     * [keyword], triés par année, puis par titre.
     *
     * @param keyword le mot clef à rechercher dans les titres (sensible à la
     *     casse).
     * @return la liste des médias recherchés.
     */
    fun listByTitleKeyword(keyword: String): List<Media>

    /**
     * liste tous les films et séries dont la [person] fait partie de l'équipe,
     * triés par titre, puis par année. Les épisodes n'ont pas d'équipe.
     *
     * @param person le nom complet de la personne à rechercher (sensible à la
     *     casse, sans la mention « (dir.) »).
     * @return la liste de médias recherchés.
     */
    fun listByCrew(person: String): List<MediaWithCrew>

    /**
     * efface un média (identifié par égalité). Effacer une série efface aussi
     * ses épisodes.
     *
     * @param media le média à effacer.
     * @exception MediaException si le média n'est pas présent.
     */
    fun delete(media: Media)

}
