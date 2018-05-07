package com.yezhihun.demo.util;

/**
 * Created by tianye on 2018/5/7.
 */

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();
}
