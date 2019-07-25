package k.s.yarlykov.kotlininconsole.algorithm.graph

class ShortestPathApp {

    private val nodesList = arrayListOf<Node>()
    private val adjMatrix = arrayListOf<IntArray>()
    private var size = 0

    init {
        // Обнулить элементы матрицы
        for (i in 0 until MAX_NODES) {
            val array = IntArray(MAX_NODES)
            for (j in 0 until MAX_NODES) {
                array[j] = 0
            }
            adjMatrix += array
        }
    }

    fun addNode(label: Char) {
        nodesList += Node(label)
        size++
    }

    fun addEdge(from: Int, to: Int) {
        adjMatrix[from][to] = 1
        adjMatrix[to][from] = 1
    }

    fun displayNode(index: Int) {
        println("${nodesList[index]}")
    }

    fun getAdjUnvisitedNode(index: Int): Int {
        for (i in 0 until size) {
            if (adjMatrix[index][i] == 1 && !nodesList[i].isVisited) {
                return i
            }
        }
        return -1
    }

    fun shortestPath(targetIdx: Int): Int {
        val queue = Queue(MAX_NODES)
        var nextNodeIdx = 0
        var idx: Int = -1

        nodesList[nextNodeIdx].isVisited = true
        queue.push(nextNodeIdx)

        try {
            while (!queue.isEmpty()) {
                nextNodeIdx = queue.pop()

                while ({ idx = getAdjUnvisitedNode(nextNodeIdx); idx }() != -1) {
                    nodesList[idx].from = nodesList[nextNodeIdx]

                    if (idx == targetIdx) {
                        return idx
                    } else {
                        nodesList[idx].isVisited = true
                        queue.push(idx)
                    }
                }
            }
        } finally {
            clearVisitedFlag()
        }

        return idx
    }

    // Очистка флага
    fun clearVisitedFlag() {
        for (i in 0 until size) {
            nodesList[i].isVisited = false
        }
    }

    // Сюда прилетает индекс последней ноды маршрута.
    fun displayPath(idx: Int) {
        if(idx != -1) {
            println("Shortest path is:")
            displayPath(nodesList[idx])
        }
    }

    // Чтобы отрисовать путь правильно "слева направо" мы должны
    // рекурсивно опуститься "справа налево" до первой ноды, а затем
    // выходя из рекурсии распечатать имена нод.
    fun displayPath(node: Node?) {
        if (node == null) {
            return
        } else {
            displayPath(node.from)
            print("${node} ")
        }
    }

    companion object {
        val MAX_NODES: Int = 32
    }
}

class Node(val label: Char, var isVisited: Boolean = false) {
    var from: Node? = null

    override fun toString(): String {
        return label.toString()
    }
}

fun main() {
    val graph = ShortestPathApp()

    graph.addNode('A')    // 0
    graph.addNode('B')    // 1
    graph.addNode('C')    // 2
    graph.addNode('D')    // 3
    graph.addNode('E')    // 4
    graph.addNode('F')    // 5
    graph.addNode('G')    // 6

    graph.addEdge(0, 1) //AB
    graph.addEdge(0, 2) //AC
    graph.addEdge(0, 3) //AD
    graph.addEdge(1, 6) //BG
    graph.addEdge(2, 4) //CE
    graph.addEdge(3, 5) //DF
    graph.addEdge(4, 6) //EG
    graph.addEdge(5, 6) //FG

    val idx = graph.shortestPath(6)
    if (idx != -1) {
        graph.displayPath(idx)
    }
}