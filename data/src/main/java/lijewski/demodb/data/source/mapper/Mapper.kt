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

    /**
     * Map a List of [E] instance to a List of [D] instance
     */
    fun mapFromEntityList(typeList: List<E>): List<D> {
        return typeList.map {
            mapFromEntity(it)
        }
    }

    /**
     * Map a List of [D] instance to a List of [E] instance
     */
    fun mapToEntityList(typeList: List<D>): List<E> {
        return typeList.map {
            mapToEntity(it)
        }
    }
}
