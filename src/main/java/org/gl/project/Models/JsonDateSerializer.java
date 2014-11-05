package org.gl.project.Models;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date>{
     
    private SimpleDateFormat dateFormat = null;
   
    public JsonDateSerializer(){
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
 
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {

    String formattedDate = dateFormat.format(date);
    gen.writeString(formattedDate);
    }
}