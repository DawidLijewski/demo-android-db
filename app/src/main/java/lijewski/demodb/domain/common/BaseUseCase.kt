package lijewski.demodb.domain.common

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

typealias CompletionBlock<T> = BaseUseCase.Request<T>.() -> Unit

abstract class BaseUseCase<T>() {

    private var parentJob: Job = Job()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(): T

    fun execute(block: CompletionBlock<T>) {
        val response = Request<T>().apply { block() }
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground()
                }
                response(result)
            } catch (e: Exception) {
                response(e)
            }
        }
    }

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    class Request<T> {
        private var onComplete: ((T) -> Unit)? = null
        private var onError: ((Throwable) -> Unit)? = null

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        fun onError(block: (Throwable) -> Unit) {
            onError = block
        }

        operator fun invoke(result: T) {
            onComplete?.invoke(result)
        }

        operator fun invoke(throwable: Throwable) {
            onError?.invoke(throwable)
        }
    }
}
