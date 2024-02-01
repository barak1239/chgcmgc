package assiment1;

public class ConcretePlayer implements Player{
    private boolean player1;
    private int wins;
    public ConcretePlayer(boolean player1){
        this.player1=player1;
        wins =0;
    }
    @Override
    public boolean isPlayerOne() {
        return this.player1;
    }

    @Override
    public int getWins() {
        return wins;
    }
    
    public void anotherOne(){
        wins++;
    }
}
