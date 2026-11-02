package netprime.businesslayer

import netprime.datalayer.datamodel.medias.Episode
import netprime.datalayer.datamodel.medias.Media
import netprime.datalayer.datamodel.medias.Movie
import netprime.datalayer.datamodel.medias.Serie
import netprime.datalayer.datamodel.users.User


class NetPrime {

    // TODO

    private var users: MutableSet<User> = mutableSetOf()

    private var movies: MutableList<Movie> = mutableListOf()
    private var series: MutableList<Serie> = mutableListOf()
    private var episodes: MutableList<Episode> = mutableListOf()

    private val medias: Set<Media>
        get() = movies union series union episodes

    // TODO

}
