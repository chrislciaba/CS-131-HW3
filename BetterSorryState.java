import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReadWriteLock;

class BetterSorryState implements State {
    private byte[] value;
    private byte maxval;
    private ReadWriteLock l;
    private Lock writeLock1;
    
    BetterSorryState(byte[] v) { 
	value = v; 
	maxval = 127; 
	l = new ReentrantReadWriteLock();
	writeLock1 = l.writeLock();
    }

    BetterSorryState(byte[] v, byte m) { 
	value = v; 
	maxval = m; 
	l = new ReentrantReadWriteLock();
	writeLock1 = l.writeLock();
    }

    public int size() { return value.length; }

    public byte[] current() {
	return value;
    }

    public boolean swap(int i, int j) { 
        if (value[i] <= 0 || value[j] >= maxval) {
	    return false;
	}
	writeLock1.lock();
	value[i]--;
	value[j]++;
	writeLock1.unlock();
	return true;
    }
}
