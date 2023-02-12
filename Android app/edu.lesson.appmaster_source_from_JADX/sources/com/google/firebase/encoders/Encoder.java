package com.google.firebase.encoders;

import java.io.IOException;

interface Encoder<TValue, TContext> {
    void encode(TValue tvalue, TContext tcontext) throws IOException;
}
