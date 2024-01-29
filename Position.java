package assiment1;

public class Position {
    private int width;
    private int length;

    public Position(int width, int length){
        this.width=width;
        this.length= length;
    }
    
    public Position(Position other) {
    	this.width=other.width;
        this.length= other.length;
    }

    public int y() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public int x() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public boolean equals(Position other) {
    	return (this.width == other.width) && (this.length == other.length);
    }

}
