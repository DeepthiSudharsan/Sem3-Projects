package edu.amrita.cb.en.aie.ds2;

//Binary Search Tree
public class NEclass<T extends Comparable <T>> extends ConsSetNE<T>
{
    T key;
    ConsSetNE<T> left;
    ConsSetNE<T> right;
    
    static final ConsSetNE<Comparable<Object>> Empty = new ConsSetNE<Comparable<Object>>()
    {

		@Override
		public boolean has(Comparable<Object> k) 
		{
			return false;
		}

		@SuppressWarnings({"unchecked","rawtypes"})
		@Override
		public ConsSetNE add(Comparable<Object> k) 
		{
			
			return new NEclass(this,k,this);
		}
		@Override
		public String toString() 
		{
			
			return "";
		}
    	
    };
    public ConsSetNE<T> getEmpty()
    {
    	return (ConsSetNE<T>)Empty;
    }
    public NEclass(T k)
    {
    	key = k;
    	left = getEmpty();
    	right = getEmpty();
    }
    public NEclass(ConsSetNE<T> l,T k,ConsSetNE<T> r)
    {
    	key = k;
    	left = l;
    	right = r;
    }
	@Override
	public boolean has(T k) 
	{
		if(k.compareTo(key)<0) return left.has(k);
		else if(k.compareTo(key)>0) return right.has(k);
		return true;
	}

	@Override
	public ConsSetNE<T> add(T k) 
	{
		if(k.compareTo(key)<0) return new NEclass<T>(left.add(k),key,right);
		else if(k.compareTo(key)>0) return new NEclass<T>(left,key,right.add(k));
		return this;
	}
	@Override
	public String toString() 
	{
		return '('+left.toString() + "," + key + "," + right.toString() + ')'; 
	}
	
	public static void main(String[] args)
	{
		ConsSetNE s = new NEclass(5.2);
		System.out.println(s);
		ConsSetNE s1 = s.add(3.2);
		System.out.println(s1);
		ConsSetNE s2 = s1.add(74.5);
		System.out.println(s2);
		ConsSetNE s3 = s2.add(2.2);
		System.out.println(s3);
		ConsSetNE s4 = s3.add(-4.45);
		System.out.println(s4);
		ConsSetNE s5 = s4.add(6.0);
		System.out.println(s5);
		ConsSetNE s6 = s5.add(8.78);
		System.out.println(s6);
		ConsSetNE s7 = s6.add(1.1);
		System.out.println(s7);
	}

}
