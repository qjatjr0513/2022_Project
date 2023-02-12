package com.google.android.gms.location.places;

import com.google.android.gms.common.api.DataBufferResponse;

@Deprecated
public class AutocompletePredictionBufferResponse extends DataBufferResponse<AutocompletePrediction, AutocompletePredictionBuffer> {
    AutocompletePredictionBufferResponse() {
    }

    public String toString() {
        return ((AutocompletePredictionBuffer) getResult()).toString();
    }
}
