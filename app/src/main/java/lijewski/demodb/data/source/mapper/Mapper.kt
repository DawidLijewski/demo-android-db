package lijewski.demodb.data.source.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> the cached model input type
 * @param <T> the remote model input type
 * @param <V> the model return type
 */
interface Mapper<E, D> {

    /**
     * Map a [E] instance to a [D] instance
     */
    fun mapFromEntity(type: E): D

    /**
     * Map a [D] instance to a [E] instance
     */
    fun mapToEntity(type: D): E

}