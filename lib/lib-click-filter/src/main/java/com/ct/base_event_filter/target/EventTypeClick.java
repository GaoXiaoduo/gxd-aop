package com.ct.base_event_filter.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 事件类型注解
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 4/16/21 11:05 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventTypeClick {
    int eventType () default 0;
}