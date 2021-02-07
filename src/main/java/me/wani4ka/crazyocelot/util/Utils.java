package me.wani4ka.crazyocelot.util;

import lombok.val;

import java.util.Random;
import java.util.stream.Collector;

public class Utils {
	private static final String randomStrAlphabet = "AĄBCĆDEĘFGHIJKLŁMNŃOÓPRSŚTUWYZŻŹaąbcćdeęfghijklłmnńoóprsśtuwyzżź";
	private static final Random random = new Random();
	private static final Collector<Object, StringBuilder, String> sbCollector = Collector.of(
			StringBuilder::new,
			StringBuilder::append,
			StringBuilder::append,
			StringBuilder::toString
	);

	public static <T> Object getField(Class<T> clazz, T obj, String fieldName) {
		try {
			val f = clazz.getDeclaredField(fieldName);
			f.setAccessible(true);
			return f.get(obj);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> void setField(Class<T> clazz, T obj, String fieldName, Object val) {
		try {
			val f = clazz.getDeclaredField(fieldName);
			f.setAccessible(true);
			f.set(obj, val);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	public static String randomString(int length) {
		return random.ints(length, 0, randomStrAlphabet.length())
				.mapToObj(randomStrAlphabet::charAt).collect(sbCollector);
	}
}
