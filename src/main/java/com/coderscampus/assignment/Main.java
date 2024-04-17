package com.coderscampus.assignment;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		getData();
	}

	public static void getData() throws InterruptedException, ExecutionException {

		Assignment8 assignment = new Assignment8();
		CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(assignment::getNumbers);
		List<Integer> numbersList = future.get();
		System.out.println("Fetched numbers: " + numbersList);
		printOccurrences(numbersList);

	}

	private static void printOccurrences(List<Integer> numbersList) {

		Map<Integer, Long> occurance = (Map<Integer, Long>) numbersList.stream()
				.collect(Collectors.groupingBy(number -> number, Collectors.counting()));
		occurance.forEach((number, count) -> System.out.println(number + "=" + count));
	}

}
