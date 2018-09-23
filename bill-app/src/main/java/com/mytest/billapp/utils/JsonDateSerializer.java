package com.mytest.billapp.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonDateSerializer extends JsonSerializer<Date>{
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		String formattedDate = StringUtils.EMPTY;
		if(date != null) {
			formattedDate = dateFormat.format(date);
		}
		gen.writeString(formattedDate);
	}
}
