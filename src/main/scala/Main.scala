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

  var buttonEmptyText = "_"

  val board = new Board
  // solution pas viable, il faut changer dans le board 
  // lui donner une variable etat pour pouvoir y acced
  // board.state et avoir l'etat 
  // et dans le update board dans Board checker l'etat avant chaque MaJ du board

  def handleButtonAction(button: Button, turn: Label, info: Label, replay : Button){
    if(board.state == "win"){
      button.enabled = false
      info.text += "<- <-"
    }

    if(button.text == "_"){
      button.text = playerTurn.toString()

      var coord = board.getCoord(button.name)
      board.updateBoard(coord._1,coord._2,playerLetter = playerTurn)

      if(board.state == "full"){
        info.text = "Le board est full plus de move possible"
        replay.visible = true
      }else if(board.state == "win"){
        info.text = s"Joueur $playerTurn a Gagné"
        replay.visible = true
        //ajouter un bouton pour rejouer qui à la base est vide
      }


      playerTurn = whosNext(playerturn = playerTurn)
      turn.text = s"Au tour du joueur $playerTurn"
      println(board.showBoard())
    }
  }

  val frame = new MainFrame {
    title = "Test swing scala"
    minimumSize = new Dimension(500,500)

    val turn = new Label {
      text = "Au tour du Joueur X"
    }

    val infoMessage = new Label {
      text = "..."
    }

    val replay = new Button("Rejouer ?"){
      name = "replay"
      visible = false
      reactions += {
        case event.ButtonClicked(_) => println("replay")
      }
    }

    val firstRow = new BoxPanel(Orientation.Horizontal){
        contents += new Button(buttonEmptyText){
          name = "A1"
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn,infoMessage,replay)
          }
        }
        contents += new Button(buttonEmptyText){
          name = "B1"
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn,infoMessage,replay)
          }
        }
        contents += new Button(buttonEmptyText){
          name = "C1"
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn,infoMessage,replay)
          }
        }
      }

      val secondRow = new BoxPanel(Orientation.Horizontal){
        contents += new Button(buttonEmptyText){
          name = "A2"
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn,infoMessage,replay)
          }
        }
        contents += new Button(buttonEmptyText){
          name = "B2"
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn,infoMessage,replay)
          }
        }
        contents += new Button(buttonEmptyText){
          name = "C2"
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn,infoMessage,replay)
          }
        }
      }

      val thirdRow = new BoxPanel(Orientation.Horizontal){
        contents += new Button(buttonEmptyText){
          name = "A3"
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn,infoMessage,replay)
          }
        }
        contents += new Button(buttonEmptyText){
          name = "B3"
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn,infoMessage,replay)
          }
        }
        contents += new Button(buttonEmptyText){
          name = "C3"
          reactions += {
            case event.ButtonClicked(_) => handleButtonAction(this,turn,infoMessage,replay)
          }
        } 
      }

      contents = new BoxPanel(Orientation.Vertical) {
        contents += firstRow
        contents += secondRow
        contents += thirdRow
        contents += turn
        contents += infoMessage
        contents += replay
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