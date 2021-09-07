package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.StampedLock;

/**
 * showcase the Stampedlock
 */
public class IncomeTaxDept {
    AtomicLong counter = new AtomicLong(0);
    private List<TaxPayer> taxPayersList;
    private double totalRevenue;
    private final StampedLock sl = new StampedLock();
    Logger LOG = new Logger(IncomeTaxDept.class);

    public IncomeTaxDept(long revenue, int numberOfTaxPayers) {
        this.totalRevenue = revenue;
        taxPayersList = new ArrayList<TaxPayer>(numberOfTaxPayers);
    }

    /**
     * This method is to show the feature of writeLock() method
     */
    public void payTax(TaxPayer taxPayer) {
        double taxAmount = taxPayer.getTaxAmount();
        long stamp = sl.writeLock();
        counter.incrementAndGet();
        try {
            totalRevenue += taxAmount;
            LOG.info(taxPayer.getTaxPayerName() + " paid tax, now Total Revenue --->>> " + this.totalRevenue);
        } finally {
            sl.unlockWrite(stamp);
            counter.decrementAndGet();
        }
    }

    /**
     * This method is to show the feature of writeLock() method
     */
    public double getFederalTaxReturn(TaxPayer taxPayer) {
        double incomeTaxRetunAmount = taxPayer.getTaxAmount() * 10 / 100;
        long stamp = sl.writeLock();
        counter.incrementAndGet();
        try {
            this.totalRevenue -= incomeTaxRetunAmount;
        } finally {
            sl.unlockWrite(stamp);
            counter.decrementAndGet();
        }
        return incomeTaxRetunAmount;
    }

    /**
     * This method is to show the feature of readLock() method
     */
    public double getTotalRevenue() {
        long stamp = sl.readLock();
        double rev = 0;
        try {
            rev = this.totalRevenue;
        } finally {
            sl.unlockRead(stamp);
        }
        
        return rev;
    }

    /**
     * This method is to show the feature of tryOptimisticRead() method
     */
    public double getTotalRevenueOptimisticRead() {
        long stamp = sl.tryOptimisticRead();
        double balance = this.totalRevenue;
        // calling validate(stamp) method to ensure that stamp is valid, if not then
        // acquiring the read lock
        if (!sl.validate(stamp)) {
            LOG.info("stamp for tryOptimisticRead() is not valid now, so acquiring the read lock");
            stamp = sl.readLock();
            try {
                balance = this.totalRevenue;
            } finally {
                sl.unlockRead(stamp);
            }
        }

        return balance;
    }

    /**
     * This method is to show the feature of tryConvertToWriteLock() method
     */
    public double getStateTaxReturnUisngConvertToWriteLock(TaxPayer taxPayer) {
        double incomeTaxRetunAmount = taxPayer.getTaxAmount() * 5 / 100;
        long stamp = sl.readLock();
        // Trying to upgrade the lock from read to write
        long ws = sl.tryConvertToWriteLock(stamp);
        // Checking if tryConvertToWriteLock got success otherwise call writeLock method
        if (ws == 0L) {
            LOG.info("stamp is zero for tryConvertToWriteLock(), so acquiring the write lock");
            sl.unlockRead(stamp);
            stamp = sl.writeLock();
            try {
                this.totalRevenue -= incomeTaxRetunAmount;
            } finally {
                sl.unlockWrite(stamp);
            }
        }else{
            this.totalRevenue -= incomeTaxRetunAmount;
            stamp = ws;
            sl.unlock(stamp);
        }
        
        return incomeTaxRetunAmount;
    }

    public void registerTaxPayer(TaxPayer taxPayer) {
        taxPayersList.add(taxPayer);
    }

    public List<TaxPayer> getTaxPayersList() {
        return taxPayersList;
    }
}