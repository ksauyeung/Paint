package paint.store;

import paint.OutOfRangeException;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ObjectArrayBuffer implements BufferStore {
    private Object[] buffer;
    private int cursor;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ReadWriteLock cursorLock = new ReentrantReadWriteLock();

    private int horizontal, vertical;

    public ObjectArrayBuffer(int horizontal, int vertical) {
        if(horizontal < 0 || vertical < 0) {
            throw new IllegalArgumentException();
        }
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.buffer = new Character[horizontal * vertical];
    }

    private int getIndex(int posX, int posY) {
        if(posX > horizontal || posX <= 0 || posY > vertical || posY <= 0) {
            return -1;
        }
        int index = posX - 1 + (posY - 1) * horizontal;
        return index;
    }

    @Override
    public boolean write(Object data, int posX, int posY) {
        readWriteLock.writeLock().lock();
        try {
            int idx = getIndex(posX, posY);
            if (idx < 0 || idx > buffer.length - 1) {
                return false;
            }
            buffer[idx] = data;
        } finally {
            readWriteLock.writeLock().unlock();
        }
        return true;
    }

    @Override
    public Object read(int posX, int posY) throws OutOfRangeException {
        readWriteLock.readLock().lock();
        try {
            int idx = getIndex(posX, posY);
            if(idx < 0 || idx >= buffer.length) {
                throw new OutOfRangeException();
            }
            return buffer[idx];
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public boolean isValid(int posX, int posY) {
        int idx = getIndex(posX, posY);
        return idx >= 0  && idx < buffer.length;
    }

    public void clear() {
        readWriteLock.writeLock().lock();
        try {
            for(int i=0; i<buffer.length; i++) {
                buffer[i] = null;
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public boolean first() {
        cursor = 0;
        return true;
    }

    @Override
    public boolean next() {
        cursorLock.writeLock().lock();
        try {
            if(cursor == buffer.length - 1) {
                return false;
            }
            ++cursor;
            return true;
        } finally {
            cursorLock.writeLock().unlock();
        }
    }

    @Override
    public boolean hasNext() {
        cursorLock.readLock().lock();
        try {
            return cursor < buffer.length - 1;
        } finally {
            cursorLock.readLock().unlock();
        }
    }

    @Override
    public boolean previous() {
        cursorLock.readLock().lock();
        try {
            return cursor > 0;
        } finally {
            cursorLock.readLock().unlock();
        }
    }

    @Override
    public Object get() {
        readWriteLock.readLock().lock();
        cursorLock.readLock().lock();
        try {
           return buffer[cursor];
        } finally {
            cursorLock.readLock().unlock();
            readWriteLock.readLock().unlock();
        }
    }
}
