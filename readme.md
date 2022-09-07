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