package core

import error.StateNotFoundException
import java.io.File

class Program private constructor() {

    private val commands: HashMap<String, Command> = HashMap()

    fun getCommand(state: String, symbol: Char): Command {
        val result = commands["$state+$symbol"] ?: throw StateNotFoundException()
        return result
    }

    private fun insertCommand(command: Command) {
        val key = "${command.currentState}+${command.currentSymbol}"
        commands[key] = command
    }

    companion object {
        fun load(path: String): Program {
            val program = Program()
            val file = File(path)
            //for line in file...
            file.forEachLine {
                val line = it
                if (line.startsWith("#")) return@forEachLine
                val command = Command.create(line)
                program.insertCommand(command)
            }
            return program
        }
    }
}