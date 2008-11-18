package com.googlecode.jsendnsca.sender;

import static junit.framework.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class MessagePayloadTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnEmptyServiceName() throws Exception {
        final MessagePayload payload = new MessagePayload();

        payload.setHostname("localhost");
        payload.setLevel(MessagePayload.LEVEL_CRITICAL);
        payload.setServiceName(StringUtils.EMPTY);
    }
    
    @Test
    public void shouldConstructValidObjectWhenUsingNoArgConstructor() throws Exception {
        final MessagePayload messagePayload = new MessagePayload();

        assertEquals("localhost", messagePayload.getHostname());
        assertEquals(MessagePayload.LEVEL_UNKNOWN, messagePayload.getLevel());
        assertEquals("UNDEFINED", messagePayload.getServiceName());
        assertEquals(StringUtils.EMPTY, messagePayload.getMessage());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionOnEmptyHostName() throws Exception {
        final MessagePayload payload = new MessagePayload();

        payload.setHostname(StringUtils.EMPTY);
    }
    
    @Test
    public void shouldAllowEmptyMessageToBeSet() throws Exception {
        final MessagePayload payload = new MessagePayload();

        payload.setMessage(StringUtils.EMPTY);
    }
    
    @Test
    public void shouldConstructNewMessagePayload() throws Exception {
        final MessagePayload messagePayload = new MessagePayload("localhost",0,"test service","test message");
        
        assertEquals("localhost", messagePayload.getHostname());
        assertEquals(MessagePayload.LEVEL_OK, messagePayload.getLevel());
        assertEquals("test service", messagePayload.getServiceName());
        assertEquals("test message", messagePayload.getMessage());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowExceptionOnConstructingNewMessagePayloadWithNullHostname() throws Exception {
        new MessagePayload(null,1,"test service","test message");
    }
    
    @Test
    public void shouldConvertStringLevelsToInteger() throws Exception {
        final MessagePayload messagePayload = new MessagePayload();
        
        messagePayload.setLevel("OK");
        assertEquals(MessagePayload.LEVEL_OK, messagePayload.getLevel());
        messagePayload.setLevel("WARNING");
        assertEquals(MessagePayload.LEVEL_WARNING, messagePayload.getLevel());
        messagePayload.setLevel("CRITICAL");
        assertEquals(MessagePayload.LEVEL_CRITICAL, messagePayload.getLevel());
        messagePayload.setLevel("UNKNOWN");
        assertEquals(MessagePayload.LEVEL_UNKNOWN, messagePayload.getLevel());
    }
}
