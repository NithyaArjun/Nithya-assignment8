package com.coderscampus.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		getData();
	}

	public static void getData() throws InterruptedException, ExecutionException {

		Assignment8 assignment = new Assignment8();
		ExecutorService service = Executors.newFixedThreadPool(50);
		CompletableFuture<List<Integer>>[] futures = new CompletableFuture[1000];
		
		for (int i = 0; i < 1000; i++) {
			futures[i] = CompletableFuture.supplyAsync(assignment::getNumbers, service);
		}

		CompletableFuture.allOf(futures).join();
		List<Integer> allNumbers = new ArrayList<>();
		
		for (CompletableFuture<List<Integer>> future : futures) {
			allNumbers.addAll(future.get());
		}

		printOccurrences(allNumbers);

	}

	private static void printOccurrences(List<Integer> numbersList) {

		Map<Integer, Long> occurance = (Map<Integer, Long>) numbersList.stream()
				.collect(Collectors.groupingBy(number -> number, Collectors.counting()));
		occurance.forEach((number, count) -> System.out.println(number + "=" + count));
	}

}
