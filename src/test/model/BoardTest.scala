package model

import org.scalatest._
import model.BoardImpl

class BoardTest extends FlatSpec {

  "A card " should " be added to the board in a specific position" in {
    val card = CardImpl("A")
    val board = BoardImpl()
    board.addCard2Tile(card, 1,1 )
    assert(board.boardTiles.head.card.equals(card))
  }

  "A card " should " be removed to the board in a specific position" in {
    val card = CardImpl("A")
    val board = BoardImpl()
    board.addCard2Tile(card, 1,1)
    board.removeCardFromTile(1,1)
    assert(board.boardTiles.head.card.equals(boardConstants.defaultCard))
  }

  "A card " should " be added to the board in a specific position and in played word" in {
    val card = CardImpl("A")
    val board = BoardImpl()
    board.addCard2Tile(card, 1,1, add2PlayedWord = true)
    assert(board.boardTiles.head.card.equals(card) && board.playedWord.contains(BoardTileImpl(Position(1,1),card)))
  }

  "A card " should " be removed to the board in a specific position and from played word" in {
    val card = CardImpl("A")
    val board = BoardImpl()
    board.addCard2Tile(card, 1,1)
    board.removeCardFromTile(1,1, removeFromPlayedWord = true)
    assert(board.boardTiles.head.card.equals(boardConstants.defaultCard) && !board.playedWord.contains(BoardTileImpl(Position(1,1),card)))
  }

  "A hand " should " be removed add to board" in {
    val card = CardImpl("A")
    val board = BoardImpl()
    board.addCard2Tile(card, 1,1)
    board.removeCardFromTile(1,1, removeFromPlayedWord = true)
    assert(board.boardTiles.head.card.equals(boardConstants.defaultCard) && !board.playedWord.contains(BoardTileImpl(Position(1,1),card)))
  }

  "A list of Card " should " be added to the board and removed" in {
    val boardTile = BoardTileImpl(new Position(1,3), CardImpl("D"))
    val boardTile1 = BoardTileImpl(new Position(2,3), CardImpl("B"))
    val boardTile2 = BoardTileImpl(new Position(3,3), CardImpl("E"))
    val listBoardTile = List(boardTile,boardTile1,boardTile2)
    val board = BoardImpl()
    board.addPlayedWord(listBoardTile)
    assert(board.playedWord.equals(listBoardTile))
    board.clearPlayedWords()
    assert(board.playedWord.equals(List()))
  }

  "A list of card played " should " be remove from board" in {
    val boardTile = BoardTileImpl(new Position(1,3), CardImpl("D"))
    val boardTile1 = BoardTileImpl(new Position(2,3), CardImpl("B"))
    val boardTile2 = BoardTileImpl(new Position(3,3), CardImpl("E"))
    val listBoardTile = List(boardTile,boardTile1,boardTile2)
    val board = BoardImpl()
    board.addPlayedWord(listBoardTile)
    board.clearBoardFromPlayedWords()
    assert(!board.boardTiles.contains(listBoardTile))
  }
}