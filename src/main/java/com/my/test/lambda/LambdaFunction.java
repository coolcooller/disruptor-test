package com.my.test.lambda;

import java.util.function.Function;

public class LambdaFunction {

	public static void main(String[] args) {

		Function<Integer, Integer> times2 = i -> i * 2;
		Function<Integer, Integer> squared = i -> i * i;

		System.out.println(times2.apply(4));
		System.out.println(squared.apply(4));

		System.out.println(times2.compose(squared).apply(4)); // 32
																// 先4×4然后16×2,先执行apply(4)，在times2的apply(16),先执行参数，再执行调用者。
		System.out.println(times2.andThen(squared).apply(4)); // 64 先4×2,然后8×8,先执行times2的函数，在执行squared的函数。

		System.out.println(Function.identity().compose(squared).apply(4)); // 16
	}

	/*
	 * 前两个输出比较容易理解，就是把参数值赋值到方法里面，再由apply返回的结果输出即可。
	 * 主要就是第3，4个的compose和andThen，由于代码里面是很简单两个数学计算，先说明大概流程，估计推一推就懂了。
	 * 
	 * 在compose里面，先执行squared的apply(4)方法，然后再把结果给times2让他去执行16×2的方法。
	 * 而andThen恰恰相反，由英文来理解，先后顺序，即先执行times2的apply方法，再把结果执行squared的apply方法。
	 * 这样，就能得到最终的结果。
	 * 
	 * 而在最后一个输出中，虽然有compose方法，但是前一个的Function.identity并没有任何方法，因为identity仅仅是返回一个方法，
	 * 所以也就执行了squared这一个方法而已。
	 * 
	 * 通过上文的分析，大概可以理解Function这个类了，就是一个方法，有种c++里面函数指针的感觉，一个变量可以指向一个方法，并且可以把两个方法组合起来使用
	 * (使用compose和andThen)，而可以通过identity这个静态方法来获取当前执行的方法 ---------------------
	 * 作者：6点A君 来源：CSDN 原文：https://blog.csdn.net/anLA_/article/details/78191494
	 * 版权声明：本文为博主原创文章，转载请附上博文链接！
	 */

}
