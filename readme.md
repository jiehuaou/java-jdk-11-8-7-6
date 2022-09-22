# java-7x

* AutoCloseable object is called automatically when exiting a try-with-resources block for which the object has been declared in the resource specification header. This construction ensures prompt release, avoiding resource exhaustion exceptions.


# java-8

## why use lamda

 1. make code simple
 2. invoke direct lambda without new class creation
 3. make lazy call conditionally

## ReentrantLocks

* lock()
* unlock()

## ReentrantReadWriteLock

* lock.writeLock.lock()
* lock.writeLock.unlock()
* lock.readLock.lock()
* lock.readLock.unlock()

Reentrant locks allow programmers to acquire multiple read locks as long as there are no write locks.
if used carelessly, reentrant RW locks can starve (make hungry) threads writing to the lock under load condition, 
This can cause serious performance issues.

## StampedLock

There are two elements of a stamped lock, namely – the lock version and locking mode. The lock version is also known as a stamp, and is essentially a value of type “long” returned every time a lock is acquired.

**optimistic read lock acquisition can help improve performance**
```java
private double balance;
...
long stamp = lock.tryOptimisticRead();
double currentBalance = this.balance ;
if (!lock.validate(stamp)) {
	stamp = lock.readLock();
	try {
		currentX = this.balance ;
	} finally {
		lock.unlockRead(stamp);
	}
}
```

**Conditional Write Locking & Conversion**

reduce the chance of using exclusive access (contention) and improves throughput.

```java
private double balance;
...
long stamp = lock.tryOptimisticRead();    // OptimisticRead
double currentX = balance ;
if (!lock.validate(stamp)) {
    long stamp = lock.readLock();    // Read Locking
	if ( stamp ... is valid) {
	
		if ( check if this.balance is expected value ) {  // only in some condition then acquire writeLock
			
			long ws = sl.tryConvertToWriteLock(stamp);  // Write Locking
			if (ws != 0L) {
				this.balance = newValue ;   // change value
			}
			
		}
	}
	...
}
```

another example
```java
void moveIfAtOrigin(double newX, double newY) { // upgrade
  // Could instead start with optimistic, not read mode
  long stamp = sl.readLock();
  try {
    while (x == 0.0 && y == 0.0) {             // only in some condition then acquire writeLock
      long ws = sl.tryConvertToWriteLock(stamp);
      if (ws != 0L) {
        stamp = ws;
        x = newX;
        y = newY;
        break;
      }
      else {
        sl.unlockRead(stamp);
        stamp = sl.writeLock();
      }
    }
  } finally {
    sl.unlock(stamp);
  }
}
 ```

## Java Volatile Keyword

### Full volatile Visibility Guarantee

If Thread A writes to a volatile variable and Thread B subsequently reads the same volatile variable, then all variables visible to Thread A before writing the volatile variable, will also be visible to Thread B after it has read the volatile variable.

```java
public class MyClass {
    private int years;
    private int months
    private volatile int days;
    
    // invoke by thread B
    public int totalDays() {
        int total = this.days;
        total += months * 30;
        total += years * 365;
        return total;
    }

    // invoke by thread A
    public void update(int years, int months, int days){
        this.years  = years;
        this.months = months;
        this.days   = days;
    }
}
```

when Thread A write value to **days**, the values of years and months are also written to main memory.

When Thread B read the value of **days**, the values of months and years are also read into main memory. Therefore you are guaranteed to see the latest values of **days, months and years** with the above read sequence.

### Java volatile Happens-Before Guarantee

In particular, if all Threads share the following class variables:
```java
class MyClass {
  private String s = "running";
  private volatile boolean b = false;
}
```

In your case, you have the following events:

* Thread 1 writes "**done**" to s
* Thread 1 writes **true** to b
* Thread 2 reads from b
* Thread 2 reads from s

And the following rules come into play:

* "If x and y are actions of the same thread and x comes before y in program order, then hb(x, y)." (the program order rule)
* "A write to a volatile field (§8.3.1.4) happens-before every subsequent read of that field." (the volatile rule)

The following **happens-before** relationships therefore exist:

* Thread 1 writes to s **happens before** Thread 1 writes to b (program order rule)
* Thread 1 writes to b **happens before** Thread 2 reads from b (volatile rule)
* Thread 2 reads from b **happens before** Thread 2 reads from s (program order rule)
* If you follow that chain, you can see that as a result:

Thread 1 writes to s **happens before** Thread 2 reads from s

finally Thread 2 print s with "**done**"

### *When is volatile Enough?*

In case **only one thread reads and writes** the value of a volatile variable and **other threads only read** the variable, then the reading threads are guaranteed to see the latest value written to the volatile variable. 

### *When is volatile not Enough?*

if two threads are both reading and writing to a shared variable, then using the volatile keyword for that is not enough. You need to use a synchronized in that case to guarantee that the reading and writing of the variable is atomic. Reading or writing a **volatile variable does not block threads** reading or writing. 

### Java Synchronized Entry Visibility Guarantee

When a thread enters a synchronized block, all variables visible to the thread are refreshed from the main memory.

### Java Synchronized Exit Visibility Guarantee

When a thread exits a synchronized block, all variables visible to the thread are written back to the main memory.

### Java Synchronized Happens Before Guarantee

Java **synchronized** blocks provide two *happens-before* guarantees: 

* **One guarantee** related to the beginning of a synchronized block,  
* **another guarantee** related to the end of a synchronized block.

### Java Synchronized Block Beginning Happens Before Guarantee

The beginning of a Java synchronized block provides the visibility guarantee, that when a thread enters a synchronized block all variables visible to the thread will be read from the main memory.

```java
public void get(Values v) {
        synchronized(this) {
            v.valC = this.valC;
        }
        v.valB = this.valB;
        v.valA = this.valA;
    }
```

The synchronized block at the beginning of the method will guarantee that all of the variables **this.valC, this.valB and this.valA** are refreshed (read in) from the main memory.

### Java Synchronized Block End Happens Before Guarantee

The end of a synchronized block provides the visibility guarantee that all changed variables will be written back to the main memory when the thread exits the synchronized block.

```java
public void set(Values v) {
        this.valA = v.valA;
        this.valB = v.valB;
        synchronized(this) {
            this.valC = v.valC;
        }
    }
```

As you can see, the synchronized block at the end of the method will guarantee that all of the changed variables this.valA, this.valB and this.valC will be written back to (flushed) to main memory when the thread calling set() exits the synchronized blocks.

## Stream Replace IfElse

define Map of Predicate\<T>, Function<T, R>, which Predicate\<T> represent the condition 
and Function<T, R> provide the result logic.

```java
    public static void main(String[] args) {

        Map<Predicate<String>, Function<String, String>> rules = new HashMap<>();
        
        final Predicate<String> rule1 = (String x)->x.equalsIgnoreCase("world");
        final Predicate<String> rule2 = (String x)->x.equalsIgnoreCase("hello");
        final Predicate<String> rule3 = (String x)->x.equalsIgnoreCase("web");
        rules.put(rule1, (String x) -> "this is world : " + x);
        rules.put(rule2, (String x) -> "this is hello : " + x);
        rules.put(rule3, (String x) -> "this is web : " + x);

        String result = applyRule(rules, "hello");
        System.out.println("result -> " + result);

        result = applyRule(rules, "world");
        System.out.println("result -> " + result);

        result = applyRule(rules, "123");
        System.out.println("result -> " + result);
    }

    public static String applyRule(Map<Predicate<String>, Function<String, String>> rules, String param) {
        return rules.entrySet().stream()
                .filter(e -> e.getKey().test(param))
                .map(e -> e.getValue().apply(param))
                .findFirst()
                .orElseGet(() -> "unknown : " + param); // default logic
    }
```

