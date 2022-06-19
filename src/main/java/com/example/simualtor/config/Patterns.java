package com.example.simualtor.config;

import java.util.regex.Pattern;

public class Patterns {

    public static final Pattern OID_VALUE = Pattern.compile("E:[0-9+.]*=(\"[^\"]*\"|[0-9]*)");
    public static final Pattern TIME = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}) (\\d{2}:\\d{2}:\\d{2})");

}
