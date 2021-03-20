package P1;

public class BoxP1 {
    private int height, width, length;
    public BoxP1(){
        this(1,1,1);
    }
    public BoxP1(int height, int length, int width){
        this.height = height;
        this.length = length;
        this.width = width;
    }
    public BoxP1(BoxP1 otherBox){
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
        return String.format("P1.Box width: %d, P1.Box length: %d, P1.Box height: %d", width, length,height);
    }
}
