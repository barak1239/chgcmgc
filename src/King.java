package assiment1;

public class King extends ConcretePiece{
    private String type;
    private String name;
    public King(Player owner) {
        super(owner);
        this.name = "K7";
        this.type = "♔︎";
    }
    @Override
    public String getType() {
        return "♔︎";
    }

}
