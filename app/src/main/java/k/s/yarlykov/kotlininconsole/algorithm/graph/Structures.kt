package k.s.yarlykov.kotlininconsole.algorithm.graph

class Stack(val capacity: Int) {

    val stackArray = IntArray(capacity)
    var top: Int = -1

    fun push(element: Int) {
        stackArray[++top] = element
    }

    fun pop(): Int {
        return stackArray[top--]
    }

    fun isEmpty(): Boolean {
        return top == -1
    }

    fun peek(): Int {
        return stackArray[top]
    }
}

class Queue(capacity: Int) {

    val queuekArray = IntArray(capacity)
    var head = 0
    var tail = -1
    var size = 0

    fun push(element: Int) {
        queuekArray[++tail] = element
        size++
    }

    fun pop(): Int {
        size--
        return queuekArray[head++]
    }

    fun peek(): Int {
        return queuekArray[head]
    }

    fun isEmpty(): Boolean {
        return size == 0
    }
}
