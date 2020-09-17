public class Main {
  public static void main(String[] args) {
    System.out.println("Factory pattern demo");
    

    shape t = shapeFactory.getShape("Triangle");
    shape s = shapeFactory.getShape("Square");

    t.draw();
    s.draw();

  }
}

interface shape{
  void draw();
}

class square implements shape{
  
  public void draw(){
    System.out.println("Drawing sq");
  }
}

class triangle implements shape{
  public void draw(){
    System.out.println("Drawing triangle");
  }
}

class shapeFactory{
  public static shape getShape(String s){
    if(s.equals("Square")) return new square();
    else if (s.equals("Triangle")) return new triangle();
    return null;
  }
}
