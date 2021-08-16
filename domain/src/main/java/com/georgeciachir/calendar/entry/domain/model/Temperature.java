package com.georgeciachir.calendar.entry.domain.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Temperature {

    int max;
    int min;
}
