package com.bertramlabs.plugins

import java.lang.annotation.ElementType
import java.lang.annotation.Target
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Retention


@Retention(RetentionPolicy.RUNTIME)
public @interface SSL {
	String forced() default "true"
}
