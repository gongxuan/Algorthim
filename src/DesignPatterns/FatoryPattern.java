package DesignPatterns;
//Object: Create Object without exposing creation logic to the Client.
public class FatoryPattern {
    public static void main(String[] args){
        ShapeFactory factory = new ShapeFactory();
        Shape shape = factory.getShape("Square");
        shape.draw();
        shape = factory.getShape("Rectangle");
        shape.draw();
    }
}

class ShapeFactory{
    public Shape getShape(String shapeName){
        if(shapeName==null){
            return null;
        }
        if(shapeName.equalsIgnoreCase("Rectangle")){
            return new Rectangle();
        }else if(shapeName.equalsIgnoreCase("Square")){
            return new Square();
        }
        return null;
    }
}

interface Shape{
    void draw();
}

class Rectangle implements Shape{
    @Override
    public void draw(){
        System.out.println("draw Rectangle");
    }
}

class Square implements Shape{
    @Override
    public void draw(){
        System.out.println("draw Square");
    }
}