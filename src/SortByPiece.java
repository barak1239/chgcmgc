

import java.util.Comparator;

public class SortByPiece implements Comparator<Piece> {
	private int flag;
	private boolean winner;
	public SortByPiece(int flag) {
		this.flag = flag;
	}
	
	
	
	public int compare(Piece a, Piece b)
    {
		if (flag == 1) { // number of steps
			return ((ConcretePiece)a).stepsNum() - ((ConcretePiece)b).stepsNum();
		}
		else if (flag == 2) { // number of kills
			return ((ConcretePiece)b).getKills() - ((ConcretePiece)a).getKills();
		}
		else if (flag == 3) { // number of squares
			return ((ConcretePiece)b).culc_squares() - ((ConcretePiece)a).culc_squares();
			
		}
		else {
			return ((ConcretePiece)a).getId() - ((ConcretePiece)b).getId();
		}
		
    }
}
