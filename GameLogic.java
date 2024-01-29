package assiment1;

public class GameLogic implements PlayableLogic {
    private ConcretePiece[][] Board;
    private ConcretePlayer player1, player2;
    
    private Position king;
    private boolean turn;
    
    // corner squares
	private Position[] corners = new Position[4];
    

    public GameLogic() {
        this.Board = new ConcretePiece[11][11];
        this.player1 = new ConcretePlayer(false);
        this.player2 = new ConcretePlayer(true);
        this.reset();
        this.king = new Position(5, 5);
        this.turn = false;
        this.corners[0] = new Position(0, 0);
        this.corners[1] = new Position(10, 0);
        this.corners[2] = new Position(0, 10);
        this.corners[3] = new Position(10, 10);
    }
    
    
    
    @Override
    public boolean move(Position a, Position b) {
    	// check if valid move
    	if (a == null) return false;
    	if ((a.x() != b.x()) || (a.y() != b.y())) return false;
    	if (this.Board[b.x()][b.y()] != null) return false;
    	if (!(this.inBound(a) && this.inBound(b))) return false;
    	
    	if (this.getPieceAtPosition(a) instanceof Pawn)
    		for (int i = 0; i < this.corners.length; i++)
    			if (b.equals(this.corners[i])) 
    				return false;
    	
    	for (int i = Math.min(a.y(), b.y()); i < Math.max(a.y(), b.y()); i++)
    		for (int j = Math.min(a.x(), b.x()); j < Math.max(a.x(), b.x()); j++)
    			if (this.Board[j][i] != null)
                    return false;
    	
    	this.setPieceAtPosition(b, this.Board[a.x()][a.y()]);
        this.setPieceAtPosition(a, null);
        this.turn = !this.turn;
        this.kill(this.check_for_kill(b));
    	
    	return true;
    }

    private Position[] check_for_kill(Position b) {
    	if (this.getPieceAtPosition(b) instanceof King) return null;
        Position[] to_kill = new Position[3];
        int ind = 0;
        int[] x = {1, 0, -1, 0};
        int[] y = {0, 1, 0, -1};
        
        for (int i = 0; i < x.length; i++) {
        	Position temp_pos = new Position(b.x() + x[i], b.y() + y[i]);
        	if (!this.inBound(temp_pos)) continue;
        	if (getPieceAtPosition(b).getOwner().isPlayerOne() != getPieceAtPosition(temp_pos).getOwner().isPlayerOne()) {
        		Position wall = new Position(b.x() + (2 * x[i]), b.y() + (2 * y[i]));
        		if (!inBound(wall)) {
                    to_kill[ind] = temp_pos;
                    ind++;
                    continue;
                }
        		for (int corner = 0; corner < this.corners.length; corner++)
        			if (wall.equals(this.corners[corner])) {
	                    to_kill[ind] = temp_pos;
	                    ind++;
	                    continue;
        			}
        		if (getPieceAtPosition(wall).getOwner().isPlayerOne() == getPieceAtPosition(b).getOwner().isPlayerOne()) {
                    to_kill[ind] = temp_pos;
                    ind++;
                }
        	}
        }
        return to_kill;

    }
    
    private void kill(Position[] to_kill){
    	if (to_kill != null)
    		for (int i=0; i < to_kill.length; i++) 
    			if (to_kill[i] != null)
    				this.setPieceAtPosition(to_kill[i], null);
    }

    @Override
    public Piece getPieceAtPosition(Position position) {
        return this.Board[position.x()][position.y()];
    }
    
    private void setPieceAtPosition(Position position, ConcretePiece piece) {
    	this.Board[position.x()][position.y()] = piece;
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
    	
        Piece temp = null;
        // check if white wins
        for (int i = 0; i < this.corners.length; i++)
        	if (this.getPieceAtPosition(this.corners[i]) instanceof King) return true;
    	
        
    	// check if black wins
    	Position top = new Position(this.king.x(), this.king.y() - 1);
    	Position buttom = new Position(this.king.x(), this.king.y() + 1);
    	Position left = new Position(this.king.x() - 1, this.king.y());
    	Position right = new Position(this.king.x() + 1, this.king.y());
    	
    	boolean flag = true;
    	if (this.inBound(top)) {
    		temp = this.getPieceAtPosition(top);
    		flag = flag && temp.getOwner().isPlayerOne();
    	}
    	if (this.inBound(buttom)) {
    		temp = this.getPieceAtPosition(buttom);
    		flag = flag && temp.getOwner().isPlayerOne();
    	}
    	if (this.inBound(left)) {
    		temp = this.getPieceAtPosition(left);
    		flag = flag && temp.getOwner().isPlayerOne();
    	}
    	if (this.inBound(right)) {
    		temp = this.getPieceAtPosition(right);
    		flag = flag && temp.getOwner().isPlayerOne();
    	}
        return flag;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return this.turn;
    }

    @Override
    public void reset() {
        Board = new ConcretePiece[11][11];
        for (int i = 3; i <= 7; i++) Board[i][0] = new Pawn(player1);
        for (int i = 3; i <= 7; i++) Board[i][10] = new Pawn(player1);
        for (int i = 3; i <= 7; i++) Board[0][i] = new Pawn(player1);
        for (int i = 3; i <= 7; i++) Board[10][i] = new Pawn(player1);
        
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
        
        // need to be king
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
