package com.axity.office.service.util;

import com.axity.office.commons.request.MessageDto;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 * Message Serializer class
 * 
 * @author username@axity.com
 */
public class MessageSerializer extends JsonSerializer<MessageDto>
{

}
