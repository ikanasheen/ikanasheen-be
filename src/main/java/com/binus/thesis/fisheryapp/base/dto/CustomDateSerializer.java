/*
 * $Id$
 * 
 * Copyright (c) 2018 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package com.binus.thesis.fisheryapp.base.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sanjaya Rusli
 * @version $Revision$, Aug 10, 2018
 * @since 1.0
 */
public class CustomDateSerializer extends StdSerializer<Date>{
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // specify your specific timezone
	private static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
	
	public CustomDateSerializer() {
		this(null);
	}

	public CustomDateSerializer(Class<Date> vc) {
		super(vc);
	}

	@Override
	public void serialize(Date date, JsonGenerator jgen, SerializerProvider prov) throws IOException {
		if(date != null) {
			if(date instanceof java.sql.Date) {
				jgen.writeString(dateFormater.format(date));
			} else {
				jgen.writeString(formatter.format(date));
			}
		}
	}
}
