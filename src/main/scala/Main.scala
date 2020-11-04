//test branch GUI
import scala.io.StdIn.readLine
import scala.swing._

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

    var state = game.updateBoard(coord._1,coord._2,playerTurn)

    if(state == "next"){
        val nextplayer = whosNext(playerTurn)
        play(game,nextplayer)
    }else if(state == "retry"){
      println(s"Le joueur $playerTurn rejoue")
      play(game,playerTurn)
    }else if(state == "full"){
      game.showBoard()
      println("Le board est full plus de move possible")
    }else if(state == "win"){
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

  //play()



  var playerTurn: Char = whosNext('O') 

  var A1: Char = '_'
  var A2: Char = '_'
  var A3: Char = '_'
  var B1: Char = '_'
  var B2: Char = '_'
  var B3: Char = '_'
  var C1: Char = '_'
  var C2: Char = '_'
  var C3: Char = '_'

  def handleButtonAction(button: Button, turn: Label){
    button.text = playerTurn.toString()
    playerTurn = whosNext(playerturn = playerTurn)
    turn.text = s"Au tour du joueur $playerTurn"
  }

  val frame = new MainFrame {
    title = "Test swing scala"
    minimumSize = new Dimension(500,500)

    val turn = new Label {
      text = "Au tour du Joueur X"
    }

    val firstRow = new BoxPanel(Orientation.Horizontal){
        contents += new Button(A1.toString()){
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn)
          }
        }
        contents += new Button(B1.toString()){
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn)
          }
        }
        contents += new Button(C1.toString()){
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn)
          }
        }
      }

      val secondRow = new BoxPanel(Orientation.Horizontal){
        contents += new Button(A2.toString()){
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn)
          }
        }
        contents += new Button(B2.toString()){
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn)
          }
        }
        contents += new Button(B3.toString()){
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn)
          }
        }
      }

      val thirdRow = new BoxPanel(Orientation.Horizontal){
        contents += new Button(A3.toString()){
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn)
          }
        }
        contents += new Button(B3.toString()){
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn)
          }
        }
        contents += new Button(C3.toString()){
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn)
          }
        } 
      }

      contents = new BoxPanel(Orientation.Vertical) {
        contents += firstRow
        contents += secondRow
        contents += thirdRow
        contents += turn
      }

    centerOnScreen()
  }


  //faire un bouton rejouer qui relance le run 
  // ou reset simplemement la board
  frame.visible = true
  // pack()
  // centerOnScreen()
  // open()
}