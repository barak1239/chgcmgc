package assiment1;

import java.util.Arrays;
import java.util.Stack;

public class GameLogic implements PlayableLogic {
    private Piece[][] Board;
    private ConcretePlayer player1, player2;
    private Piece[] pieces;
    private Position king;
    private boolean turn;
    private Stack<Integer> moves;
    // corner squares
	private Position[] corners = new Position[4];
    
	

    public GameLogic() {
    	this.moves = new Stack<Integer>();
    	this.pieces = new ConcretePiece[37];
        this.player1 = new ConcretePlayer(false);// black
        this.player2 = new ConcretePlayer(true); // white
        this.reset();
        this.corners[0] = new Position(0, 0);
        this.corners[1] = new Position(10, 0);
        this.corners[2] = new Position(0, 10);
        this.corners[3] = new Position(10, 10);
    }
    
    @Override
    public boolean move(Position a, Position b) {
    	// check if valid move
    	if (a == null) return false;
    	if (!(this.inBound(a) && this.inBound(b))) return false;
    	if ((a.x() != b.x()) && (a.y() != b.y())) return false;
    	if (this.getPieceAtPosition(b) != null) return false;
    	if (this.getPieceAtPosition(a) == null) return false;
    	if (this.turn != this.getPieceAtPosition(a).getOwner().isPlayerOne()) return false;
    	
    	
    	
    	if (this.getPieceAtPosition(a) instanceof Pawn)
    		for (int i = 0; i < this.corners.length; i++)
    			if (b.equals(this.corners[i])) 
    				return false;
    	
    	for (int i = Math.min(a.y(), b.y()); i < Math.max(a.y(), b.y()); i++)
    		for (int j = Math.min(a.x(), b.x()); j < Math.max(a.x(), b.x()); j++)
    			if (this.Board[j][i] != null)
                    return false;
    	
    	ConcretePiece piece = (ConcretePiece)this.getPieceAtPosition(b);
    	if (piece instanceof King) this.king = b;
    	this.saveMove(a, b);
    	
    	this.setPieceAtPosition(b, this.getPieceAtPosition(a));
    	this.setPieceAtPosition(a, null);
    	
        this.turn = !this.turn;
        this.kill(b, this.check_for_kill(b));
    	
    	return true;
    }

    
    
    @Override
    public Piece getPieceAtPosition(Position position) {
        return this.Board[position.x()][position.y()];
    }
    
    private void setPieceAtPosition(Position position, Piece piece) {
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
        	if (this.getPieceAtPosition(this.corners[i]) instanceof King) {
        		this.player2.anotherOne();
        		this.printGameLog();
        		return true;
        	}
    	
        
    	// check if black wins    	
    	int[] x = {0, 1, 0, -1};
    	int[] y = {1, 0, -1, 0};
    	for (int i = 0; i < x.length; i++) {
    		Position pos = new Position(this.king.x() + x[i], this.king.y() + y[i]);
    		if (this.inBound(pos)) {
        		temp = this.getPieceAtPosition(pos);
        		if (temp == null || temp.getOwner().isPlayerOne()) return false;
        	}
    	}
    	
    	this.player1.anotherOne();
    	this.printGameLog();
        return true;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return this.turn;
    }

    @Override
    public void reset() {
    	this.pieces = new ConcretePiece[37];
    	this.Board = new ConcretePiece[11][11];
    	this.king = new Position(5, 5);
    	this.turn = false;
    	for (int i = 0; i < 13; i++)
    		this.pieces[i] = (i == 6) ? new King(player2) : new Pawn(player2);
    	for (int i = 13; i < 37; i++)
    		this.pieces[i] = new Pawn(player1);
    	
    	Board[3][0] = this.pieces[13];
        Board[4][0] = this.pieces[14];
        Board[5][0] = this.pieces[15];
        Board[6][0] = this.pieces[16];
        Board[7][0] = this.pieces[17];
        Board[5][1] = this.pieces[18];
        Board[0][3] = this.pieces[19];
        Board[10][3] = this.pieces[20];
        Board[0][4] = this.pieces[21];
        Board[10][4] = this.pieces[22];
        Board[0][5] = this.pieces[23];
        Board[1][5] = this.pieces[24];
        Board[9][5] = this.pieces[25];
        Board[10][5] = this.pieces[26];
        Board[0][6] = this.pieces[27];
        Board[10][6] = this.pieces[28];
        Board[0][7] = this.pieces[29];
        Board[10][7] = this.pieces[30];
        Board[5][9] = this.pieces[31];
        Board[3][10] = this.pieces[32];
        Board[4][10] = this.pieces[33];
        Board[5][10] = this.pieces[34];
        Board[6][10] = this.pieces[35];
        Board[7][10] = this.pieces[36];
        
        Board[5][3] = this.pieces[0];
        Board[4][4] = this.pieces[1];
        Board[5][4] = this.pieces[2];
        Board[6][4] = this.pieces[3];
        Board[3][5] = this.pieces[4];
        Board[4][5] = this.pieces[5];
        Board[5][5] = this.pieces[6];
        Board[6][5] = this.pieces[7];
        Board[7][5] = this.pieces[8];
        Board[4][6] = this.pieces[9];
        Board[5][6] = this.pieces[10];
        Board[6][6] = this.pieces[11];
        Board[5][7] = this.pieces[12];
        
        
    }
    
    @Override
    public void undoLastMove() {
    	if (!this.moves.isEmpty()) {
    		ConcretePiece killer = null;
    		
    		this.turn = !this.turn;
    		int id  = this.moves.pop();
    		ConcretePiece piece = (ConcretePiece)this.pieces[id];
    		
    		Position current_pos = piece.undo();
    		Position last_pos = piece.getPosition();
    		this.setPieceAtPosition(last_pos, piece);
    		
    		
    		if (this.inBound(current_pos)) {
    			killer = (ConcretePiece)piece.getKiller();
    			piece.setKiller(null);
    			killer.addKills(-1);
    			this.setPieceAtPosition(current_pos, null);
    		}
    		else
    			this.undoLastMove();
    	}
    }

    @Override
    public int getBoardSize() {
        return 11;
    }

    /////////////////////////////PRIVATE METHOD/////////////////////////////////////
    private boolean inBound(Position pos) {
        return pos.x() >= 0 && pos.x() < 11 && pos.y() >= 0 && pos.y() < 11;
    }
    
    private Position[] check_for_kill(Position b) {
    	ConcretePiece piece = (ConcretePiece)this.getPieceAtPosition(b);
    	if (piece instanceof King) return null;
    	
        Position[] to_kill = new Position[3];
        int ind = 0;
        int[] x = {1, 0, -1, 0};
        int[] y = {0, 1, 0, -1};
        
        for (int i = 0; i < x.length; i++) {
        	Position temp_pos = new Position(b.x() + x[i], b.y() + y[i]);
        	if (!this.inBound(temp_pos)) continue;
        	if (this.getPieceAtPosition(temp_pos) == null) continue;
        	if (this.getPieceAtPosition(temp_pos) instanceof King) continue;
        	if (this.getPieceAtPosition(b).getOwner().isPlayerOne() != this.getPieceAtPosition(temp_pos).getOwner().isPlayerOne()) {
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
        		if (this.getPieceAtPosition(wall) == null) continue;
        		if (this.getPieceAtPosition(wall) instanceof King) continue;
        		if (this.getPieceAtPosition(wall).getOwner().isPlayerOne() == this.getPieceAtPosition(b).getOwner().isPlayerOne()) {
                    to_kill[ind] = temp_pos;
                    ind++;
                }
        	}
        }
        piece.addKills(ind);
        return to_kill;

    }
    
    private void kill(Position killer_pos, Position[] to_kill){
    	if (to_kill != null)
    		for (int i=0; i < to_kill.length; i++) 
    			if (to_kill[i] != null) {
    				ConcretePiece killer = (ConcretePiece)this.getPieceAtPosition(killer_pos);
    				((ConcretePiece)this.getPieceAtPosition(to_kill[i])).setKiller(killer);
    				this.saveMove(to_kill[i], new Position(-1, -1));
    				this.setPieceAtPosition(to_kill[i], null);
    			}
    				
    }
    
    private void saveMove(Position a, Position b) {
    	ConcretePiece piece = (ConcretePiece)this.getPieceAtPosition(a);
    	if (piece.isFirstMove()) piece.move(a);
    	piece.move(b);
    	this.moves.push(piece.getId() - 1);
    }
    
    private void printGameLog() {
    	Arrays.sort(this.pieces, new SortByPiece(1));
    	for (int i = 0; i < this.pieces.length; i++) {
    		ConcretePiece piece = (ConcretePiece)this.pieces[i];
    		System.out.print(piece.toString());
    		System.out.print(": ");
    		System.out.print(Arrays.toString(piece.arrayMoves()));
    		System.out.println();
    	}
    	System.out.println();
    	
    	Arrays.sort(this.pieces, new SortByPiece(2));
    	for (int i = 0; i < this.pieces.length; i++) {
    		ConcretePiece piece = (ConcretePiece)this.pieces[i];
    		System.out.print(piece.toString());
    		System.out.print(": ");
    		System.out.print("%d kills".formatted(piece.getKills()));
    		System.out.println();
    	}
    	System.out.println();
    	
    	Arrays.sort(this.pieces, new SortByPiece(3));
    	for (int i = 0; i < this.pieces.length; i++) {
    		ConcretePiece piece = (ConcretePiece)this.pieces[i];
    		System.out.print(piece.toString());
    		System.out.print(": ");
    		System.out.print("%d squares".formatted(piece.culc_squares()));
    		System.out.println();
    	}
    	Arrays.sort(this.pieces, new SortByPiece(0));
    }

}
