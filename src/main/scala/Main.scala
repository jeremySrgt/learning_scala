import scala.io.StdIn.readLine


import Player.Player
import Board.Board
import Test.Test

object Main extends App {

  def play(game : Board = new Board, playerTurn : Char = 'X') : Unit = {
    
    val player1 = new Player('X')
    val player2 = new Player('O')


    //le board est quadrillé comme suit : 
    //   A B C
    // 1 . . .
    // 2 . . .
    // 3 . . .


    game.showBoard()

    //var turn = player1.letter

    println(s"Au tour du joueur $playerTurn")

    var move = readLine("ou joue tu ?")
    println(s"tu as choisi : $move")

    var coord = game.getCoord(move)

    if(coord._1 == -100 && coord._2 == -100){
      println("Position Invalide")
      println(s"Le joueur $playerTurn rejoue")
      play(game,playerTurn)
    }

    game.updateBoard(coord._1,coord._2,playerTurn)

    if(game.state == "next"){
        val nextplayer = whosNext(playerTurn)
        play(game,nextplayer)
    }else if(game.state == "retry"){
      println(s"Le joueur $playerTurn rejoue")
      play(game,playerTurn)
    }else if(game.state == "full"){
      game.showBoard()
      println("Le board est full plus de move possible")
    }else if(game.state == "win"){
      game.showBoard()
      println(s"Joueur $playerTurn a Gagné")
    }

    
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