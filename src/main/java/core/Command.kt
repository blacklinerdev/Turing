package core

class Command(val currentState:String, val currentSymbol:Char, val newSymbol:Char,
              val direction:Int, val newState:String) {

    companion object{
        fun create(line :String): Command {
            val list = line.split(" ")
            return Command(list[0], list[1].toCharArray()[0], list[2].toCharArray()[0],
                list[3].toInt(), list[4]
            )
        }
    }
}