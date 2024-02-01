package assiment1;

import java.util.Stack;

public class King extends ConcretePiece{
    private Player owner;
    private String type;
    
    private int kills;
    private Stack<Position> positions;
    private int id;
    private String name;
    private boolean dead;
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
