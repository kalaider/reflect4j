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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.kalaider.reflect.ReflectionUtilities.*;

/**
 *
 * @author Alexander Vasilevsky
 */
public class ReflectionUtilitiesTest {

    public ReflectionUtilitiesTest() {
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 1</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case1() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass1.ClassA.class.getDeclaredMethod("a", Object.class, List.class),
                TestClass1.ClassB.class.getDeclaredMethod("a", Object.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 2</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case2() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass1.ClassA.class.getDeclaredMethod("a", Object.class, List.class),
                TestClass1.ClassC.class.getDeclaredMethod("a", C2.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 3</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case3() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass1.ClassA.class.getDeclaredMethod("a", Object.class, List.class),
                TestClass1.ClassD.class.getDeclaredMethod("a", C4.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 4</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case4() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass1.ClassA.class.getDeclaredMethod("a", Object.class, List.class),
                TestClass1.ClassE.class.getDeclaredMethod("a", C6.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 5</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case5() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass1.ClassA.class.getDeclaredMethod("a", Object.class, List.class),
                TestClass1.ClassF.class.getDeclaredMethod("a", C6.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 6</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case6() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass1.ClassB.class.getDeclaredMethod("a", Object.class, List.class),
                TestClass1.ClassE.class.getDeclaredMethod("a", C6.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 7</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case7() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass1.ClassC.class.getDeclaredMethod("a", C2.class, List.class),
                TestClass1.ClassD.class.getDeclaredMethod("a", C4.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 8</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case8() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass2.ClassA.class.getDeclaredMethod("a", Object.class, Object.class),
                TestClass2.ClassB.class.getDeclaredMethod("a", C2.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 9</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case9() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass2.ClassA.class.getDeclaredMethod("a", Object.class, Object.class),
                TestClass2.ClassC.class.getDeclaredMethod("a", C2.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 10</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case10() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass2.ClassA.class.getDeclaredMethod("a", Object.class, Object.class),
                TestClass2.ClassD.class.getDeclaredMethod("a", C2.class, I3.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 11</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case11() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass2.ClassA.class.getDeclaredMethod("a", Object.class, Object.class),
                TestClass2.ClassE.class.getDeclaredMethod("a", C4.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 12</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case12() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass2.ClassA.class.getDeclaredMethod("a", Object.class, Object.class),
                TestClass2.ClassF.class.getDeclaredMethod("a", C4.class, C7.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 13</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case13() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass2.ClassB.class.getDeclaredMethod("a", C2.class, List.class),
                TestClass2.ClassE.class.getDeclaredMethod("a", C4.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 14</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case14() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass2.ClassC.class.getDeclaredMethod("a", C2.class, List.class),
                TestClass2.ClassD.class.getDeclaredMethod("a", C2.class, I3.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 15</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case15() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass4.ClassA.class.getDeclaredMethod("a", List.class),
                TestClass4.ClassB.class.getDeclaredMethod("a", I2.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 16</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case16() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass4.ClassA.class.getDeclaredMethod("a", List.class),
                TestClass4.ClassC.class.getDeclaredMethod("a", I2.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 17</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case17() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass4.ClassB.class.getDeclaredMethod("a", I2.class),
                TestClass4.ClassC.class.getDeclaredMethod("a", I2.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 18</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case18() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass3.ClassA.class.getDeclaredMethod("a", List.class),
                TestClass3.ClassB.class.getDeclaredMethod("a", I2.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 19</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case19() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass5.ClassA.class.getDeclaredMethod("a", List[].class, List.class),
                TestClass5.ClassB.class.getDeclaredMethod("a", I2[].class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 20</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case20() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass5.ClassA.class.getDeclaredMethod("a", List[].class, List.class),
                TestClass5.ClassC.class.getDeclaredMethod("a", I2[].class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 21</h2>
     *
     * <p/>
     * Returns <code>true</code> if the method was overridden.
     */
    @Test
    public void isOverridden_signature1_case21() throws NoSuchMethodException {
        assertTrue(isOverridden(
                TestClass5.ClassB.class.getDeclaredMethod("a", I2[].class, List.class),
                TestClass5.ClassC.class.getDeclaredMethod("a", I2[].class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 22</h2>
     *
     * <p/>
     * Returns <code>false</code> if the method was not overridden.
     */
    @Test
    public void isOverridden_signature1_case22() throws NoSuchMethodException {
        assertFalse(isOverridden(
                TestClass6.ClassA.class.getDeclaredMethod("a", List[].class, List.class),
                TestClass6.ClassB.class.getDeclaredMethod("a", I2[].class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 23</h2>
     *
     * <p/>
     * Returns <code>false</code> if the method was not overridden.
     */
    @Test
    public void isOverridden_signature1_case23() throws NoSuchMethodException {
        assertFalse(isOverridden(
                TestClass7.ClassA.class.getDeclaredMethod("a", List[].class, List.class),
                TestClass7.ClassB.class.getDeclaredMethod("a", Set[].class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 23</h2>
     *
     * <p/>
     * Returns <code>false</code> if the method was not overridden.
     */
    @Test
    public void isOverridden_signature1_case24() throws NoSuchMethodException {
        assertFalse(isOverridden(
                TestClass8.ClassA.class.getDeclaredMethod("a", List[].class, List.class),
                TestClass8.ClassB.class.getDeclaredMethod("a", List.class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 25</h2>
     *
     * <p/>
     * Returns <code>false</code> if the method was not overridden.
     */
    @Test
    public void isOverridden_signature1_case25() throws NoSuchMethodException {
        assertFalse(isOverridden(
                TestClass9.ClassA.class.getDeclaredMethod("a", List[].class, List.class),
                TestClass9.ClassB.class.getDeclaredMethod("a", List[].class, Set.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 26</h2>
     *
     * <p/>
     * Returns <code>false</code> if the method was not overridden.
     */
    @Test
    public void isOverridden_signature1_case26() throws NoSuchMethodException {
        assertFalse(isOverridden(
                TestClass10.ClassA.class.getDeclaredMethod("a", Object[].class, List.class),
                TestClass10.ClassB.class.getDeclaredMethod("a", I1[].class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 27</h2>
     *
     * <p/>
     * Returns <code>false</code> if the method was not overridden.
     */
    @Test
    public void isOverridden_signature1_case27() throws NoSuchMethodException {
        assertFalse(isOverridden(
                TestClass11.ClassA.class.getDeclaredMethod("a", I2[].class, List.class),
                TestClass11.ClassB.class.getDeclaredMethod("a", I3[].class, List.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 28</h2>
     *
     * <p/>
     * Returns <code>false</code> if two methods from different class
     * hierarchies.
     */
    @Test
    public void isOverridden_signature1_case28() throws NoSuchMethodException {
        assertFalse(isOverridden(
                TestClass12.ClassA.class.getDeclaredMethod("a", Object.class),
                TestClass12.ClassB.class.getDeclaredMethod("a", Object.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 29</h2>
     *
     * <p/>
     * Returns <code>false</code> if two methods has different names.
     */
    @Test
    public void isOverridden_signature1_case29() throws NoSuchMethodException {
        assertFalse(isOverridden(
                TestClass12.ClassA.class.getDeclaredMethod("a", Object.class),
                TestClass12.ClassA.class.getDeclaredMethod("b", Object.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 30</h2>
     *
     * <p/>
     * Returns <code>false</code> if two methods has different number of parameters.
     */
    @Test
    public void isOverridden_signature1_case30() throws NoSuchMethodException {
        assertFalse(isOverridden(
                TestClass13.ClassA.class.getDeclaredMethod("a", Object.class),
                TestClass13.ClassB.class.getDeclaredMethod("a", Object.class, Object.class)));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 31</h2>
     *
     * <p/>
     * Throws {@link NullPointerException} if the first argument is <code>null</code>.
     */
    @Test(expected = NullPointerException.class)
    public void isOverridden_signature1_case31() throws NoSuchMethodException {
        isOverridden(
            null,
            TestClass12.ClassB.class.getDeclaredMethod("b", Object.class, Object.class));
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#isOverridden(
     *                       java.lang.reflect.Method, java.lang.reflect.Method)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 32</h2>
     *
     * <p/>
     * Throws {@link NullPointerException} if the second argument is <code>null</code>.
     */
    @Test(expected = NullPointerException.class)
    public void isOverridden_signature1_case32() throws NoSuchMethodException {
        isOverridden(
            TestClass12.ClassB.class.getDeclaredMethod("b", Object.class, Object.class),
            null);
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#resolveParameters(
     *                       java.lang.Class, java.lang.Class)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 1</h2>
     *
     * <p/>
     * Throws {@link IllegalArgumentException} if classes are from different
     * hierarchies.
     */
    @Test(expected = IllegalArgumentException.class)
    public void resolveParameters_signature1_case1() throws NoSuchMethodException {
        resolveParameters(TestClass12.ClassA.class, TestClass12.ClassB.class);
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#resolveParameters(
     *                       java.lang.Class, java.lang.Class)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 2</h2>
     *
     * <p/>
     * Throws {@link NullPointerException} if the first class is <code>null</code>.
     */
    @Test(expected = NullPointerException.class)
    public void resolveParameters_signature1_case2() throws NoSuchMethodException {
        resolveParameters(null, TestClass12.ClassB.class);
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#resolveParameters(
     *                       java.lang.Class, java.lang.Class)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 3</h2>
     *
     * <p/>
     * Throws {@link NullPointerException} if the second class is <code>null</code>.
     */
    @Test(expected = NullPointerException.class)
    public void resolveParameters_signature1_case3() throws NoSuchMethodException {
        resolveParameters(TestClass12.ClassA.class, null);
    }

    /**
     * <b>Method:</b> {@link ReflectionUtilities#resolveParameters(
     *                       java.lang.Class, java.lang.Class)}
     *
     * <p/><!-- ------------------------------------------------------ --><hr/>
     *
     * <h2>Case 4</h2>
     *
     * <p/>
     * Returns a correct map of resolved parameters.
     */
    @Test
    public void resolveParameters_signature1_case4() throws NoSuchMethodException {
        Map<Type, Type> parameters = resolveParameters(TestClass1.ClassA.class, TestClass1.ClassF.class);
        Map<Type, Type> expected = new HashMap<Type, Type>();
        expected.put(TestClass1.ClassE.class.getTypeParameters()[0],
                ((ParameterizedType)TestClass1.ClassF.class.getGenericSuperclass()).getActualTypeArguments()[0]);
        expected.put(TestClass1.ClassD.class.getTypeParameters()[0],
                expected.get(((ParameterizedType)TestClass1.ClassE.class.getGenericSuperclass()).getActualTypeArguments()[0]));
        expected.put(TestClass1.ClassC.class.getTypeParameters()[0],
                expected.get(((ParameterizedType)TestClass1.ClassD.class.getGenericSuperclass()).getActualTypeArguments()[0]));
        expected.put(TestClass1.ClassB.class.getTypeParameters()[0],
                expected.get(((ParameterizedType)TestClass1.ClassC.class.getGenericSuperclass()).getActualTypeArguments()[0]));
        expected.put(TestClass1.ClassA.class.getTypeParameters()[0],
                expected.get(((ParameterizedType)TestClass1.ClassB.class.getGenericSuperclass()).getActualTypeArguments()[0]));
        assertEquals(expected, parameters);
    }

    //==================== Testing Utilities ==================================

    private static class TestClass1 {

        static class ClassA<T> {
            void a(T arg1, List<? extends T> arg2) {  }
        }
        static class ClassB<T> extends ClassA<T> {
            @Override void a(T arg1, List<? extends T> arg2) {  }
        }
        static class ClassC<T extends C2 & I3> extends ClassB<T> {
            @Override void a(T arg1, List<? extends T> arg2) {  }
        }
        static class ClassD<T extends C4<? extends C5> & I3> extends ClassC<T> {
            @Override void a(T arg1, List<? extends T> arg2) {  }
        }
        static class ClassE<T extends C6<C5>> extends ClassD<T> {
            @Override void a(T arg1, List<? extends T> arg2) {  }
        }
        static class ClassF extends ClassE<C6<C5>> {
            @Override void a(C6<C5> arg1, List<? extends C6<C5>> arg2) {  }
        }
    }

    private static class TestClass2 {

        static class ClassA<T, K> {
            void a(T arg1, K arg2) {  }
        }
        static class ClassB<T extends C2 & I1 & I2, K extends List<? super T>> extends ClassA<T, K> {
            @Override void a(T arg1, K arg2) {  }
        }
        static class ClassC<T extends C2 & I2 & I1 & I7, K extends List<? super T> & I3> extends ClassB<T, K> {
            @Override void a(T arg1, K arg2) {  }
        }
        static class ClassD<T extends C2 & I2 & I1 & I7, K extends I3 & List<? super T>> extends ClassC<T, K> {
            @Override void a(T arg1, K arg2) {  }
        }
        static class ClassE<T extends C4 & I7, K extends List<T> & I3> extends ClassD<T, K> {
            @Override void a(T arg1, K arg2) {  }
        }
        static class ClassF extends ClassE<C4, C7> {
            @Override void a(C4 arg1, C7 arg2) {  }
        }
    }

    private static class TestClass3 {

        static class ClassA {
            <T extends List<? extends I2> & I2> void a(T arg1) {  }
        }
        static class ClassB extends ClassA {
            @Override <T extends I2 & List<? extends I2>> void a(T arg1) {  }
        }
    }

    private static class TestClass4 {

        static class ClassA<P> {
            <T extends List<? extends P> & I2> void a(T arg1) {  }
        }
        static class ClassB<K extends I1 & I2 & I3> extends ClassA<K> {
            @Override <T extends I2 & List<? extends K>> void a(T arg1) {  }
        }
        static class ClassC extends ClassB<C5> {
            @Override <T extends I2 & List<? extends C5>> void a(T arg1) {  }
        }
    }

    private static class TestClass5 {

        static class ClassA<P> {
            <T extends List<? extends P> & I2> void a(T[] arg1, List<? extends T> arg2) {  }
        }
        static class ClassB<K extends I1 & I2 & I3> extends ClassA<K> {
            @Override <T extends I2 & List<? extends K>> void a(T[] arg1, List<? extends T> arg2) {  }
        }
        static class ClassC extends ClassB<C5> {
            @Override <T extends I2 & List<? extends C5>> void a(T[] arg1, List<? extends T> arg2) {  }
        }
    }

    private static class TestClass6 {

        static class ClassA<P> {
            <T extends List<? extends P> & I2> void a(T[] arg1, List<? extends T> arg2) {  }
        }
        static class ClassB<K extends I1 & I2 & I3> extends ClassA<K> {
            <T extends I2> void a(T[] arg1, List<? extends T> arg2) {  }
        }
    }

    private static class TestClass7 {

        static class ClassA<P> {
            <T extends List<? extends P> & I2> void a(T[] arg1, List<? extends T> arg2) {  }
        }
        static class ClassB<K extends I1 & I2 & I3> extends ClassA<K> {
            <T extends Set<? extends K>> void a(T[] arg1, List<? extends T> arg2) {  }
        }
    }

    private static class TestClass8 {

        static class ClassA<P> {
            <T extends List<? extends P> & I2> void a(T[] arg1, List<? extends T> arg2) {  }
        }
        static class ClassB<K extends I1 & I2 & I3> extends ClassA<K> {
            <T extends List<? extends K> & I2> void a(T arg1, List<? extends T> arg2) {  }
        }
    }

    private static class TestClass9 {

        static class ClassA<P> {
            <T extends List<? extends P> & I2> void a(T[] arg1, List<? extends T> arg2) {  }
        }
        static class ClassB<K extends I1 & I2 & I3> extends ClassA<K> {
            <T extends List<? extends K> & I2> void a(T[] arg1, Set<? extends T> arg2) {  }
        }
    }

    private static class TestClass10 {

        static class ClassA<P> {
            void a(P[] arg1, List<? extends P> arg2) {  }
        }
        static class ClassB<K extends I1> extends ClassA<I1> {
            void a(K[] arg1, List<? extends K> arg2) {  }
        }
    }

    private static class TestClass11 {

        static class ClassA {
            <T extends I2 & I3> void a(T[] arg1, List<? extends T> arg2) {  }
        }
        static class ClassB extends ClassA {
            <T extends I3 & I4> void a(T[] arg1, List<? extends T> arg2) {  }
        }
    }

    private static class TestClass12 {

        static class ClassA {
            void a(Object arg) {  }
            void b(Object arg) {  }
        }
        static class ClassB {
            void a(Object arg) {  }
            void b(Object arg1, Object arg2) {  }
        }
    }

    private static class TestClass13 {

        static class ClassA {
            void a(Object arg) {  }
        }
        static class ClassB extends ClassA {
            void a(Object arg1, Object arg2) {  }
        }
    }

    private static interface I1 {  }
    private static interface I2 {  }
    private static interface I3 {  }
    private static interface I4 {  }
    private static interface I5 {  }
    private static interface I6 {  }
    private static interface I7 {  }
    private static class     C1 {  }
    private static class     C2                                                                implements I1, I2 {  }
    private static class     C3 <T extends List<? extends I3>>           extends C2 {  }
    private static class     C4 <T extends I1 & I2 & List<? extends I3>> extends C3<T>         implements I7 {  }
    private static class     C5                                          extends ArrayList<I3> implements I1, I2, I3 {  }
    private static class     C6 <T extends C5>                           extends C4<T>         implements I3 {  }
    private static class     C7                                          extends ArrayList<C4> implements I3 {  }
}
