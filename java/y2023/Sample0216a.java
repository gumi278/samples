package y2023;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 配列に対するclone()の挙動確認
 * 
 * 配列は複製。
 * 要素は値コピー。
 */
public class Sample0216a
{
	public static void main(String[] args)
	{
		//clone()した直後の中身が同じであるにも関わらずhash値が異なることから、配列のインスタンスは別物と言える。
		//要素はコピーなのか、複製がキャストされたものなのかはここからは判断できない。
		System.out.println("+++sample1+++");
		sample1();
		System.out.println();
		
		//ls1[0]の値がls2[0]と同じなので、これらは同じインスタンスであると言える。
		//要素は値コピーされている。
		System.out.println("+++sample2+++");
		sample2();
		System.out.println();
		
		return;
	}
	
	static void sample1()
	{
		final int[] ls1 = new int[]{0, 1, 2, 3, 4, 5};
		int[] ls2 = null;
		String s1 = null;
		
		s1 = Arrays.toString(ls1);
		System.out.print("ls1: ");
		System.out.print(s1);
		System.out.print("/");
		System.out.println(System.identityHashCode(ls1));
		
		ls2 = (int[])ls1.clone();
		s1 = Arrays.toString(ls2);
		System.out.print("ls2: ");
		System.out.print(s1);
		System.out.print("/");
		System.out.println(System.identityHashCode(ls2));
		
		System.out.println();
		
		ls2[0]++;
		System.out.println("ls2[0]++");
		System.out.println();
		
		s1 = Arrays.toString(ls1);
		System.out.print("ls1: ");
		System.out.print(s1);
		System.out.print("/");
		System.out.println(System.identityHashCode(ls1));
		
		s1 = Arrays.toString(ls2);
		System.out.print("ls2: ");
		System.out.print(s1);
		System.out.print("/");
		System.out.println(System.identityHashCode(ls2));
		
		return;
	}
	
	
	static void sample2()
	{
		final AtomicInteger[] ls1 = new AtomicInteger[]{new AtomicInteger(0), new AtomicInteger(1), new AtomicInteger(2), new AtomicInteger(3), new AtomicInteger(4), new AtomicInteger(5)};
		AtomicInteger[] ls2 = null;
		String s1 = null;
		
		s1 = Arrays.toString(ls1);
		System.out.print("ls1: ");
		System.out.println(s1);
		
		ls2 = (AtomicInteger[])ls1.clone();
		s1 = Arrays.toString(ls2);
		System.out.print("ls2: ");
		System.out.println(s1);

		System.out.println();
		
		ls2[0].incrementAndGet();
		System.out.println("ls2[0].incrementAndGet()");

		System.out.println();
		
		s1 = Arrays.toString(ls1);
		System.out.print("ls1: ");
		System.out.println(s1);
		s1 = Arrays.toString(ls2);
		System.out.print("ls2: ");
		System.out.println(s1);
		
		return;
	}
}
