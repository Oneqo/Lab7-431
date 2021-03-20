public class Box {
    private int height, width, length;
    public Box(){
        this(1,1,1);
    }
    public Box(int height, int length, int width){
        this.height = height;
        this.length = length;
        this.width = width;
    }
    public Box(Box otherBox){
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
        return String.format("Box width: %d, Box length: %d, Box height: %d", width, length,height);
    }
}
