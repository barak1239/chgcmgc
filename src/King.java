public class King extends ConcretePiece{
    private Player owner;
    private String type;
    public King(Player owner) {
        this.owner = owner;
        if (this.owner.isPlayerOne())
            this.type = "♔";
        else
            this.type = "♚︎";
    }
}
