public class Main {
  public static void main(String[] args) {
    System.out.println("Factory pattern demo");
    

    Shape t = ShapeFactory.getShape("Triangle");
    Shape s = ShapeFactory.getShape("Square");

    t.draw();
    s.draw();

  }
}

interface Shape{
  void draw();
}

class Square implements shape{
  
  public void draw(){
    System.out.println("Drawing sq");
  }
}

class Triangle implements shape{
  public void draw(){
    System.out.println("Drawing triangle");
  }
}

class ShapeFactory{
  public static shape getShape(String s){
    if(s.equals("Square")) return new square();
    else if (s.equals("Triangle")) return new triangle();
    return null;
  }
}
