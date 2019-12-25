package MultiThread;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class BoundedBlockingQueue {

    private int capacity;
    private final AtomicInteger curSize = new AtomicInteger(0);
    private boolean hasInit = false;
    private Queue<Object> buffer = null;
    private Lock lock;
    //for multi-put.
    private Lock putLock;
    //wait queue for waiting not full signal
    private Condition notFull;
    //wait queue ofr waiting not empty signal
    private Condition notEmpty;

    public void init(int capacity) throws Exception{
        if(capacity<=0)
            throw new Exception("<=0");
        lock.lock();
        try{
            if(buffer==null){
                buffer = new LinkedList<>();
                notFull = lock.newCondition();
                notEmpty = lock.newCondition();
                this.capacity = capacity;
                hasInit = true;
            }else{
                throw new Exception("Already initialized");
            }
        }finally{
            lock.unlock();
        }
    }
    private void checkInit() throws Exception{
        if(!hasInit)
            throw new Exception("Init not done!");
    }

    public Object get() throws Exception{
        checkInit();
        lock.lock();
        try{
            while(curSize.get()<=0){
                try {
                    notEmpty.await();
                }catch (InterruptedException e){
                    throw new Exception(e);
                }
            }
            curSize.getAndDecrement();
            notFull.signal();
            return buffer.remove(0);
        }catch(Exception e){
            throw new Exception(e);
        }finally{
            lock.unlock();
        }
    }

    public void put(Object obj) throws Exception{
        putLock.lock();
        lock.lock();
        try{
            while(curSize.get()==capacity){
                try {
                    notFull.await();
                }catch (InterruptedException e){
                    e.getStackTrace();
                }
            }
            curSize.getAndIncrement();
            buffer.add(obj);
            notEmpty.signal();
        }catch (Exception e){
            throw e;
        }finally{
            lock.unlock();
            putLock.unlock();
        }
    }

    public void multiput(List list) throws Exception{
        putLock.lock();
        lock.lock();
        try{
            for(Object obj : list){
                while(curSize.get()==capacity){
                    try {
                        notFull.await();
                    }catch (InterruptedException e){
                        e.getStackTrace();
                    }
                }
                curSize.getAndIncrement();
                buffer.add(obj);
                notEmpty.signal();
            }
        }catch (Exception e){
            throw e;
        }finally{
            lock.unlock();
            putLock.unlock();
        }
    }
}
