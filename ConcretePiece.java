package assiment1;

public class ConcretePiece implements Piece{
    private Player owner;
    private String type;
    private Position position;
    public ConcretePiece(Player owner){
        this.owner = owner;
    }
    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public String getType() {
        return type;
    }
}
