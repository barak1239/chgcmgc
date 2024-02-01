package assiment1;

import java.util.Stack;

public class ConcretePiece implements Piece{
    private Player owner;
    private String type;
    private int kills;
    private Stack<Position> positions;
    private int id;
    private String name;
    private static int counter_w = 0;
    private static int counter_b = 0;
    
    
    public ConcretePiece(Player owner){
        this.owner = owner;
        if (owner.isPlayerOne()) counter_b++;
        else counter_w++;
        this.id = counter_b + counter_w;
        this.name = String.format("%s%d", (!owner.isPlayerOne()) ? "A" : "D", (!owner.isPlayerOne()) ? counter_b : counter_w);
        this.positions = new Stack<Position>();
        
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
