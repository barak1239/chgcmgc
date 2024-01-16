public class King extends ConcretePiece{
    private Player owner;
    private String type;
    private Position position;
    public King(Player owner) {
        super(owner);
    }
    @Override
    public String getType() {
        return "♔︎";
    }

}
