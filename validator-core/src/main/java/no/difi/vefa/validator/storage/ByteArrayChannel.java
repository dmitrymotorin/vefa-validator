package no.difi.vefa.validator.storage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

public class ByteArrayChannel implements SeekableByteChannel {

    private int position = 0;

    private int size;

    private byte[] bytes;

    public ByteArrayChannel(byte[] bytes, int position) {
        this(bytes, position, bytes.length);
    }

    public ByteArrayChannel(byte[] bytes, int position, int size) {
        this.bytes = bytes;
        this.position = position;
        this.size = size;
    }

    @Override
    public int read(ByteBuffer dst) throws IOException {
        int length = Math.min(1024, size - position);
        byte[] result = new byte[length];

        System.arraycopy(bytes, position, result, 0, length);

        dst.put(result);
        return length;
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        // Not supported.
        return 0;
    }

    @Override
    public long position() throws IOException {
        return position;
    }

    @Override
    public SeekableByteChannel position(long newPosition) throws IOException {
        return new ByteArrayChannel(bytes, (int) newPosition);
    }

    @Override
    public long size() throws IOException {
        return size;
    }

    @Override
    public SeekableByteChannel truncate(long size) throws IOException {
        return new ByteArrayChannel(bytes, position, (int) size);
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void close() throws IOException {
        bytes = null;
    }
}
