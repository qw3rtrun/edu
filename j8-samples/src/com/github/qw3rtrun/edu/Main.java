package com.github.qw3rtrun.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;


public class Main {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("1231", "234", "567", "4");
		List<String> list2 = new ArrayList<>(list);
		list2.forEach((String s) -> {System.out.println(s);});
		list2.forEach(System.out::println);
		
		list2.removeIf((s) -> !s.startsWith("t"));
		list2.forEach(System.out::println);
		
		list2.replaceAll((s) -> s+"!");
		
		try
		{
		final List<Integer> result = list.parallelStream()
			.filter((s) -> s.length() < 4)
			.<Integer>map(Integer::parseInt)
			.filter((i) -> i%2==0)
			.collect(Collectors.<Integer>toList());
			System.out.println(result);
		}
		catch (NumberFormatException e) {
			System.out.println("!!!!!");
		}
		
		
		BinaryOperator<String> bi = (s1, s2) -> s1+s2;
		String s = "!";
		UnaryOperator<String> un = (s1) -> bi.apply(s1, s);
		
		System.out.println(un.apply("Hello"));
	}

}
