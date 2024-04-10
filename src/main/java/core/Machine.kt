package core

import error.TuringSyntaxException
import java.io.File


class Machine(programsrc :String, inputsrc : String) {

    var state  = "0"
    var step = 0
    var pos = 0

    var input :String
    var program :Program

    init {
        input = loadInput(inputsrc)
        program = loadProgram(programsrc)
    }

    private fun loadInput(src : String) : String{
        val input : String
        if(src == "-"){
            println("Enter input:")
            input = readlnOrNull().toString()
        }else{
            val file = File(src)
            input = file.readText()
        }
        return input
    }

    private fun loadProgram(src : String) :Program{
        return Program.load(src)
    }

    private fun terminate(){
        if(state.equals("halt-accepted")){
            System.out.println("Accepted")
        } else if(state.equals("halt-rejected")){
            System.out.println("Rejected")
        } else{
            System.out.println("Unknown halt state")
        }
        System.out.println("Terminated")
    }

    private fun readSymbol() : Char{
        return input.get(pos)
    }

    private fun getCommand(symbol : Char) : Command {
        return program.getCommand(state, symbol)
    }

    private fun writeSymbol(symbol : Char){
        val sb = StringBuffer(input)
        sb.setCharAt(pos, symbol)
    }

    private fun move(direction : Int){
        when(direction){
            -1 -> pos--
            0 -> return;
            1 -> pos++
            else -> {
                throw TuringSyntaxException()
            }
        }
    }

    private fun step (){
        //check termination
        if(state.startsWith("halt")){
            terminate()
        }
        //read symbol
        var symbol = readSymbol()
        //get command for symbol
        val command = getCommand(symbol)
        //write new symbol
        writeSymbol(command.newSymbol)
        //move head left or right
        move(command.direction)
        //go to new state
        state = command.newState
        //increase step counter
        step++
    }

    fun run() {
        while(!state.startsWith("halt")){
            step()
        }
    }

    fun main(args :Array<String>){
        val machine = Machine(args[0], args[1])
        machine.run()
    }
}