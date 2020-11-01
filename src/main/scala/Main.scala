import scala.io.StdIn.readLine


import Player.Player
import Board.Board
import Test.Test

object Main extends App {

  def play(game : Board = new Board, playerTurn : Char = 'X') : Unit = {
    
    val player1 = new Player('X')
    val player2 = new Player('O')

    game.showBoard()

    var turn = player1.letter

    println(s"Au tour du jouer $turn")

    var move = readLine("ou joue tu ?")
    println(s"tu as choisi : $move")

    var coord = game.getCoord(move)

    game.updateBoard(coord._1,coord._2,turn)

    play(game)
    
    
    
    
    // game.state match {
    //   case Win(player) => println(s"$player won the game :)")
    //   case NoMoreMoves() => println("No more moves to make :(")
    //   case InProgress() => {
    //     try {
    //       println("your turn")
    //       play(game)
    //     }catch {
    //       case e: Throwable => {
    //         println("Une erreur est survenue")
    //         play(game)
    //       }
    //     }
    //   }
    // }
  }

  play()
}