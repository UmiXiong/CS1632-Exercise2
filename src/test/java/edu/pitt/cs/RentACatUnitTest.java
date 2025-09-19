package edu.pitt.cs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentACatUnitTest {

	/**
	 * The test fixture for this JUnit test. Test fixture: a fixed state of a set of
	 * objects used as a baseline for running tests. The test fixture is initialized
	 * using the @Before setUp method which runs before every test case. The test
	 * fixture is removed using the @After tearDown method which runs after each
	 * test case.
	 */

	RentACat r; // Object to test
	Cat c1; // First cat object
	Cat c2; // Second cat object
	Cat c3; // Third cat object

	ByteArrayOutputStream out; // Output stream for testing system output
	PrintStream stdout; // Print stream to hold the original stdout stream
	String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n") for use in assertEquals

	@Before
	public void setUp() throws Exception {
		// INITIALIZE THE TEST FIXTURE
		
		// 1. Create a new RentACat object and assign to r using a call to RentACat.createInstance(InstanceType).
		// Passing InstanceType.IMPL as the first parameter will create a real RentACat object using your RentACatImpl implementation.
		// Passing InstanceType.MOCK as the first parameter will create a mock RentACat object using Mockito.
		// Which type is the correct choice for this unit test?  I'll leave it up to you.  The answer is in the Unit Testing Part 2 lecture. :)
		// TODO: Fill in

//		current test class should be real
		r=RentACat.createInstance(InstanceType.IMPL);

//		dependecy should be mock
		// 2. Create a Cat with ID 1 and name "Jennyanydots", assign to c1 using a call to Cat.createInstance(InstanceType, int, String).
		// Passing InstanceType.IMPL as the first parameter will create a real cat using your CatImpl implementation.
		// Passing InstanceType.MOCK as the first parameter will create a mock cat using Mockito.
		// Which type is the correct choice for this unit test?  Again, I'll leave it up to you.
		// TODO: Fill in
		c1=Cat.createInstance(InstanceType.MOCK,1,"Jennyanydots");

//		since mock doesn't store the original value, so the return stuff should be defined here
		when(c1.getId()).thenReturn(1);
		when(c1.toString()).thenReturn("ID 1. Jennyanydots\n");
		when(c1.getRented()).thenReturn(false);

		// 3. Create a Cat with ID 2 and name "Old Deuteronomy", assign to c2 using a call to Cat.createInstance(InstanceType, int, String).
		// TODO: Fill in
		c2=Cat.createInstance(InstanceType.MOCK,2,"Old Deuteronomy");
		//		since mock doesn't store the original value, so the return stuff should be defined here
		when(c2.getId()).thenReturn(2);
		when(c2.toString()).thenReturn("ID 2. Old Deuteronomy\n");
		when(c2.getRented()).thenReturn(false);



		// 4. Create a Cat with ID 3 and name "Mistoffelees", assign to c3 using a call to Cat.createInstance(InstanceType, int, String).
		// TODO: Fill in
		c3=Cat.createInstance(InstanceType.MOCK,3,"Mistoffelees");
		//		since mock doesn't store the original value, so the return stuff should be defined here
		when(c3.getId()).thenReturn(3);
		when(c3.toString()).thenReturn("ID 3. Mistoffelees\n");
		when(c3.getRented()).thenReturn(false);



		// 5. Redirect system output from stdout to the "out" stream
		// First, make a back up of System.out (which is the stdout to the console)
		stdout = System.out;
		// Second, update System.out to the PrintStream created from "out"
		// TODO: Fill in.  Refer to the textbook chapter 14.6 on Testing System Output.


		//		from 14.6, i should use a out to get the byte xxx stream and then new PrintStream(out)
		out=new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	}

	@After
	public void tearDown() throws Exception {
		// Restore System.out to the original stdout
		System.setOut(stdout);
//		clear output
		out.reset();

		// Not necessary strictly speaking since the references will be overwritten in
		// the next setUp call anyway and Java has automatic garbage collection.
		r = null;
		c1 = null;
		c2 = null;
		c3 = null;
	}

	/**
	 * Test case for Cat getCat(int id).
	 * 
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is null.
	 *                 System output is "Invalid cat ID." + newline.
	 * </pre>
	 * 
	 * Hint: You will need to use Java reflection to invoke the private getCat(int)
	 * method. efer to the Unit Testing Part 1 lecture and the textbook appendix 
	 * hapter on using reflection on how to do this.  Please use r.getClass() to get
	 * the class object of r instead of hardcoding it as RentACatImpl.
	 */
	@Test
	public void testGetCatNullNumCats0() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO: Fill in
//		java reflection, with class and parameters
		Method m=r.getClass().getDeclaredMethod("getCat",int.class);
//		also private method being tested
		m.setAccessible(true);
		Object result=m.invoke(r,2);
//		test whether the value is null
		assertNull(result);
		System.out.println("invalid cat ID. "+newline);


	}

	/**
	 * Test case for Cat getCat(int id).
	 *
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call getCat(2).
	 * Postconditions: Return value is not null.
	 *                 Returned cat has an ID of 2.
	 * </pre>
	 *
	 * Hint: You will need to use Java reflection to invoke the private getCat(int)
	 * method. efer to the Unit Testing Part 1 lecture and the textbook appendix
	 * hapter on using reflection on how to do this.  Please use r.getClass() to get
	 * the class object of r instead of hardcoding it as RentACatImpl.
	 */
	@Test
	public void testGetCatNumCats3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO: Fill in
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		//		java reflection, with class and parameters
		Method m=r.getClass().getDeclaredMethod("getCat",int.class);
//		also private method being tested
		m.setAccessible(true);
		Cat result=(Cat) m.invoke(r,2);
//		test whether the value is null
		assertEquals(2,result.getId());
	}

	/**
	 * Test case for String listCats().
	 *
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "".
	 * </pre>
	 */
	@Test
	public void testListCatsNumCats0() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO: Fill in
		Method m=r.getClass().getDeclaredMethod("listCats");
//		also private method being tested
		m.setAccessible(true);
		String result=(String) m.invoke(r);

//		test whether the value is null
		assertEquals("",result);
	}

	/**
	 * Test case for String listCats().
	 *
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call listCats().
	 * Postconditions: Return value is "ID 1. Jennyanydots\nID 2. Old
	 *                 Deuteronomy\nID 3. Mistoffelees\n".
	 * </pre>
	 */
	@Test
	public void testListCatsNumCats3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO: Fill in
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		//		java reflection, with class and parameters
		Method m=r.getClass().getDeclaredMethod("listCats");
//		also private method being tested
		m.setAccessible(true);
		String result=(String) m.invoke(r);
//		test whether the value is null
		assertEquals("ID 1. Jennyanydots\nID 2. Old " +
				"Deuteronomy\nID 3. Mistoffelees\n",result);
	}

	/**
	 * Test case for boolean renameCat(int id, String name).
	 *
	 * <pre>
	 * Preconditions: r has no cats.
	 * Execution steps: Call renameCat(2, "Garfield").
	 * Postconditions: Return value is false.
	 *                 c2 is not renamed to "Garfield".
	 *                 System output is "Invalid cat ID." + newline.
	 * </pre>
	 */
	@Test
	public void testRenameFailureNumCats0() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO: Fill in
		//java reflection, with class and parameters
		Method m=r.getClass().getDeclaredMethod("renameCat", int.class, String.class);
//		also private method being tested
		m.setAccessible(true);
		boolean result=(boolean) m.invoke(r,2,"Garfield");
//		test whether the value is null
		assertFalse(result);
		assertNotEquals("Garfield",c2.getName());
		System.out.println("Invalid cat ID. "+newline);

	}

	/**
	 * Test case for boolean renameCat(int id, String name).
	 *
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call renameCat(2, "Garfield").
	 * Postconditions: Return value is true.
	 *                 c2 is renamed to "Garfield".
	 * </pre>
	 */
	@Test
	public void testRenameNumCat3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO: Fill in
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		Method m=r.getClass().getDeclaredMethod("renameCat", int.class, String.class);
//		also private method being tested
		m.setAccessible(true);
		boolean result=(boolean) m.invoke(r,2,"Garfield");
//		test whether the value is null
		assertTrue(result);

//		check the name instead of assert it, since mock will not change the name of the cat
		verify(c2).renameCat("Garfield");
	}

	/**
	 * Test case for boolean rentCat(int id).
	 *
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is true.
	 *                 c2 is rented as a result of the execution steps.
	 *                 System output is "Old Deuteronomy has been rented." + newline
	 * </pre>
	 */
	@Test
	public void testRentCatNumCats3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO: Fill in
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		when(c2.getRented()).thenReturn(false);
		Method m=r.getClass().getDeclaredMethod("rentCat", int.class);
//		also private method being tested
		m.setAccessible(true);
		boolean result=(boolean) m.invoke(r,2);
//		test whether the value is null
		assertTrue(result);
//		assertTrue(c2.getRented());

		System.out.println("Old Deuteronomy has been rented." + newline);
	}

	/**
	 * Test case for boolean rentCat(int id).
	 *
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call rentCat(2).
	 * Postconditions: Return value is false.
	 *                 c2 stays rented.
	 *                 System output is "Sorry, Old Deuteronomy is not here!" + newline
	 * </pre>
	 */
	@Test
	public void testRentCatFailureNumCats3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO: Fill in
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		when(c2.getRented()).thenReturn(true);
		Method m=r.getClass().getDeclaredMethod("rentCat", int.class);
//		also private method being tested
		m.setAccessible(true);
		boolean result=(boolean) m.invoke(r,2);
//		test whether the value is null
		assertFalse(result);
		assertTrue(c2.getRented());
		System.out.println("Sorry, Old Deuteronomy is not here!" + newline);
	}

	/**
	 * Test case for boolean returnCat(int id).
	 *
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 *                c2 is rented.
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is true.
	 *                 c2 is returned as a result of the execution steps.
	 *                 System output is "Welcome back, Old Deuteronomy!" + newline
	 * </pre>
	 */
	@Test
	public void testReturnCatNumCats3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO: Fill in
		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
//		first rented, then returned back
		when(c2.getRented()).thenReturn(true).thenReturn(false);
		Method m=r.getClass().getDeclaredMethod("returnCat", int.class);
//		also private method being tested
		m.setAccessible(true);
		boolean result=(boolean) m.invoke(r,2);
//		test whether the value is null
		assertTrue(result);
		System.out.println("Welcome back, Old Deuteronomy!" + newline);

	}

	/**
	 * Test case for boolean returnCat(int id).
	 *
	 * <pre>
	 * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
	 * Execution steps: Call returnCat(2).
	 * Postconditions: Return value is false.
	 *                 c2 stays not rented.
	 *                 System output is "Old Deuteronomy is already here!" + newline
	 * </pre>
	 */
	@Test
	public void testReturnFailureCatNumCats3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// TODO: Fill in

		r.addCat(c1);
		r.addCat(c2);
		r.addCat(c3);
		Method m=r.getClass().getDeclaredMethod("returnCat", int.class);
//		also private method being tested
		m.setAccessible(true);
		boolean result=(boolean) m.invoke(r,2);
//		test whether the value is null
		assertFalse(result);
		System.out.println("Old Deuteronomy is already here!" + newline);
	}

}