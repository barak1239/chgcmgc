public class Position {
    private int width;
    private int length;
    private Position b;

    public Position(int width, int length){
        this.width=width;
        this.length= length;
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

}
