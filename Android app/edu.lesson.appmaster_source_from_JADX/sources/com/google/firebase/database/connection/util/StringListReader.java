package com.google.firebase.database.connection.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

public class StringListReader extends Reader {
    private int charPos;
    private boolean closed;
    private boolean frozen;
    private int markedCharPos;
    private int markedStringListPos;
    private int stringListPos;
    private List<String> strings;

    public StringListReader() {
        this.strings = null;
        this.closed = false;
        this.markedCharPos = this.charPos;
        this.markedStringListPos = this.stringListPos;
        this.frozen = false;
        this.strings = new ArrayList();
    }

    public void addString(String string) {
        if (this.frozen) {
            throw new IllegalStateException("Trying to add string after reading");
        } else if (string.length() > 0) {
            this.strings.add(string);
        }
    }

    public void freeze() {
        if (!this.frozen) {
            this.frozen = true;
            return;
        }
        throw new IllegalStateException("Trying to freeze frozen StringListReader");
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String string : this.strings) {
            builder.append(string);
        }
        return builder.toString();
    }

    public void reset() throws IOException {
        this.charPos = this.markedCharPos;
        this.stringListPos = this.markedStringListPos;
    }

    private String currentString() {
        if (this.stringListPos < this.strings.size()) {
            return this.strings.get(this.stringListPos);
        }
        return null;
    }

    private int currentStringRemainingChars() {
        String current = currentString();
        if (current == null) {
            return 0;
        }
        return current.length() - this.charPos;
    }

    private void checkState() throws IOException {
        if (this.closed) {
            throw new IOException("Stream already closed");
        } else if (!this.frozen) {
            throw new IOException("Reader needs to be frozen before read operations can be called");
        }
    }

    private long advance(long numChars) {
        long advanced = 0;
        while (this.stringListPos < this.strings.size() && advanced < numChars) {
            int remainingStringChars = currentStringRemainingChars();
            long remainingChars = numChars - advanced;
            if (remainingChars < ((long) remainingStringChars)) {
                this.charPos = (int) (((long) this.charPos) + remainingChars);
                advanced += remainingChars;
            } else {
                advanced += (long) remainingStringChars;
                this.charPos = 0;
                this.stringListPos++;
            }
        }
        return advanced;
    }

    public int read(CharBuffer target) throws IOException {
        checkState();
        int remaining = target.remaining();
        int total = 0;
        String current = currentString();
        while (remaining > 0 && current != null) {
            int strLength = Math.min(current.length() - this.charPos, remaining);
            int i = this.charPos;
            target.put(this.strings.get(this.stringListPos), i, i + strLength);
            remaining -= strLength;
            total += strLength;
            advance((long) strLength);
            current = currentString();
        }
        if (total > 0 || current != null) {
            return total;
        }
        return -1;
    }

    public int read() throws IOException {
        checkState();
        String current = currentString();
        if (current == null) {
            return -1;
        }
        char c = current.charAt(this.charPos);
        advance(1);
        return c;
    }

    public long skip(long n) throws IOException {
        checkState();
        return advance(n);
    }

    public boolean ready() throws IOException {
        checkState();
        return true;
    }

    public boolean markSupported() {
        return true;
    }

    public void mark(int readAheadLimit) throws IOException {
        checkState();
        this.markedCharPos = this.charPos;
        this.markedStringListPos = this.stringListPos;
    }

    public int read(char[] cbuf, int off, int len) throws IOException {
        checkState();
        int charsCopied = 0;
        String current = currentString();
        while (current != null && charsCopied < len) {
            int copyLength = Math.min(currentStringRemainingChars(), len - charsCopied);
            int i = this.charPos;
            current.getChars(i, i + copyLength, cbuf, off + charsCopied);
            charsCopied += copyLength;
            advance((long) copyLength);
            current = currentString();
        }
        if (charsCopied > 0 || current != null) {
            return charsCopied;
        }
        return -1;
    }

    public void close() throws IOException {
        checkState();
        this.closed = true;
    }
}
