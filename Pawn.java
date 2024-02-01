package assiment1;

import java.util.Stack;

public class Pawn extends ConcretePiece{
    private Player owner;
    private String type;
    private Position position;
    
    private int kills;
    private Stack<Position> positions;
    private int id;
    private String name;
    private boolean dead;
    public Pawn(Player owner){
    	
    	super(owner);
    	this.type = "♟";
    	
    }
    @Override
    public String getType() {
        return "♟";
    }

}
