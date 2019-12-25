package DesignPatterns;
//This pattern involves a single class which is responsible to
// create an object while making sure that only single object gets created.
public class Singleton {

}

class SingletonObject{
    private static final SingletonObject instace = new SingletonObject();
    private SingletonObject(){
        //make the constructor private so that this class cannot be
        //instantiated
    }
    public SingletonObject getInstace(){
        return this.instace;
    }
}

class SingletonObject2{
    private static SingletonObject2 instance;
    public  SingletonObject2(){
    }
    public static synchronized SingletonObject2 getInstace(){
        if(instance==null){
            instance = new SingletonObject2();
        }
        return instance;
    }
}

class SingletonObjectDoubleCheck{
    private static SingletonObjectDoubleCheck instance;
    private static final Object lock = new Object();
    public  SingletonObjectDoubleCheck(){
    }
    public static SingletonObjectDoubleCheck getInstace(){
        SingletonObjectDoubleCheck localRef = instance;
        if(localRef==null){
            synchronized (lock){
                localRef = instance;
                if(localRef==null){
                    localRef = new SingletonObjectDoubleCheck();
                }
                instance = localRef;
            }
        }
        return localRef;
    }
}
