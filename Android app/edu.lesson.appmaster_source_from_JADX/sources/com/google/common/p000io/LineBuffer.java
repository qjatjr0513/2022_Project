package com.google.common.p000io;

import java.io.IOException;

/* renamed from: com.google.common.io.LineBuffer */
abstract class LineBuffer {
    private StringBuilder line = new StringBuilder();
    private boolean sawReturn;

    /* access modifiers changed from: protected */
    public abstract void handleLine(String str, String str2) throws IOException;

    LineBuffer() {
    }

    /* access modifiers changed from: protected */
    public void add(char[] cbuf, int off, int len) throws IOException {
        int pos = off;
        if (this.sawReturn && len > 0) {
            if (finishLine(cbuf[pos] == 10)) {
                pos++;
            }
        }
        int start = pos;
        int end = off + len;
        while (pos < end) {
            switch (cbuf[pos]) {
                case 10:
                    this.line.append(cbuf, start, pos - start);
                    finishLine(true);
                    start = pos + 1;
                    break;
                case 13:
                    this.line.append(cbuf, start, pos - start);
                    this.sawReturn = true;
                    if (pos + 1 < end) {
                        if (finishLine(cbuf[pos + 1] == 10)) {
                            pos++;
                        }
                    }
                    start = pos + 1;
                    break;
            }
            pos++;
        }
        this.line.append(cbuf, start, (off + len) - start);
    }

    private boolean finishLine(boolean sawNewline) throws IOException {
        handleLine(this.line.toString(), this.sawReturn ? sawNewline ? "\r\n" : "\r" : sawNewline ? "\n" : "");
        this.line = new StringBuilder();
        this.sawReturn = false;
        return sawNewline;
    }

    /* access modifiers changed from: protected */
    public void finish() throws IOException {
        if (this.sawReturn || this.line.length() > 0) {
            finishLine(false);
        }
    }
}
