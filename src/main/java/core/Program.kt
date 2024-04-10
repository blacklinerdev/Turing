package core

import error.StateNotFoundException
import java.io.File
import java.util.*

class Program(commands : HashMap<String, Command>){
    var commands : HashMap<String, Command>

    init {
        commands
    }


    fun getCommand(state : String, symbol : Char) : Command{
        val result = commands.get("$state+$symbol") ?: throw StateNotFoundException()
        return result
    }

    companion object {
        fun load(path : String): Program {
            var file = File(path)
            file.
        }
    }
}