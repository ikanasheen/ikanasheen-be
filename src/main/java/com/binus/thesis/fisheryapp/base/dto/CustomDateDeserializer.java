package com.binus.thesis.fisheryapp.base.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ASYST
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	private static Logger log = LoggerFactory.getLogger(CustomDateDeserializer.class);
	
	@Override
	public Date deserialize(JsonParser param, DeserializationContext context) throws IOException, JsonProcessingException {
		String date = param.getText();
		if(!StringUtils.isEmpty(date)) {
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				try {
					return sdfDate.parse(date);
				} catch (ParseException e1) {
					log.error("Error while try to parse a date: "+date+", caused by: "+e.getLocalizedMessage(), e);
				}
			}
			Date dt = context.parseDate(date);
			return dt;
		} else {
			return null;
		}
	}

}
