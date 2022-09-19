package com.github.bedrockecs.server.comm.zimpl.exchange

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentLinkedQueue

@Component
class GameTickLoopExchange {

    private val pendingTickLoops = ConcurrentLinkedQueue<Pair<() -> Unit, CompletableJob>>()

    /**
     * runs the function in tick loop
     */
    suspend fun runInTickLoop(func: () -> Unit) {
        val job = Job()
        pendingTickLoops.add(func to job)
        job.join()
    }

    fun onServerTick() {
        pendingTickLoops.forEach {
            it.first()
            it.second.complete()
        }
    }

    fun onServerStop() {
        pendingTickLoops.forEach { it.second.cancel() }
    }
}
