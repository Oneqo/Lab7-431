package P1.P2;

public class BoxP2 {
    private int height, width, length;
    public BoxP2(){
        this(1,1,1);
    }
    public BoxP2(int height, int length, int width){
        this.height = height;
        this.length = length;
        this.width = width;
    }
    public BoxP2(BoxP2 otherBox){
        this(otherBox.height, otherBox.length, otherBox.width);
    }

    public boolean isAUnitBox(){
        return (height == length && length == width && width == 1);
    }
    public int getVolume(){
        return height*width*length;
    }
    public void setDimensions(int length, int width, int height){
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("P2.Box width: %d, P2.Box length: %d, P2.Box height: %d", width, length,height);
    }
}
