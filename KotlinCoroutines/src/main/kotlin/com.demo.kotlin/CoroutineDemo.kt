package com.demo.kotlin

import kotlinx.coroutines.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory


// coroutine ~ thread(management is performance heavy)
// coroutine = threads onto of jvm thread
// fibers in scala  = coroutine in kotlin =  virtual thread in java

val logger: Logger = LoggerFactory.getLogger("CoroutineDemo ")

// like python async - non blocking
suspend fun bathTime(){
    // the kotlin runtime will need a 'continuation' datastructure which stores all local context (this can be recursivley)
    logger.info("going to batchroom")
    delay(500) // blocks the computation and thread picks up some other work
    // continuation resumed
    logger.info("Bath done, existing")
}

suspend fun boilingWater(){
    logger.info("boiling water")
    delay(3000) // blocks the computation and thread picks up some other work
    // continuation resumed
    logger.info("water boiled")
}

suspend fun sequentialMorningRoutine(){
    coroutineScope {
        bathTime()
        // can add more code here, this is isolated from other coroutine scope
        // all need to finish before scope is closed
        // can do nested coroutines_scopes
    }
    coroutineScope {
        boilingWater()
    }
}

suspend fun concurrentMorningRoutine(){
    coroutineScope {
        launch{ // this will start a new child coroutine scope - nested scopes
            bathTime()
        }
        launch {
            boilingWater() //
        }
    }
}

suspend fun noStructConcurrentMorningRoutine(){
    coroutineScope {
        GlobalScope.launch{ // this will start a new coroutine scope not nested, but global scope has its own dangers, use causiously
            bathTime()
        }
        GlobalScope.launch {
            boilingWater() //
        }
    }
}

suspend fun makeCoffee(){
    logger.info("make coffee")
    delay(3000) // blocks the computation and thread picks up some other work
    // continuation resumed
    logger.info("done with coffee")
}

// coroutine spec
suspend fun moringRoutineWithCoffee(){
    coroutineScope {
        val bathTimeJob:Job = launch { bathTime() }
        val boilingWaterJob : Job = launch { boilingWater() }
        bathTimeJob.join()
        boilingWaterJob.join()
        launch {
            makeCoffee()
        }
    }
}
// or using nested coroutines - much nicer
suspend fun moringRoutineWithCoffeeNestedCoroutineScope(){
    coroutineScope {
        coroutineScope {
            launch { bathTime() }
            launch { boilingWater() }
        }
        makeCoffee()
    }
}

// return val from coroutine
suspend fun preparingJavaCOffee():String{
    logger.info("make coffee")
    delay(3000) // blocks the computation and thread picks up some other work
    // continuation resumed
    logger.info("done with coffee")
    return "java coffee"
}
suspend fun toastingBread():String{
    logger.info("make toast")
    delay(3000) // blocks the computation and thread picks up some other work
    // continuation resumed
    logger.info("done with toast")
    return "toasted bread"
}

suspend fun prepareBreakfast(){
    coroutineScope {
        val deferred = async { preparingJavaCOffee() } // returns a deferred object
        val deferred1 = async { toastingBread() }
        val coffee = deferred.await()
        val toast = deferred1.await()
        logger.info("I'm eating $toast and drinking $coffee")
    }
}

suspend fun main(args: Array<String>){
    prepareBreakfast()
    Thread.sleep(3000)
}