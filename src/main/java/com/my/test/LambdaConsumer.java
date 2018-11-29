package com.my.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSON;

public class LambdaConsumer {

	public static void main(String[] args) {

		List<Person> lisiList = new ArrayList<>();
		Consumer<Person> consumer = x -> {
			if (x.name.equals("lisi")) {
				lisiList.add(x);
			}
			System.out.println(x.name + "#####");
		};

		consumer = consumer.andThen(x -> lisiList.removeIf(y -> y.age < 23));
		consumer = consumer.andThen(x -> System.out.println(x.name));
		System.out.println("----------");
		Stream.of(
				new Person(21, "zhangsan"), 
				new Person(22, "lisi"), 
				new Person(23, "wangwu"),
				new Person(24, "wangwu"), 
				new Person(23, "lisi"), 
				new Person(26, "lisi"), 
				new Person(26, "zhangsan"))
			.forEach(consumer);
		
		System.out.println(JSON.toJSONString(lisiList));

	}
}

class Person {
	
	public int age;
	public String name;
	
	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
