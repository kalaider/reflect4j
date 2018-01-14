# reflect4j

[![Build Status](https://travis-ci.org/kalaider/reflect4j.svg?branch=master)](https://travis-ci.org/kalaider/reflect4j) [![codecov](https://codecov.io/gh/kalaider/reflect4j/branch/master/graph/badge.svg)](https://codecov.io/gh/kalaider/reflect4j)

A set of utility methods in addition to the Java Reflection API.

## Overview

### `isOverridden`

The package provides a way to check if one method was overridden by another.

```java
class ClassA<T> {
    void test(T arg1, List<? extends T> arg2) {  }
}

class ClassB<T>
    extends ClassA<T> {
    @Override void test(T arg1, List<? extends T> arg2) {  }
}

isOverridden(ClassA.class.getDeclaredMethod("test", Object.class, List.class),
             ClassB.class.getDeclaredMethod("test", Object.class, List.class)));

// output

/*
    true
*/
```

### `resolveParameters`

Resolves the actual parameter types of a generic class against its superclass, e.i. maps generic parameters of superclass to generic parameters of subclass.

```java
class TestClass1<K, T extends List<K>> {  }

class TestClass2<P>
    extends TestClass1<P, List<P>> {  }

class TestClass3
    extends TestClass2<Number> {  }

System.out.println(
    resolveParameters(TestClass1.class, TestClass2.class));
System.out.println(
    resolveParameters(TestClass1.class, TestClass3.class));
System.out.println(
    resolveParameters(TestClass2.class, TestClass3.class));

// output

/*
    {T=java.util.List<P>, K=P}
    {T=java.util.List<P>, P=class java.lang.Number, K=class java.lang.Number}
    {P=class java.lang.Number}
*/
```
