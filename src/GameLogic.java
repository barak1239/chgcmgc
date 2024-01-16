public class GameLogic implements PlayableLogic {
    private ConcretePiece[][] Board;
    private ConcretePlayer player1, player2;

    public GameLogic() {
        ConcretePiece[][] Board = new ConcretePiece[11][11];
        player1 = new ConcretePlayer(false);
        player2 = new ConcretePlayer(true);
        reset();
    }

    @Override
    public boolean move(Position a, Position b) {
        Position c = new Position(0, 0);
        Position d = new Position(0, 10);
        Position e = new Position(10, 10);
        Position f = new Position(10, 0);
        //if origin position is null - impossible
        if (a == null) {
            return false;
        }
        if (a.x() == b.x() && a.y() == b.y())
            return false;
        if (Board[b.x()][b.y()] != null) {
            return false;
        }
        if (!(inBound(a) && inBound(b))) {
            return false;
        }

        // illegal only for pawn to be in the corner
        if (getPieceAtPosition(a) instanceof Pawn) {
            //corner only for pawn
            if (b.x() == 0) {
                if (b.y() == 0 || b.y() == 10)
                    return false;
            }
            if (b.x() == 10) {
                if (b.y() == 0 || b.y() == 10)
                    return false;
            }
        }
        //clear path
        if (a.x() == b.x() ^ a.y() == b.y()) {
            if (b.y() == a.y()) {
                for (int i = Math.min(a.x(), b.x()) + 1; i < Math.max(a.x(), b.x()); i++) {
                    if (Board[i][a.y()] != null)
                        return false;
                }
            } else {
                for (int i = Math.min(a.y(), b.y()) + 1; i < Math.max(a.y(), b.y()); i++) {
                    if (Board[a.x()][i] != null)
                        return false;
                }
            }
        } else {
            return false;
        }
        Board[b.x()][b.y()] = Board[a.x()][a.y()];
        Board[a.x()][a.y()] = null;

        return true;
    }

    private void check_for_kill(Position b) {
        Position[] to_kill = new Position[3];
        int[] x = {1, 0, -1, 0};
        int[] y = {0, 1, 0, -1};
        int ind = 0;
        for (int i = 0; i < x.length; i++) {
            Position killedPot = new Position(x[i] + b.x(), y[i] + b.y());
            if (!(inBound(killedPot)))
                continue;
            if (getPieceAtPosition(b).getOwner().isPlayerOne() != getPieceAtPosition(killedPot).getOwner().isPlayerOne()) {
                Position wall = new Position(x[i] + killedPot.x(), y[i] + killedPot.y());
                if (!(inBound(wall))) {
                    to_kill[ind] = killedPot;
                    ind++;
                    continue;
                }
                if (getPieceAtPosition(wall).getOwner().isPlayerOne() != getPieceAtPosition(killedPot).getOwner().isPlayerOne()) {
                    to_kill[ind] = killedPot;
                    ind++;
                }

            }
        }

    }
    private void kill(Position[] to_kill){
        for (int i=0; i<to_kill.length;i++){
            Board[to_kill[]];
        }
    }

    @Override
    public Piece getPieceAtPosition(Position position) {
        return Board[position.x()][position.y()];
    }

    @Override
    public Player getFirstPlayer() {
        return player1;
    }

    @Override
    public Player getSecondPlayer() {
        return player2;
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
        Board = new ConcretePiece[11][11];
        Board[3][0] = new Pawn(player1);
        Board[4][0] = new Pawn(player1);
        Board[5][0] = new Pawn(player1);
        Board[6][0] = new Pawn(player1);
        Board[7][0] = new Pawn(player1);
        Board[3][10] = new Pawn(player1);
        Board[4][10] = new Pawn(player1);
        Board[5][10] = new Pawn(player1);
        Board[6][10] = new Pawn(player1);
        Board[7][10] = new Pawn(player1);
        Board[0][3] = new Pawn(player1);
        Board[0][4] = new Pawn(player1);
        Board[0][5] = new Pawn(player1);
        Board[0][6] = new Pawn(player1);
        Board[0][7] = new Pawn(player1);
        Board[10][3] = new Pawn(player1);
        Board[10][4] = new Pawn(player1);
        Board[10][5] = new Pawn(player1);
        Board[10][6] = new Pawn(player1);
        Board[10][7] = new Pawn(player1);
        Board[1][5] = new Pawn(player1);
        Board[9][5] = new Pawn(player1);
        Board[5][1] = new Pawn(player1);
        Board[5][9] = new Pawn(player1);
        Board[3][5] = new Pawn(player2);
        Board[4][4] = new Pawn(player2);
        Board[4][6] = new Pawn(player2);
        Board[4][5] = new Pawn(player2);
        Board[5][3] = new Pawn(player2);
        Board[5][4] = new Pawn(player2);
        Board[5][5] = new Pawn(player2);
        Board[5][6] = new Pawn(player2);
        Board[7][5] = new Pawn(player2);
        Board[6][4] = new Pawn(player2);
        Board[6][5] = new Pawn(player2);
        Board[6][6] = new Pawn(player2);
        Board[5][5] = new King(player2);
        Board[5][7] = new Pawn(player2);
    }

    @Override
    public void undoLastMove() {

    }

    @Override
    public int getBoardSize() {
        return 11;
    }

    /////////////////////////////PRIVATE METHOD/////////////////////////////////////
    private boolean inBound(Position pos) {
        return pos.x() >= 0 && pos.x() < 11 && pos.y() >= 0 && pos.y() < 11;
    }


}
