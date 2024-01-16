public class GameLogic implements PlayableLogic{
    private ConcretePiece[][] Board;
    private ConcretePlayer player1, player2;
    public GameLogic(){
        ConcretePiece [][] Board = new ConcretePiece[11][11];
    }
    @Override
    public boolean move(Position a, Position b) {
        return false;
    }

    @Override
    public Piece getPieceAtPosition(Position position) {
        return  Board[position.getWidth()][position.getWidth()];
    }

    @Override
    public Player getFirstPlayer() {
        return null;
    }

    @Override
    public Player getSecondPlayer() {
        return null;
    }

    @Override
    public boolean isGameFinished() {
        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void undoLastMove() {

    }

    @Override
    public int getBoardSize() {
        return 11;
    }
}
