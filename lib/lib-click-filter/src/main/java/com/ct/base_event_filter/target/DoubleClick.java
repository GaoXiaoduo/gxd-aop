package com.ct.base_event_filter.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 双击或者多次点击注解
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 2019-12-24 15:20
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface DoubleClick {
}
