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

