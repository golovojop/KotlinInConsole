/**
 * Materials:
 *
 * 2D Arrays:
 * https://www.ict.social/kotlin/basics/multidimensional-arrays-in-kotlin
 */

package k.s.yarlykov.kotlininconsole.algorithm.graph

// Узел графа
class Vertex(val label: Char, var isVisited: Boolean = false) {
    override fun toString(): String {
        return label.toString()
    }
}

class GraphApp {

    private val vertexList = arrayListOf<Vertex>()
    private var adjMatrix = arrayOf<IntArray>()
    private var size = 0

    init {
        // Обнулить элементы матрицы
        for(i in 0 until MAX_VERTS) {
            val array = IntArray(MAX_VERTS)
            for (j in 0 until MAX_VERTS) {
                array[j] = 0
            }
            adjMatrix += array
        }
    }

    fun addVertex(label: Char) {
        vertexList += Vertex(label)
        size++

    }

    fun addEdge(from: Int, to: Int) {
        adjMatrix[from][to] = 1
        adjMatrix[to][from] = 1
    }

    fun displayVertex(index: Int) {
        println("${vertexList[index]}")
    }

    fun getAdjUnvisitedVertex(index: Int): Int {
        for(i in 0 until size) {
            if(adjMatrix[index][i] == 1 && !vertexList[i].isVisited) {
                return i
            }
        }
        return -1
    }

    fun dfs() {
        val stack = Stack(MAX_VERTS)
        vertexList[0].isVisited = true
        displayVertex(0)
        stack.push(0)

        while(!stack.isEmpty()) {
            val v = getAdjUnvisitedVertex(stack.peek())

            if(v == -1) {
                stack.pop()
            } else {
                vertexList[v].isVisited = true
                displayVertex(v)
                stack.push(v)
            }
        }

        clearVisitedFlag()
    }

    fun bfs() {
        val queue = Queue(MAX_VERTS)
        vertexList[0].isVisited = true
        queue.push(0)

        while(!queue.isEmpty()) {
            var e = queue.pop()
            displayVertex(e)

            while(true) {
                val v = getAdjUnvisitedVertex(e)
                if(v != -1) {
                    vertexList[v].isVisited = true
                    queue.push(v)
                } else {
                    break
                }
            }
        }

        clearVisitedFlag()
    }

    // Очистка флага
    fun clearVisitedFlag() {
        for(i in 0 until size) {
            vertexList[i].isVisited = false
        }
    }

    companion object {
        val MAX_VERTS: Int = 32
    }
}


fun main() {
    val graph = GraphApp()

    graph.addVertex('A')    // 0
    graph.addVertex('B')    // 1
    graph.addVertex('C')    // 2
    graph.addVertex('D')    // 3
    graph.addVertex('E')    // 4

    graph.addEdge(0, 1) //AB
    graph.addEdge(0, 2) //AC
    graph.addEdge(0, 3) //AD
    graph.addEdge(1, 4) //BE


    // Должно быть
    // A B E C D
    graph.dfs()

    println("\n\n")

    // Должно быть
    // A B C D E
    graph.bfs()
}