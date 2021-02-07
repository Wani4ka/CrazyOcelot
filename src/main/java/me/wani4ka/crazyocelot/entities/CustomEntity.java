package me.wani4ka.crazyocelot.entities;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomEntity {
	String name();
	String parent();
}
