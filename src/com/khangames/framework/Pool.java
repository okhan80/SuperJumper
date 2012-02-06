package com.khangames.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: omarkhan
 * Date: 1/13/12
 * Time: 3:30 PM
 * 
 * Purpose: To Reuse previously created instances of a class that is used frequently
 */
public class Pool<T> {
    
    public interface PoolObjectFactory<T> {
        public T createObject();
    }
    
    private final List<T> freeObjects;
    private final PoolObjectFactory<T> factory;
    private final int maxSize;
    
    public Pool(PoolObjectFactory<T> factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObjects = new ArrayList<T>(maxSize);
    }

    /**
     * Responsible for handing brand-new instance of the type that Pool holds via factories createObject()
     * or return a pooled instance in case there is one in the arraylist. If used, get recycled objects as
     * long as Pool has stored freeObject list. Otherwise method will create a new one via factory
     * 
     * @return T - Generic Object
     */
    public T newObject() {
        T object = null;
        
        if(freeObjects.size() == 0) {
            object = factory.createObject();
            
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        
        return object;
    }

    /**
     * Reinsert objects that are no longer used. Insert the object into freeObjects list if it is not at capacity
     * If list is full, object is not added and likely will be consumed by garbage collector next time it executes
     *
     * @param object - Generic Object
     */
    public void free(T object) {
        if(freeObjects.size() < maxSize) {
            freeObjects.add(object);
        }
    }
}
