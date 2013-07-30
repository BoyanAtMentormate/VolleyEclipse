package com.example.volleysimplesample.util;

import java.util.Locale;

public class RegexUtil {
    private static final String regex = "[#.:/]{1,64}";
    
	public static String sanitizeKey(String key) {
		key = key.replaceAll(regex, "_");
		key = key.substring(0, 63);
		return key.toLowerCase(Locale.getDefault());
	}
}
