package com.binus.thesis.fisheryapp.transform;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ConvertTransform {
}
