package cache;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import java.io.File;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by bubblebitoey on 1/28/2017 AD.
 */
public class CacheTest {
	private static final String FILE_NAME = "test";
	
	@Test
	public void testString() {
		String text = "Some String";
		
		Cache.loadCache().setFileName(FILE_NAME).saveToFile(text);
		
		String loadString = Cache.loadCache().setFileName(FILE_NAME).loadFromFile(text.getClass());
		
		assertEquals(text, loadString);
	}
	
	@Test
	public void testXString() {
		String text = "S&P";
		
		Cache.loadCache().setFileName(FILE_NAME).saveToFile(text);
		
		String loadString = Cache.loadCache().setFileName(FILE_NAME).loadFromFile(text.getClass());
		
		assertEquals(text, loadString);
	}
	
	@Test
	public void testChar() {
		Character c = 'y';
		Cache.loadCache().setFileName(FILE_NAME).saveToFile(c);
		
		Character loadChar = Cache.loadCache().setFileName(FILE_NAME).loadFromFile(c.getClass());
		
		assertEquals(c, loadChar);
	}
	
	@Test
	public void testInteger() {
		Integer integer = 120000;
		
		Cache.loadCache().setFileName(FILE_NAME).saveToFile(integer);
		
		Integer loadInt = Cache.loadCache().setFileName(FILE_NAME).loadFromFile(integer.getClass());
		
		assertEquals(integer, loadInt);
	}
	
	@Test
	public void testArray() {
		String[] arr = new String[]{"I", "Hate", "This", "App"};
		
		Cache.loadCache().setFileName(FILE_NAME).saveToFile(arr);
		
		String[] loadArr = Cache.loadCache().setFileName(FILE_NAME).loadFromFile(arr.getClass());
		
		assertArrayEquals(arr, loadArr);
	}
	
	@AfterClass
	public static void removeTestFile() {
		 File f = new File(FILE_NAME);
		assertTrue(f.delete());
	}
}