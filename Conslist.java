package edu.amrita.cb.en.aie.ds2;

public abstract class Conslist<T> //<T> is type parameter
{
     abstract public T head();
     abstract public Conslist<T> tail();
     abstract public T reduce(ReduceClosure<T> f);
     public boolean isEmpty()
     {
    	 return true;
     }
     public void print()
     {
    	 
     }
     public Conslist<?> reverse()
     {
    	 return Cons.Nil;
     }
     public int length()
     {
    	 return 0;
     }
     
     public Conslist<?> map(MapClosure<T,?> f) //<?> tells compiler that at the moment we dk what to give
     {
    	 //<T> list of integers n <U> list of strings
    	 return Cons.Nil;
     }
}
