package com.google.common.p000io;

import com.google.common.base.Preconditions;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: com.google.common.io.CharStreams */
public final class CharStreams {
    private static final int DEFAULT_BUF_SIZE = 2048;

    static CharBuffer createBuffer() {
        return CharBuffer.allocate(2048);
    }

    private CharStreams() {
    }

    public static long copy(Readable from, Appendable to) throws IOException {
        if (!(from instanceof Reader)) {
            Preconditions.checkNotNull(from);
            Preconditions.checkNotNull(to);
            long total = 0;
            CharBuffer buf = createBuffer();
            while (from.read(buf) != -1) {
                Java8Compatibility.flip(buf);
                to.append(buf);
                total += (long) buf.remaining();
                Java8Compatibility.clear(buf);
            }
            return total;
        } else if (to instanceof StringBuilder) {
            return copyReaderToBuilder((Reader) from, (StringBuilder) to);
        } else {
            return copyReaderToWriter((Reader) from, asWriter(to));
        }
    }

    static long copyReaderToBuilder(Reader from, StringBuilder to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        char[] buf = new char[2048];
        long total = 0;
        while (true) {
            int read = from.read(buf);
            int nRead = read;
            if (read == -1) {
                return total;
            }
            to.append(buf, 0, nRead);
            total += (long) nRead;
        }
    }

    static long copyReaderToWriter(Reader from, Writer to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        char[] buf = new char[2048];
        long total = 0;
        while (true) {
            int read = from.read(buf);
            int nRead = read;
            if (read == -1) {
                return total;
            }
            to.write(buf, 0, nRead);
            total += (long) nRead;
        }
    }

    public static String toString(Readable r) throws IOException {
        return toStringBuilder(r).toString();
    }

    private static StringBuilder toStringBuilder(Readable r) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (r instanceof Reader) {
            copyReaderToBuilder((Reader) r, sb);
        } else {
            copy(r, sb);
        }
        return sb;
    }

    public static List<String> readLines(Readable r) throws IOException {
        List<String> result = new ArrayList<>();
        LineReader lineReader = new LineReader(r);
        while (true) {
            String readLine = lineReader.readLine();
            String line = readLine;
            if (readLine == null) {
                return result;
            }
            result.add(line);
        }
    }

    public static <T> T readLines(Readable readable, LineProcessor<T> processor) throws IOException {
        String line;
        Preconditions.checkNotNull(readable);
        Preconditions.checkNotNull(processor);
        LineReader lineReader = new LineReader(readable);
        do {
            String readLine = lineReader.readLine();
            line = readLine;
            if (readLine == null || !processor.processLine(line)) {
            }
            String readLine2 = lineReader.readLine();
            line = readLine2;
            break;
        } while (!processor.processLine(line));
        return processor.getResult();
    }

    public static long exhaust(Readable readable) throws IOException {
        long total = 0;
        CharBuffer buf = createBuffer();
        while (true) {
            long read = (long) readable.read(buf);
            long read2 = read;
            if (read == -1) {
                return total;
            }
            total += read2;
            Java8Compatibility.clear(buf);
        }
    }

    public static void skipFully(Reader reader, long n) throws IOException {
        Preconditions.checkNotNull(reader);
        while (n > 0) {
            long amt = reader.skip(n);
            if (amt != 0) {
                n -= amt;
            } else {
                throw new EOFException();
            }
        }
    }

    public static Writer nullWriter() {
        return NullWriter.INSTANCE;
    }

    /* renamed from: com.google.common.io.CharStreams$NullWriter */
    private static final class NullWriter extends Writer {
        /* access modifiers changed from: private */
        public static final NullWriter INSTANCE = new NullWriter();

        private NullWriter() {
        }

        public void write(int c) {
        }

        public void write(char[] cbuf) {
            Preconditions.checkNotNull(cbuf);
        }

        public void write(char[] cbuf, int off, int len) {
            Preconditions.checkPositionIndexes(off, off + len, cbuf.length);
        }

        public void write(String str) {
            Preconditions.checkNotNull(str);
        }

        public void write(String str, int off, int len) {
            Preconditions.checkPositionIndexes(off, off + len, str.length());
        }

        public Writer append(@NullableDecl CharSequence csq) {
            return this;
        }

        public Writer append(@NullableDecl CharSequence csq, int start, int end) {
            Preconditions.checkPositionIndexes(start, end, csq == null ? "null".length() : csq.length());
            return this;
        }

        public Writer append(char c) {
            return this;
        }

        public void flush() {
        }

        public void close() {
        }

        public String toString() {
            return "CharStreams.nullWriter()";
        }
    }

    public static Writer asWriter(Appendable target) {
        if (target instanceof Writer) {
            return (Writer) target;
        }
        return new AppendableWriter(target);
    }
}
