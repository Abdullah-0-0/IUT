package exo2

/* NE PAS MODIFIER CETTE CLASSE */

/**
 * représente un dock, c'est-à-dire un emplacement où garer des vaisseaux
 * encapsule un tableau en ne laissant accessible que 2 méthodes 'get()' et 'set()'
 *
 * Au départ le tableau est par défaut initialisé avec toutes ses cases 'LIBRE'
 *
 * implémentation du design pattern Proxy : on restreint les méthodes de 'Array<E>'
 */

class Dock(val size : Int, private val elements : Array<Dockable>) {

    operator fun get(index: Int): Dockable {
        return elements[index]
    }

    operator fun set(index: Int, element: Dockable) {
        elements[index] = element
    }
}