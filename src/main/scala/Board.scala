package Board

class Board(val board : Array[Array[Char]] = Array.ofDim(3,3), var t : Int = 3){

    for(i <- 0 to 2; j <- 0 to 2){
        board(i)(j) = '.'
    }

    def showBoard() : Unit = {
        board foreach{ row => row foreach print; println}
    }

    // override def toString(): String = {
    //     board foreach{ row => row foreach print; println}
    // }

    def updateBoard(row : Int, col : Int, playerLetter : Char){

        board(row)(col) = playerLetter
    }


    def getCoord(position : String): (Int,Int) = {
        val coordMap = Map("A1" -> (0,0), "A2" -> (1,0), "A3" -> (2,0),"B1" -> (0,1), "B2" -> (1,1), "B3" -> (2,1),
        "C1" -> (0,2), "C2" -> (1,2), "C3" -> (2,2))

        coordMap(position.toString())
    }
}