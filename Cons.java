package edu.amrita.cb.en.aie.ds2;

import java.util.NoSuchElementException;

public class Cons<T> extends Conslist<T>
{
	T h;
	Conslist<T> t;
	
	public static final Conslist<Object> Nil = new Conslist<Object>()
	{
		public Object head()
		{
			throw new NoSuchElementException("head of empty list");
		}
		
		public Conslist<Object> tail()
		{
			return this;
		}

		public Object reduce(ReduceClosure<Object> f) 
		{
			throw new NoSuchElementException("head of empty list");
		}
	};
	
	public Cons(T h,Conslist<T> t)
	{
		this.h = h;
		this.t = t;
	}
	
	public static <T> Conslist<T> list(T...lists)
	{
		return make_tr((Conslist<T>)Nil,lists.length-1,lists);
	}
	@SuppressWarnings({ "unused", "unchecked" })
	private static <T> Conslist<T> make(int index,T...lists)
	{
		if(index>=lists.length)
			return (Conslist<T>)Nil;
		else
			return new Cons<T>(lists[index],make(++index,lists));
	}
	private static <T> Conslist<T> make_tr(Conslist<T> acc,int index,T...lists)
	{
		if(index<0)
			return acc;
		else return make_tr(new Cons<T>(lists[index],acc),--index,lists);
	}
	@Override
	public T head()
	{
		return h;
	}
	
	@Override
	public Conslist<T> tail()
	{
		return t;
	}
	
	@Override
	public boolean isEmpty()
	{
		return false;
	}

	@Override
	public void print()
	{
		System.out.print(h + " ");
		//tail recursion...
		t.print();
	}
	@Override
	public int length()
	{
		return length_tr(0,this);
	}
	private static <T> int length_tr(int acc, Conslist<T> c)
	{
		if(c == Nil) return acc;
		else return length_tr(acc+1,c.tail());
	}
	public Conslist<T> reverse()
	{
		return reverse((Conslist<T>) Nil,this);
	}
	public static <T> Conslist<T> reverse(Conslist<T> acc,Conslist<T> current)
	{
		if(current == Nil) return acc;
		else return reverse(new Cons<T>(current.head(),acc),current.tail());
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Conslist<?> map(MapClosure<T,?> funct)
	{
		return new Cons(funct.lambda(this.h),this.t.map(funct));
		//We cant give <?> in Cons and so to let the compiler decide based on lambda we dont give anything
	}
	
	@Override
	public T reduce(ReduceClosure<T> funct)
	{
		return reduce_tr(this.head(),this.tail(),funct);
	}
	private static <T> T reduce_tr(T acc,Conslist<T> c,ReduceClosure<T> f)
	{
		if(c==Nil) return acc;
		else
		{
			return reduce_tr(f.lambda(acc,c.head()),c.tail(),f);
		}
	}
	public static void main(String []args)
	{
		Conslist<Double> c = Cons.list(1.3,2.2,4.3,6.4,5.78,6.45,7.23,9.87,8.96,10.0);
		c.print();
		System.out.println();
		
		MapClosure<Double,Double> m = new MapClosure<Double,Double>(){
		public Double lambda(Double e)
		{
			return e*e;
		}
		};
		
		ReduceClosure<Double> r = new ReduceClosure<Double>()
		{
			public Double lambda(Double acc,Double e)
			{
				return (acc+e);
			}
		};
		
		Conslist cSq = c.map(m);
		cSq.print();
		System.out.println("\n"+cSq.reduce(r));
		System.out.println(c.reduce(r));
		
	}
}