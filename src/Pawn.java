public class Pawn extends ConcretePiece{
    private Player owner;
    private String type;
    private Position position;
    public Pawn(Player owner){
    super(owner);
    }
    @Override
    public String getType() {
        return "♟";
    }

}
