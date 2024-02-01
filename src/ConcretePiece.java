
import java.util.Stack;

public class ConcretePiece implements Piece{
    private Player owner;
    private String type;
    private int kills;
    private Piece killed_by;
    private Stack<Position> positions;
    private int id;
    private String name;
    private static int counter_w = 0;
    private static int counter_b = 0;
    
    
    public ConcretePiece(Player owner){
        this.owner = owner;
        if (!owner.isPlayerOne()) counter_b++;
        else counter_w++;
        this.id = counter_b + counter_w;
        this.name = String.format("%s%d", (!owner.isPlayerOne()) ? "A" : "D", (!owner.isPlayerOne()) ? counter_b : counter_w);
        if (this.type=="♔︎")
            this.name = "K7";
        this.positions = new Stack<Position>();
        this.killed_by = null;
        
    }
    
    public Piece getKiller() {
    	return this.killed_by;
    }
    
    public void setKiller(Piece killer) {
    	this.killed_by = killer;
    }
    
    public Position[] arrayMoves() {
    	return this.positions.toArray(new Position[this.positions.size()]);
    }
    
    public void move(Position pos) {
    	this.positions.push(pos);
    }
    
    public Position undo() {
    	return this.positions.pop();
    }
    
    public Position getPosition() {
    	return this.positions.peek();
    }
    
    public int stepsNum() {
    	return this.positions.size();
    }
    
    public boolean isFirstMove() {
    	return this.positions.isEmpty();
    }
    
    public void addKills(int killed) {
    	this.kills += killed;
    }
    
    public int getKills() {
    	return this.kills;
    }
    
    public int getId() {
    	return this.id;
    }
    
    public int culc_squares() {
		if (this.stepsNum() <= 1) return 0;
		//Stack<Position> temp = new Stack<Position>();
		Position current_pos = this.undo();
		Position prev_pos = this.getPosition();
		
		int squares_num = this.culc_squares();
		
		this.move(current_pos);
				
		return current_pos.distance(prev_pos) + squares_num;
	}
    
    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public String getType() {
        return this.type;
    }
    
    @Override
    public String toString() {
    	return this.name;
    }
}
