//computer
package Computer
import scala.util.Random
import scala.math.max
import Board.Board


class Computer(val board: Board){

    val player = 'X'
    val opponent = 'O'

    def isMovesLeft(board: Array[Array[Char]]): Boolean = {
        for(i <- 0 to 2; j <- 0 to 2){
            if(board(i)(j) == '.'){
                return true
            }
        }
        return false
    }

    def evaluate(board: Array[Array[Char]]): Int = {
        for (row<- 0 to 2) 
        { 
            if (board(row)(0) == board(row)(1) && 
                board(row)(1) == board(row)(2)) 
            { 
                if (board(row)(0) == player) 
                    return +10; 
                else if (board(row)(0) == opponent) 
                    return -10; 
            } 
        } 
    
        for (col <- 0 to 2) 
        { 
            if (board(0)(col) == board(1)(col) && 
                board(1)(col) == board(2)(col)) 
            { 
                if (board(0)(col) == player) 
                    return +10; 
    
                else if (board(0)(col) == opponent) 
                    return -10; 
            } 
        } 
    
        if (board(0)(0) == board(1)(1) && board(1)(1) == board(2)(2)) 
        { 
            if (board(0)(0) == player) 
                return +10; 
            else if (board(0)(0) == opponent) 
                return -10; 
        } 
    
        if (board(0)(2) == board(1)(1) && board(1)(1) == board(2)(0)) 
        { 
            if (board(0)(2) == player) 
                return +10; 
            else if (board(0)(2) == opponent) 
                return -10; 
        } 
    
        return 0; 
    }


    def minimax(board: Array[Array[Char]], depth: Int, isMax: Boolean): Int = {
        val score = evaluate(board); 
    
        if (score == 10) 
            return score; 
    
        if (score == -10) 
            return score; 
    
        if (isMovesLeft(board) == false) 
            return 0; 
    
        if (isMax) 
        { 
            var best = -1000; 
            for (i <- 0 to 2; j <- 0 to 2) 
            { 
                if (board(i)(j)=='.') 
                { 
                    board(i)(j) = player; 

                    best = math.max(best, minimax(board,  
                                    depth + 1, !isMax)) 
                    board(i)(j) = '.' 
                } 
            } 
            return best-depth
        } 

        else
        { 
            var best = 1000; 
            for (i <- 0 to 2; j <- 0 to 2) 
            { 
                if (board(i)(j) == '.') 
                { 
                    board(i)(j) = opponent; 

                    best = math.min(best, minimax(board,  
                                    depth + 1, !isMax)); 
                    board(i)(j) = '.'; 
                } 
            }
            return best+depth 
        } 
    }

    def findBestMove(board: Array[Array[Char]]): (Int,Int)={
        var bestVal = -1000 
        var bestMove = (-1,-1)

        for (i<- 0 to 2; j <- 0 to 2) 
        {
            if (board(i)(j) == '.') 
            { 
                board(i)(j) = player; 

                var moveVal = minimax(board, 0, false); 
                board(i)(j) = '.'; 

                if (moveVal > bestVal) 
                { 
                    bestMove = (i,j) 
                    bestVal = moveVal; 
                } 
            } 
        } 
    
        print(s"Valeur du meilleur move ordi $bestMove \n")
    
        return bestMove; 
    }

}
