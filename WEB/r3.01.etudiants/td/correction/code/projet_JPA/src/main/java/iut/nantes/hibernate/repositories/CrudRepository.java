package iut.nantes.hibernate.repositories;

import java.util.Optional;

import java.io.Serializable;

/**
 * Inspiré de org.springframework.data.repository.CrudRepository<T,ID>
 */
public interface CrudRepository<T,ID>  {
    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the entity instance completely.
     * @param entity the entity to save, must not be null.
     * @return the saved entity.
     * @throws IllegalArgumentException - in case the given entity is null.
     */
    <S extends T> S save(S entity);

    /**
     * Saves all given entities.
     * @param entities must not be null nor must it contain null.
     * @return the saved entities. The returned Iterable will have the same size as the Iterable passed as an argument.
     * @throws IllegalArgumentException - in case the given entities or one of its entities is null.
     */
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    /**
     * Retrieves an entity by its id.
     * @param id must not be null.
     * @return the entity with the given id or Optional#empty() if none found.
     * @throws IllegalArgumentException - if id is null.
     */

    Optional<T> findById(ID id);

    /**
     * Returns whether an entity with the given id exists.
     * @param id must not be null.
     * @return true if an entity with the given id exists, false otherwise.
     * @throws IllegalArgumentException - if id is null.
     */

    boolean existsById(ID id);

    /**
     * Returns all instances of the type.
     * @return all entities
     */
    Iterable<T> findAll();

    /**
     * Returns all instances of the type T with the given IDs.
     * If some or all ids are not found, no entities are returned for these IDs.
     *
     * Note that the order of elements in the result is not guaranteed.
     * @param ids must not be null nor contain any null values.
     * @return iterable of found entities. The size can be equal or less than the number of given ids.
     * @throws IllegalArgumentException - in case the given ids or one of its items is null.
     */
    Iterable<T> findAllById(Iterable<ID> ids);

    /**
     * Returns the number of entities available.
     * @return the number of entities.
     */
    long count();

    /**
     * Deletes the entity with the given id.
     * If the entity is not found in the persistence store it is silently ignored.
     * @param id must not be null.
     * @throws IllegalArgumentException - in case the given id is null
     */
    void deleteById(ID id);

    /**
     * Deletes a given entity.
     * @param entity must not be null.
     * @throws IllegalArgumentException - in case the given entity is null.
     */
    void delete(T entity);

    /**
     * Deletes all instances of the type T with the given IDs.
     * Entities that aren't found in the persistence store are silently ignored.
     * @param ids must not be null. Must not contain null elements.
     * @throws IllegalArgumentException - in case the given ids or one of its elements is null.
     */
    void deleteAllById(Iterable<? extends ID> ids);

    /**
     * Deletes the given entities.
     * @param entities must not be null. Must not contain null elements.
     * @throws IllegalArgumentException - in case the given entities or one of its entities is null.
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();
}
