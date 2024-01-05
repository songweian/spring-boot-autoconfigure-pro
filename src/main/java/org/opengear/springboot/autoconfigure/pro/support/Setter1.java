package org.opengear.springboot.autoconfigure.pro.support;

@FunctionalInterface
public interface Setter1<R, V> {
    R set(V value);
}
