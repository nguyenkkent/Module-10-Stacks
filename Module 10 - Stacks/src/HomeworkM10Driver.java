import java.util.*;

public class HomeworkM10Driver {

	private static boolean allTestsPassed = true; 
	
	public static void main(String[] args) {

		System.out.println("\n--------------------------------TESTING PRIORITY PUSH--------------------------------");
		// parameter 1: the contents that will be pushed onto the stack- contents are pushed from start to finish.
		//              so the element at array index 0 will be on the bottom of the stack.
		// parameter 2: the element to priority push
		// parameter 3: the expected return value of the priority push method: 
		//              true if the element was prioritized, false if it was pushed without being prioritized
		// parameter 4: the expected contents of the stack after the priority push (listed bottom to top, just as parameter 1)
		// parameter 5: a description of the test
		testPriorityPush(new Integer[]{2, 4, 5, 8, 10},    12, false,   new Integer[]{2, 4, 5, 8, 10, 12},     "push an element not in the stack");
		testPriorityPush(new Integer[]{1, 5, 7, 8, 9, 10}, 1,  true,     new Integer[]{5, 7, 8, 9, 10, 1},      "push an element that is on the bottom of the stack");
		testPriorityPush(new Integer[]{3, 9, 8, 4, 6},     6,  true,     new Integer[]{3, 9, 8, 4, 6},          "push an element that is at the top of the stack");
		testPriorityPush(new Integer[]{7, 8, 3, 2, 1},     3,  true,     new Integer[]{7, 8, 2, 1, 3},          "push an element that is in the middle of the stack");
		testPriorityPush(new Integer[]{7, 8, 2, 1},        2,  true,     new Integer[]{7, 8, 1, 2},             "push an element that is in the middle of the stack");
		testPriorityPush(new Integer[]{7, 8, 3, 8, 1},     8,  true,     new Integer[]{7, 8, 3, 1, 8},          "push an element that is in the middle of the stack and repeated");
		testPriorityPush(new Integer[]{4, 2, 5, 2, 3, 6},  2,  true,     new Integer[]{4, 2, 5, 3, 6, 2},       "push an element that is in the middle of the stack and repeated");
		testPriorityPush(new Integer[]{1, 9, 9, 7, 9},     9,  true,     new Integer[]{1, 9, 9, 7, 9},          "push an element that is on the top of the stack and repeated");
		testPriorityPush(new String[]{"a", "b", "e", "c"}, new String("b"), true, new String[]{"a", "e", "c", "b"}, "test with Strings");
		testPriorityPush(new Integer[]{},                  2, false,    new Integer[]{2},                      "push on an empty stack");
		testPriorityPush(new Integer[]{4},                 4,   true,     new Integer[]{4},                      "push on a singleton stack with a match");
		testPriorityPush(new Integer[]{7},                 5,  false,    new Integer[]{7, 5},                   "push on a singleton stack without a match");
	
	  
    	// UN-COMMENT FOR EXTRA CREDIT	    
      
		System.out.println("\n\n--------------------------------TESTING PEEKNEXT IN LINKEDSTACK--------------------------------");
		// parameter 1: the contents that will be pushed onto the stack- contents are pushed from start to finish.
		//              so the element at array index 0 will be on the bottom of the stack.
		// parameter 2: the expected result (if there is one)
		//              note: if there is no expected result, that means the expected result is returning null 
		//                    or throwing an exception
		// parameter 3: the test description
		testPeekNextLinked(new Integer[]{},                        "empty stack");
		testPeekNextLinked(new Integer[]{1},                       "singleton stack");
		testPeekNextLinked(new Integer[]{1, 2},          1,        "even length stack");
		testPeekNextLinked(new Integer[]{4, 5, 6},       5,        "odd length stack");
		testPeekNextLinked(new Integer[]{3, 8, 7, 2},    7,        "odd length stack");
		testPeekNextLinked(new String[]{"a", "b", "c", "d"},  "c", "test with Strings");
     	
 		System.out.println("\n\n--------------------------------TESTING PEEKNEXT IN ARRAYSTACK--------------------------------");
 		// parameters are the same as for the linked version
		testPeekNextArray(new Integer[]{},                        "empty stack");
		testPeekNextArray(new Integer[]{1},                       "singleton stack");
		testPeekNextArray(new Integer[]{1, 2},          1,        "even length stack");
		testPeekNextArray(new Integer[]{4, 5, 6},       5,        "odd length stack");
		testPeekNextArray(new Integer[]{3, 8, 7, 2},    7,        "odd length stack");
		testPeekNextArray(new String[]{"a", "b", "c", "d"},  "c", "test with Strings");   
		
   
		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if(allTestsPassed) {
			System.out.println("----------Summary---------- \nAll automated tests have passed. \nBe sure to manually look at the output.\nAlso manually review your code for style and efficiency.");
		} else {
			System.out.flush();
			System.err.println("**********Summary********** ERROR: There is failure in at least one automated test. Review the output above for details.");
		}

    }      
	
	/*----------------------------------------------------------------------------------------------------------*/
	/* TESTER METHODS */
	/*----------------------------------------------------------------------------------------------------------*/
	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 * 
	 * Also, you can ignore the use of generics in the tester methods. These methods use
	 * generics at a level beyond which we use in our class. I only use them here to make this a robust 
	 * and useful testing file. You are NOT required to understand the use of generics in this way.
	 */	
	
	public static <T> void testPriorityPush(T[] contents, T pushElement, boolean expectedBooleanResult, T[] expectedContents, String testDescription) {
		LinkedStack<T> stack = new LinkedStack<T>();
		LinkedStack<T> printStack = new LinkedStack<T>();
		for(T element : contents) {
			stack.push(element);
			printStack.push(element);
		}
		boolean actualBooleanResult = stack.priorityPush(pushElement);
		printStack.priorityPush(pushElement);
		
		List<T> printList = createDisplayListFromStack(printStack);
		
		boolean contentsMatch = true;
		int index = -1;
		for(index = expectedContents.length-1; index>=0 && !stack.isEmpty() && contentsMatch; index--) {
			T stackValue = stack.pop();
			T expectedValue = expectedContents[index];
			if(!stackValue.equals(expectedValue)) {
				contentsMatch = false;
			}
		}
		if(!stack.isEmpty() || index>=0) {
			contentsMatch = false;
		}
		
		System.out.println("\nStack before push:    Bottom*" + Arrays.toString(contents) + "*Top \nPriority Push: " + pushElement);
		System.out.println("Expected stack after: Bottom*" + Arrays.toString(expectedContents)+ "*Top");
		System.out.println("  Actual stack after: Bottom*" + printList+ "*Top");
		System.out.println("Expected return result = " + expectedBooleanResult);
		System.out.println("  Actual return result = " + actualBooleanResult);
		
		if(!contentsMatch) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED. Incorrect stack contents for test: " + testDescription);
		}
		if(actualBooleanResult != expectedBooleanResult) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED. Incorrect return value for test: " + testDescription);
		} 
				
	}
	private static <T> List<T> createDisplayListFromStack(StackInterface<T> stack) {
		List<T> printList = new LinkedList<T>();
		while(!stack.isEmpty()) {
			printList.add(stack.pop());
		}
		Collections.reverse(printList);
		return printList;
	}
	public static <T> void testPeekNextLinked(T[] contents, String testDescription) {
		testPeekNextLinked(contents, null, testDescription);
	}
	public static <T> void testPeekNextLinked(T[] contents, T expectedResult, String testDescription) {
		LinkedStack<T> stack = new LinkedStack<T>();
		LinkedStack<T> printStack = new LinkedStack<T>();
		for(T element : contents) {
			stack.push(element);
			printStack.push(element);
		}
		testPeekNext(stack, printStack, expectedResult, testDescription);
	}
	public static <T> void testPeekNextArray(T[] contents, String testDescription) {
		testPeekNextArray(contents, null, testDescription);
	}
	public static <T> void testPeekNextArray(T[] contents, T expectedResult, String testDescription) {
		ArrayStack<T> stack = new ArrayStack<T>();
		ArrayStack<T> printStack = new ArrayStack<T>();
		for(T element : contents) {
			stack.push(element);
			printStack.push(element);
		}
		testPeekNext(stack, printStack, expectedResult, testDescription);
	}
	private static <T> int getStackSize(StackInterface<T> stack) {
		int size = 0;
		while(!stack.isEmpty()) {
			stack.pop();
			size++;
		}
		return size;
	}
	private static <T> void testPeekNext(StackInterface<T> stack, StackInterface<T> printStack, T expectedResult, String testDescription) {	
		List<T> printList = createDisplayListFromStack(printStack);
		boolean exceptionThrown = false;
		boolean goodExceptionType = true;
		T actualResult;
		Exception exception = null;
		try {
			actualResult = stack.peekNext();
		} catch(Exception ex) {
			// Normally this is bad practice to catch a general exception object. Don't do this!! 
			// I had to do this here to make this general since you can choose different kinds of exceptions for this question.
			exceptionThrown = true;
			actualResult = null;
			exception = ex;
		}
		if(exception!=null) {
			if(exception.getClass()==NullPointerException.class || exception.getClass()==ArrayIndexOutOfBoundsException.class) {
				goodExceptionType = false;
			}
		}
		
		int actualAfterSize = getStackSize(stack);
		int expectedAfterSize = printList.size();
		
		System.out.println("\nContents of stack: Bottom*" + printList + "*Top");
		if(expectedResult==null) {
			System.out.println("Expected peekNext behavior: exception thrown of an appropriate type");			
		} else {
			System.out.println("Expected peekNext = " + expectedResult);
		}
		if(exceptionThrown) {
			System.out.println("  Actual peekNext behavior: " + "exception thrown of type " + exception.getClass().getName());
		} else {
			System.out.println("  Actual peekNext = " + actualResult);
		}
		
		if(expectedResult==null) {
			if(! (actualResult==null || exceptionThrown)) {
				System.out.println("\nContents of stack: Bottom*" + printList + "*Top");

				System.out.println("Expected peekNext behavior: exception thrown of an appropriate type");			
				if(exceptionThrown) {
					System.out.println("  Actual peekNext behavior: " + "exception thrown of type " + exception.getClass().getName());
				} else {
					System.out.println("  Actual peekNext = " + actualResult);
				}
				allTestsPassed = false;
				System.out.println("*****TEST FAILED for: " + testDescription);
				System.out.println("     Expected behavior is either a returned null or a thrown exception of appropriate type.");
			} else if(exceptionThrown && !goodExceptionType) {
				
				System.out.println("\nContents of stack: Bottom*" + printList + "*Top");
				System.out.println("Expected peekNext behavior: exception thrown of an appropriate type");			
				System.out.println("  Actual peekNext behavior: " + "exception thrown of type " + exception.getClass().getName());
				allTestsPassed = false;
				System.out.println("*****TEST MAYBE FAILED for: " + testDescription);
				System.out.println("     Double-check how empty and singleton stacks should handled. Be consistent with the peek() method.");
				System.out.println("     Note that it's possible there isn't really an error here, but you should double-check carefully.");

			}
		} else { 
			if(!expectedResult.equals(actualResult)) {
				System.out.println("\nContents of stack: Bottom*" + printList + "*Top");
				System.out.println("Expected peekNext = " + expectedResult);
				System.out.println("  Actual peekNext = " + actualResult);
				System.out.println("*****TEST FAILED for: " + testDescription);
				allTestsPassed = false;
			}
		}
		if(actualAfterSize!=expectedAfterSize) {
			System.out.println("\nContents of stack: Bottom*" + printList + "*Top");
			System.out.println("*****TEST FAILED. Size of stack should not change. Test: " + testDescription);
			System.out.println("     Expected size = " + expectedAfterSize);
			System.out.println("       Actual size = " + actualAfterSize);
			allTestsPassed = false;		
		}
	}    
}