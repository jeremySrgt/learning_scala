import scala.io.StdIn.readLine


import Player.Player
import Board.Board
import Test.Test

object Main extends App {

  def play(game : Board = new Board, playerTurn : Char = 'X') : Unit = {
    
    val player1 = new Player('X')
    val player2 = new Player('O')

    game.showBoard()

    //var turn = player1.letter

    println(s"Au tour du jouer $playerTurn")

    var move = readLine("ou joue tu ?")
    println(s"tu as choisi : $move")

    var coord = game.getCoord(move)

    var state = game.updateBoard(coord._1,coord._2,playerTurn)

    if(state == "next"){
        val nextplayer = whosNext(playerTurn)
        play(game,nextplayer)
    }else if(state == "retry"){
      println(s"Le joueur $playerTurn rejoue")
      play(game,playerTurn)
    }else if(state == "win"){
      game.showBoard()
      println(s"Joueur $playerTurn a Gagné")
    }

    

    //play(game, nextplayer)
    
    
    
    
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

  def whosNext(playerturn : Char) = {
    if(playerturn == 'X'){
        'O'
    }else{
      'X'
    }
  }

  play()
}