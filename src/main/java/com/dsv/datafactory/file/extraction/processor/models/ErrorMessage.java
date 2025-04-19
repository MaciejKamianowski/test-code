package com.dsv.datafactory.file.extraction.processor.models;

import com.dsv.datafactory.model.MetaData;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.io.Serializable;

// consider using @Data instead of @Getter, @Setter, and @RequiredArgsConstructor

@RequiredArgsConstructor
public class ErrorMessage implements Serializable
{   // opening curly bracket should be on the same line

    // Add serialVersionUID

    //  explicitly declare serialVersionUID when implementing Serializable to avoid issues during deserialization.
    // private static final long serialVersionUID = 1L;
    @Getter @Setter @NonNull private String topicKey;
    // mixing field-level and class-level annotations
    // the @Getter and @Setter

    @Getter @Setter @NonNull private MetaData topicMessage;
    @Getter @Setter @NonNull private Throwable exception;
}

// we can write it easier
/*@Getter
@Setter
@RequiredArgsConstructor
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @NonNull
    private String topicKey;

    @NonNull
    private MetaData topicMessage;

    @NonNull
    private Throwable exception;
}*/

// or we can make it immutable via @Value or records if you are using Java 17+

/*
@Value
public class ErrorMessage implements Serializable {
    String topicKey;
    MetaData topicMessage;
    Throwable exception;
}

public record ErrorMessage(
        String topicKey,
        MetaData topicMessage,
        Throwable exception
) implements Serializable {}*/
