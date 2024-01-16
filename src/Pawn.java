public class Pawn extends ConcretePiece{
    private Player owner;
    private String type;

    public Pawn(Player owner){
    this.owner = owner;
        if(this.owner.isPlayerOne())
            this.type ="♙";
        else
            this.type="♟︎";
    }

}
