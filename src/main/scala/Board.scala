package Board
import util.control.Breaks._

class Board(val board : Array[Array[Char]] = Array.ofDim(3,3), var t : Int = 3){

    for(i <- 0 to 2; j <- 0 to 2){
        board(i)(j) = '.'
    }

    def showBoard() : Unit = {
        // board foreach{ row => row foreach print; println}
        board.foreach{
            case(row) => row.foreach{case(position)=> print("| ") ; print(position)}
            println
        }
    }

    // override def toString(): String = {
    //     board foreach{ row => row foreach print; println}
    // }

    def updateBoard(row : Int, col : Int, playerLetter : Char): String = {

       if(board(row)(col) == '.'){
           board(row)(col) = playerLetter

           if (checkWin(row,col,playerLetter)== true){
               "win"
           }else if(checkFull() == true){
               "full"
           }else{
               "next"
           }
       }else{
           "retry"
       }
        
    }

    def checkWin(row: Int, col: Int, playerLetter : Char): Boolean = {
        
        //check colones
        breakable{
            for(i <- 0 to 2){
                if(board(row)(i) != playerLetter){
                    break
                }
                if(i == 3-1){
                    return true
                }
            }
        }
        
        //check rows
        breakable{
            for(i <- 0 to 2){
                if(board(i)(col) != playerLetter){
                    break
                }
                if(i == 3-1){
                    return true
                }
            }
        }
        
        //check diagonal
        if(row == col){
            breakable{
                for(i <- 0 to 2){
                    if(board(i)(i) != playerLetter){
                        break
                    }
                    if(i == 3-1){
                        return true
                    }
                }
            }  
        }
        //check anti diagonal
        if(row+col == 3-1){
            breakable{
                for(i <-0 to 2){
                    if(board(i)((3-1)-i) != playerLetter){
                        break
                    }
                    if(i==3-1){
                        return true
                    }
                }
            }
        }

        return false
        

    }

    def checkFull() : Boolean = {
        breakable {
            for(i <- 0 to 2; j <- 0 to 2){
                if(board(i)(j) == '.'){
                    break
                }
                if(i == 2 && j == 2){
                    return true
                }
            }
        }
        return false
    }


    def getCoord(position : String): (Int,Int) = {

        val coordMap = Map("A1" -> (0,0), "A2" -> (1,0), "A3" -> (2,0),"B1" -> (0,1), "B2" -> (1,1), "B3" -> (2,1),
        "C1" -> (0,2), "C2" -> (1,2), "C3" -> (2,2))

        if(coordMap.contains(position)){
            coordMap(position.toString())
        }else{
            (-100,-100)
        }

    }
}