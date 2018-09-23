package com.mytest.billapp.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mytest.billapp.model.JewelPurchaseDetails;

public class JsonJewelPurchaseDetailsSerializer extends StdSerializer<List<JewelPurchaseDetails>> {
	public JsonJewelPurchaseDetailsSerializer() {
        this(null);
    }
 
    public JsonJewelPurchaseDetailsSerializer(Class<List<JewelPurchaseDetails>> t) {
        super(t);
    }
 
    @Override
    public void serialize(List<JewelPurchaseDetails> items, JsonGenerator generator, SerializerProvider provider)  throws IOException, JsonProcessingException {
    	List<JewelPurchaseDetails> JewelPurchaseDetailsList = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        for (JewelPurchaseDetails item : items) {
        	JewelPurchaseDetails details = new JewelPurchaseDetails();
        	BeanUtils.copyProperties(item, details);
        	JewelPurchaseDetailsList.add(details);
            ids.add(item.getId());
        }
        generator.writeObject(JewelPurchaseDetailsList);
    }
}
