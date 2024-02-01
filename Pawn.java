package assiment1;


public class Pawn extends ConcretePiece{
    private String type;
    public Pawn(Player owner){
    	
    	super(owner);
    	this.type = "♟";
    	
    }
    @Override
    public String getType() {
        return "♟";
    }

}
