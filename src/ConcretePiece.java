public class ConcretePiece implements Piece{
    private Player owner;
    private String type;

    public ConcretePiece(){
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
