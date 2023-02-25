package com.binus.thesis.fisheryapp.base.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Muhammad Hariz Faza <hariz.faza@asyst.co.id>
 * @version $Revision$, Sep 24, 2019
 * @since 2.1.0
 */
public class CustomDateOnlySerializer extends StdSerializer<Date> {
    private static final long serialVersionUID = 1L;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // specify your specific timezone
    private static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");

    public CustomDateOnlySerializer() {
        this(null);
    }

    public CustomDateOnlySerializer(Class<Date> vc) {
        super(vc);
    }

    @Override
    public void serialize(Date date, JsonGenerator jgen, SerializerProvider prov) throws IOException {
        if (date != null) {
            jgen.writeString(dateFormater.format(date));
        }
    }
}
