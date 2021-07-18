package clean.code.chess.requirements;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
//        throw new UnsupportedOperationException("Need to implement ChessBoard.add()");
        if (!(this.IsLegalBoardPosition(xCoordinate, yCoordinate))) {
            return;
        }

        pawn.setXCoordinate(xCoordinate);
        pawn.setYCoordinate(yCoordinate);
        pawn.setChessBoard(this);
        pieces[yCoordinate][xCoordinate] = pawn;
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate < 7 && yCoordinate >= 0 && yCoordinate < 7;
//        throw new UnsupportedOperationException("Need to implement ChessBoard.IsLegalBoardPosition()");
    }

    public Pawn[][] getPieces() {
        return pieces;
    }
}
