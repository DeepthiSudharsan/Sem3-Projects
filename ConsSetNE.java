package edu.amrita.cb.en.aie.ds2;

public abstract class ConsSetNE<T> 
{
   public abstract boolean has(T key);
   public abstract ConsSetNE<T> add(T key);
}
