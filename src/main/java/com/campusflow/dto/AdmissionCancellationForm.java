package com.campusflow.dto;

import com.campusflow.model.CancellationReason;
import jakarta.validation.constraints.NotNull;

public class AdmissionCancellationForm {
    @NotNull(message = "Choose a cancellation reason.")
    private CancellationReason reason;

    public CancellationReason getReason() { return reason; }
    public void setReason(CancellationReason reason) { this.reason = reason; }
}
