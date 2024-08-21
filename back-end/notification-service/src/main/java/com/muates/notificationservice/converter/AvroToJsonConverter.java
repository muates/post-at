package com.muates.notificationservice.converter;

import lombok.RequiredArgsConstructor;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Component
@RequiredArgsConstructor
public class AvroToJsonConverter implements MessageConverter {

    @Override
    public Object fromMessage(Message<?> message, Class<?> targetClass) {
        throw new UnsupportedOperationException("Conversion from Message to Avro not supported");
    }

    @Override
    public Message<?> toMessage(Object payload, MessageHeaders headers) {
        if (payload instanceof GenericRecord) {
            GenericRecord avroRecord = (GenericRecord) payload;

            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                JsonEncoder jsonEncoder = EncoderFactory.get().jsonEncoder(avroRecord.getSchema(), outputStream);
                GenericDatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(avroRecord.getSchema());
                datumWriter.write(avroRecord, jsonEncoder);
                jsonEncoder.flush();
                outputStream.flush();

                String jsonString = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
                return MessageBuilder.withPayload(jsonString).copyHeaders(headers).build();
            } catch (IOException e) {
                throw new RuntimeException("Error converting Avro to JSON", e);
            }
        } else {
            throw new IllegalArgumentException("Payload is not an instance of GenericRecord");
        }
    }
}
