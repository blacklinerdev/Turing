package core

class Command(val currentState:String, val currentSymbol:Char, val newSymbol:Char,
              val direction:Int, val newState:String) {
}