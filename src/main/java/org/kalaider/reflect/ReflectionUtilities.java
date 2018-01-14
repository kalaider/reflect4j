/*
 * Copyright (C) 2014 Alexander Vasilevsky
 *
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kalaider.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains some utility methods for the Java Reflection API.
 *
 * @author Alexander Vasilevsky
 */
public class ReflectionUtilities {

    /**
     * The private constructor.
     */
    private ReflectionUtilities() {
    }

    /**
     * Determines the <code>methodA</code> is overridden by the
     * <code>methodB</code>.
     *
     * <p/>
     * Supports generics.
     *
     * @param methodA The first method
     * @param methodB The second method
     *
     * @return <code>true</code> if one method is overridden by another,
     *         <code>false</code> otherwise
     *
     * @throws NullPointerException                If any argument is
     *                                             <code>null</code>
     * @throws GenericSignatureFormatError         If the generic signature
     *         of this generic declaration does not conform to the format
     *         specified in the Java Virtual Machine Specification, 3rd edition
     * @throws TypeNotPresentException             If the generic superclass
     *         or any of its actual type arguments refers to a non-existent
     *         type declaration
     * @throws MalformedParameterizedTypeException if the generic superclass
     *         or any of its actual type parameters refers to a parameterized
     *         type that cannot be instantiated for any reason
     */
    public static boolean isOverridden(Method methodA, Method methodB) {
        Class<?> declaringA = methodA.getDeclaringClass();
        Class<?> declaringB = methodB.getDeclaringClass();

        if (!declaringA.isAssignableFrom(declaringB)) return false;
        if (!methodB.getName().equals(methodA.getName())) return false;

        Map<Type, Type> parameters = resolveParameters(declaringA, declaringB);
        Type[] typesA = methodA.getGenericParameterTypes();
        Type[] typesB = methodB.getGenericParameterTypes();

        if (typesA.length != typesB.length) return false;

        return compare(typesA, typesB, parameters);
    }

    /**
     * Compares two arrays of types in context of generic parameters
     * by calling the {@link #compare(java.lang.reflect.Type,
     * java.lang.reflect.Type, java.util.Map) compare()}
     * method for each pair (typeA[i]; typeB[i]).
     *
     * @param typesA  The first array of types
     * @param typesB  The second array of types
     * @param context The context of generic parameter
     *
     * @return <code>true</code> two arrays are equivalent in the passed
     *         context of generic parameters, <code>false</code> otherwise
     *
     * @throws NullPointerException           If one of the arguments
     *                                        is <code>null</code>
     * @throws ArrayIndexOutOfBoundsException If arrays differ in length
     * @throws GenericSignatureFormatError         If the generic signature
     *         of this generic declaration does not conform to the format
     *         specified in the Java Virtual Machine Specification, 3rd edition
     * @throws TypeNotPresentException             If the generic superclass
     *         or any of its actual type arguments refers to a non-existent
     *         type declaration
     * @throws MalformedParameterizedTypeException if the generic superclass
     *         or any of its actual type parameters refers to a parameterized
     *         type that cannot be instantiated for any reason
     */
    private static boolean compare(Type[] typesA, Type[] typesB, Map<Type, Type> context) {
        for (int i = 0; i < typesA.length; i++) {
            if (!compare(typesA[i], typesB[i], context)) return false;
        }
        return true;
    }

    /**
     * Compares two types in the passed context of generic parameters.
     * Checks if the first type is assignable from another.
     *
     * <p/>
     * If both types are <code>null</code>, returns <code>true</code>.
     * If if one of the types is <code>null</code>, returns <code>false</code>.
     *
     * <p/>
     * Otherwise, performs recursive comparison:
     *
     * <ul>
     *     <li>If context of generic parameters contains the
     *         first type, its opponent from context map
     *         will be recursively compared with the other type</li>
     *     <li>If both types are of {@link Class} type,
     *         they will be tested on equality</li>
     *     <li>If both types are of {@link ParameterizedType} its
     *         raw types will be tested for equality</li>
     *     <li>If both types are of {@link GenericArrayType} its
     *         component types will be recursively compared</li>
     *     <li>If both types are of {@link TypeVariable} type,
     *         its bounds will be compared without order
     *         by using the {@link #compareWithoutOrder(
     *         java.lang.reflect.Type[], java.lang.reflect.Type[],
     *         java.util.Map) compareWithoutOrder()} method</li>
     *     <li>In other cases, <code>false</code> will be returned</li>
     * </ul>
     *
     * @param typeA   The first type
     * @param typeB   The second type
     * @param context The context of generic parameters
     *
     * @return <code>true</code> if types are equivalent in the passed context
     *         of generic parameters, <code>false</code> otherwise
     *
     * @throws NullPointerException                If any argument is
     *                                             <code>null</code>
     * @throws GenericSignatureFormatError         If the generic signature
     *         of this generic declaration does not conform to the format
     *         specified in the Java Virtual Machine Specification, 3rd edition
     * @throws TypeNotPresentException             If the generic superclass
     *         or any of its actual type arguments refers to a non-existent
     *         type declaration
     * @throws MalformedParameterizedTypeException if the generic superclass
     *         or any of its actual type parameters refers to a parameterized
     *         type that cannot be instantiated for any reason
     */
    private static boolean compare(Type typeA, Type typeB, Map<Type, Type> context) {
        Type type = context.get(typeA);
        if (typeB.equals(type)) return true;
        if (type != null) return compare(type, typeB, context);
        if (typeA instanceof Class && typeB instanceof Class)
            return ((Class<?>) typeA).equals((Class<?>) typeB);
        if (typeA instanceof ParameterizedType
                && typeB instanceof ParameterizedType) {
            if (!((ParameterizedType) typeA).getRawType()
                    .equals(((ParameterizedType) typeB).getRawType()))
                return false;
            else return true;
        }
        if (typeA instanceof GenericArrayType && typeB instanceof GenericArrayType)
            return compare(((GenericArrayType) typeA).getGenericComponentType(),
                    ((GenericArrayType) typeB).getGenericComponentType(), context);
        if (typeA instanceof TypeVariable && typeB instanceof TypeVariable)
            return compareWithoutOrder(((TypeVariable) typeA).getBounds(),
                    ((TypeVariable) typeB).getBounds(), context);
        // No checking for WildcardType. It may be located only in parameterized
        // types, but they are compared by its rawtypes only.
        return false;
    }

    /**
     * Tests the passed arrays on equality in spite of element order.
     * Uses {@link #compare(java.lang.reflect.Type, java.lang.reflect.Type, java.util.Map)
     * compare()} method to perform comparison.
     *
     * @param typeA   The first type
     * @param typeB   The second type
     * @param context The context of generic parameters
     *
     * @return <code>true</code> if the passed arrays are equivalent
     *         in the passed context of generic parameters,
     *         <code>false</code> otherwise
     *
     * @throws NullPointerException                If any argument is
     *                                             <code>null</code>
     * @throws GenericSignatureFormatError         If the generic signature
     *         of this generic declaration does not conform to the format
     *         specified in the Java Virtual Machine Specification, 3rd edition
     * @throws TypeNotPresentException             If the generic superclass
     *         or any of its actual type arguments refers to a non-existent
     *         type declaration
     * @throws MalformedParameterizedTypeException if the generic superclass
     *         or any of its actual type parameters refers to a parameterized
     *         type that cannot be instantiated for any reason
     */
    private static boolean compareWithoutOrder(Type typesA[], Type[] typesB, Map<Type, Type> context) {
        if (typesA.length != typesB.length) return false;
        for (Type type1 : typesA) {
            boolean found = false;
            for (Type type2 : typesB) {
                if (compare(type1, type2, context)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    /**
     * Resolves generic type parameters of the <code>classA</code>
     * in context of <code>classC</code>.
     *
     * <p/>
     * The method resolves only the simple type variables, it
     * does nothing with complex parameterized types, which should be
     * expanded manually using the context, returned by this method.
     *
     * <p/>
     * The method starts parameter resolution from the direct
     * superclass of <code>classC</code>, using the <code>classC</code>
     * definition to recognize the actual parameters of its superclass;
     * simultaneously storing already resolved parameters of the superclass
     * in the context.
     *
     * <p/>
     * Then, it does the same with superclass of superclass of
     * <code>classC</code>, taking already resolved parameters
     * from the context and storing newly resolved parameters to it.
     *
     * <p/>
     * Thus, the map will contain all of the defined type parameters
     * of class hierarchy (not including the <code>typeC</code> itself).
     *
     * <p/>
     * The map returned may be freely modified -- it is not backed by this
     * method/class.
     *
     * <p/>
     * Example.
     *
     * <blockquote><code><pre>
     * public class ReflectionUtilitiesTest {
     *     static class TestClass1 &lt;K, T extends List&lt;K&gt;&gt; {  }
     *
     *     static class TestClass2 &lt;K&gt;
     *         extends TestClass1&lt;K, List&lt;K&gt;&gt; {  }
     *
     *     static class TestClass3
     *         extends TestClass2&lt;Number&gt; {  }
     *
     *     public static void main(String[] args) {
     *         System.out.println(
     *             resolveParameters(TestClass1.class, TestClass2.class));
     *         System.out.println(
     *             resolveParameters(TestClass1.class, TestClass3.class));
     *         System.out.println(
     *             resolveParameters(TestClass2.class, TestClass3.class));
     *     }
     * }
     * </pre></code></blockquote>
     *
     * The output is:
     * <blockquote><code><pre>
     * {T=java.util.List&lt;P&gt;, K=P}
     * {T=java.util.List&lt;P&gt;, P=class java.lang.Number, K=class java.lang.Number}
     * {P=class java.lang.Number}
     * </pre></code></blockquote>
     *
     * @param classA The upper class in the hierarchy class
     * @param classC The lower class in the hierarchy class
     *
     * @return A map that represents context of generic parameters
     *
     * @throws NullPointerException                If any argument is
     *                                             <code>null</code>
     * @throws IllegalArgumentException            If the second class passed
     *         is not a subclass of the first one
     * @throws GenericSignatureFormatError         If the generic signature
     *         of this generic declaration does not conform to the format
     *         specified in the Java Virtual Machine Specification, 3rd edition
     * @throws TypeNotPresentException             If the generic superclass
     *         or any of its actual type arguments refers to a non-existent
     *         type declaration
     * @throws MalformedParameterizedTypeException if the generic superclass
     *         or any of its actual type parameters refers to a parameterized
     *         type that cannot be instantiated for any reason
     */
    public static Map<Type, Type> resolveParameters(Class<?> classA, Class<?> classC) {
        if (!classA.isAssignableFrom(classC))
            throw new IllegalArgumentException(
                    "The second class is not a subclass of the first.");
        return resolveParameters0(classA, classC);
    }

    /**
     * Resolves generic type parameters of the <code>classA</code>
     * in context of <code>classC</code>.
     *
     * <p/>
     * The method resolves only the simple type variables, it
     * does nothing with complex parameterized types, which should be
     * expanded manually using the context, returned by this method.
     *
     * <p/>
     * The method starts parameter resolution from the direct
     * superclass of <code>classC</code>, using the <code>classC</code>
     * definition to recognize the actual parameters of its superclass;
     * simultaneously storing already resolved parameters of the superclass
     * in the context.
     *
     * <p/>
     * Then, it does the same with superclass of superclass of
     * <code>classC</code>, taking already resolved parameters
     * from the context and storing newly resolved parameters to it.
     *
     * <p/>
     * Thus, the map will contain all of the defined type parameters
     * of class hierarchy (not including the <code>typeC</code> itself).
     *
     * <p/>
     * The map returned may be freely modified -- it is not backed by this
     * method/class.
     *
     * <p/>
     * Example.
     *
     * <blockquote><code><pre>
     * public class ReflectionUtilitiesTest {
     *     static class TestClass1 &lt;K, T extends List&lt;K&gt;&gt; {  }
     *
     *     static class TestClass2 &lt;K&gt;
     *         extends TestClass1&lt;K, List&lt;K&gt;&gt; {  }
     *
     *     static class TestClass3
     *         extends TestClass2&lt;Number&gt; {  }
     *
     *     public static void main(String[] args) {
     *         System.out.println(
     *             resolveParameters0(TestClass1.class, TestClass2.class));
     *         System.out.println(
     *             resolveParameters0(TestClass1.class, TestClass3.class));
     *         System.out.println(
     *             resolveParameters0(TestClass2.class, TestClass3.class));
     *     }
     * }
     * </pre></code></blockquote>
     *
     * The output is:
     * <blockquote><code><pre>
     * {T=java.util.List&lt;P&gt;, K=P}
     * {T=java.util.List&lt;P&gt;, P=class java.lang.Number, K=class java.lang.Number}
     * {P=class java.lang.Number}
     * </pre></code></blockquote>
     *
     * @param classA The upper class in the hierarchy class
     * @param classC The lower class in the hierarchy class
     *
     * @return A map that represents context of generic parameters
     *
     * @throws NullPointerException                If any argument is
     *                                             <code>null</code>
     * @throws GenericSignatureFormatError         If the generic signature
     *         of this generic declaration does not conform to the format
     *         specified in the Java Virtual Machine Specification, 3rd edition
     * @throws TypeNotPresentException             If the generic superclass
     *         or any of its actual type arguments refers to a non-existent
     *         type declaration
     * @throws MalformedParameterizedTypeException if the generic superclass
     *         or any of its actual type parameters refers to a parameterized
     *         type that cannot be instantiated for any reason
     */
    private static Map<Type, Type> resolveParameters0(Class<?> classA, Class<?> classC) {
        Map<Type, Type> map = new HashMap<Type, Type>(3);
        Class<?> classB = classC;
        while (!classA.equals(classB)) {
            TypeVariable[] theoretical = classB.getSuperclass().getTypeParameters();
            Type gs = classB.getGenericSuperclass();
            classB = classB.getSuperclass();
            if (!(gs instanceof ParameterizedType)) continue;
            Type[] actual = ((ParameterizedType) gs).getActualTypeArguments();
            for (int i = 0; i < theoretical.length; i++) {
                Type processed = map.get(actual[i]);
                map.put(theoretical[i], processed != null ? processed : actual[i]);
            }
        }
        return map;
    }
}
