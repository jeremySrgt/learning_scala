//computer
package Computer
import scala.util.Random

import Board.Board

class Computer(val board: Board){

    def getComputerMove(): (Int,Int) = {
        var row: Int = Random.between(0,3)
        var col: Int = Random.between(0,3)

        (row,col)
    }


}
