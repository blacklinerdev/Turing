package error

class StateNotFoundException : Exception("Could not find command for current state") {
}