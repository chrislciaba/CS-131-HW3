import java.util.concurrent.atomic.AtomicIntegerArray;

class GetNSetState implements State {
    private AtomicIntegerArray value;
    private byte maxval;

    GetNSetState(byte[] v) {
	int size = v.length;
	value = new AtomicIntegerArray(size);
	for(int i = 0; i < size; i++){
	    value.set(i, (int)v[i]);
	}
	maxval = 127;
    }

    GetNSetState(byte[] v, byte m) {
	int size = v.length;
	value = new AtomicIntegerArray(size);
	for(int i = 0; i < size; i++){
	    value.set(i, (int)v[i]);
	}
	maxval = m;
    }

    public int size() { return value.length(); }

    public byte[] current() {
	int size = value.length();
	byte[] temp = new byte[size];
	for(int i = 0; i < size; i++){
	    temp[i] = (byte)value.get(i);
	}
	return temp;
    }

    public boolean swap(int i, int j) {
	if (value.get(i) <= 0 || value.get(j) >= maxval) {
	    return false;
	}
	value.getAndDecrement(i);
	value.getAndIncrement(j);
	return true;
    }
}
