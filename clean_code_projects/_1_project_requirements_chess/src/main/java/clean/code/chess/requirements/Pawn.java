package clean.code.chess.requirements;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate = -1;
    private int yCoordinate = -1;
    private PieceColor pieceColor;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public Pawn(int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.pieceColor = pieceColor;
    }

    public ChessBoard getChesssBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public void Move(MovementType movementType, int newX, int newY) {
//        throw new UnsupportedOperationException("Need to implement Pawn.Move()");
        if (movementType.equals(MovementType.MOVE)) {
            if (isPositionValidForMove(newX, newY)) {
                this.setXCoordinate(newX);
                this.setYCoordinate(newY);
            }
        } else if (movementType.equals(MovementType.CAPTURE)) {
            if (isPositionValidForCapture(newX, newY)) {
                this.setXCoordinate(newX);
                this.setYCoordinate(newY);
            }
        }
    }

    private boolean isPositionValidForMove(int newX, int newY) {
        Pawn[][] piecesCopy = this.chessBoard.getPieces();

        if (!this.chessBoard.IsLegalBoardPosition(newX, newY)) {
            return false;
        }

        if (newX != xCoordinate) {
            return false;
        }

        if (this.getPieceColor().equals(PieceColor.BLACK)) {
            if (newY != (yCoordinate - 1)) {
                return false;
            }
        } else {
            if (newY != (yCoordinate + 1)) {
                return false;
            }
        }

        return piecesCopy[newY][newX] == null;
    }

    private boolean isPositionValidForCapture(int newX, int newY) {
        Pawn[][] piecesCopy = this.chessBoard.getPieces();

        if (!this.chessBoard.IsLegalBoardPosition(newX, newY)) {
            return false;
        }

        if (!(newX == (xCoordinate - 1) || newX == (xCoordinate + 1))) {
            return false;
        }

        if (this.getPieceColor().equals(PieceColor.BLACK)) {
            if (newY != (yCoordinate - 1)) {
                return false;
            }

            Pawn pieceToTake = piecesCopy[newY][newX];

            if (pieceToTake == null) {
                return false;
            }

            return pieceToTake.getPieceColor().equals(PieceColor.WHITE);

        } else {
            if (newY != (yCoordinate + 1)) {
                return false;
            }

            Pawn pieceToTake = piecesCopy[newY][newX];

            if (pieceToTake == null) {
                return false;
            }

            return pieceToTake.getPieceColor().equals(PieceColor.BLACK);
        }
    }

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
}
