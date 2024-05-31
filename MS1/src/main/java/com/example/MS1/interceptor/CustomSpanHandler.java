//package com.example.MS1.interceptor;
//import org.springframework.cloud.sleuth.Span;
//import org.springframework.cloud.sleuth.exporter.SpanHandler;
//import org.springframework.stereotype.Component;
//
//import java.awt.event.FocusEvent;
//
//@Component
//public class CustomSpanHandler extends SpanHandler {
//
//    @Override
//    public boolean end(Span span, FocusEvent.Cause cause) {
//        // Gestisci la traccia qui
//        System.out.println("Trace ID: " + span.context().traceId());
//        System.out.println("Span ID: " + span.context().spanId());
//        return true; // Return true to allow other handlers to process the span
//    }
//}