package com.sms.common;

import java.lang.annotation.*;

/**
 * Created by moon
 * on 2014/12/3.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param {

    String value() default "";
}
