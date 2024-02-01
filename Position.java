

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
    
    public int distance(Position other) {
    	return (int) Math.sqrt(Math.pow(this.x() - other.x(), 2) + Math.pow(this.y() - other.y(), 2));
    }
    
    public boolean equals(Position other) {
    	return (this.width == other.width) && (this.length == other.length);
    }
    
    @Override
    public String toString() {
    	return "(%d, %d)".formatted(this.x(), this.y());
    }
    
}
