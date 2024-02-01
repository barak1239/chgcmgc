

public class King extends ConcretePiece{
    private String type;
    private String name;
    public King(Player owner) {
        super(owner);
        this.type = "♔︎";
    }
    @Override
    public String getType() {
        return "♔︎";
    }

}
