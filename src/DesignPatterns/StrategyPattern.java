package DesignPatterns;
//https://www.tutorialspoint.com/design_pattern/strategy_pattern.htm
//a class behavior can be changed  at runtime

class Context{
    private Strategy strategy;
    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    public int executeStrategy(int num1,int num2){
        return strategy.doOperation(num1,num2);
    }
}
interface Strategy{
    int doOperation(int num1,int num2);
}
class Add implements Strategy{
    @Override
    public int doOperation(int num1,int num2){
        return num1+num2;
    }
}
class Subtract implements Strategy{
    @Override
    public int doOperation(int num1,int num2){
        return num1-num2;
    }
}

public class StrategyPattern {
    public static void main(String[] args){
        Context context = new Context(new Add());
        System.out.println(context.executeStrategy(1,2));
        context = new Context(new Subtract());
        System.out.println(context.executeStrategy(1,2));
    }
}

