package com.java.truth.softwares.rightshipsoftfunctions.events;

import java.time.Instant;

public record PaymentCompletedEvent(
        String eventId,
        Instant occurredAt,
        Long paymentId,
        Long orderId,
        String status,
        String providerRef
) {}
