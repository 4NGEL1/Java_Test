package com.axity.office.commons.aspectj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to intercept controller methods that return a JSON object
 * 
 * @author username@axity.com
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface JsonResponseInterceptor
{
}
