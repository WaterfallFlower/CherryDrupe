/*
    CherryDrupe - an useful set of experimental tools.
    Copyright (C) 2022 ChessChicken-KZ

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package kz.chesschicken.cherrydrupe.kiln;

import kz.chesschicken.cherrydrupe.hijack.impl.UnsafeUtilities;
import kz.chesschicken.cherrydrupe.kiln.accessor.Kiln;
import kz.chesschicken.cherrydrupe.kiln.accessor.KilnField;
import kz.chesschicken.cherrydrupe.kiln.field.Getter;
import kz.chesschicken.cherrydrupe.kiln.field.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Kiln - is another wrapper of Unsafe + Reflections combinations, allowing accessing field and methods which shouldn't be though.
 * @author ChessChicken-KZ
 * @since 0.3
 */
public class KilnProcessor {

    public static void processStaticOnly(@NotNull Class<?> c) throws NoSuchFieldException {
        process(c, null, true, false);
    }

    public static void processDynamicOnly(@NotNull Class<?> c, @NotNull Object t) throws NoSuchFieldException {
        process(c, t, false, true);
    }

    public static void processAll(@NotNull Class<?> c, @NotNull Object t) throws NoSuchFieldException {
        process(c, t, true, true);
    }

    public static void process(@NotNull Class<?> c, @Nullable Object t, boolean processStatic, boolean processDynamic) throws NoSuchFieldException {
        if(c.isAnnotationPresent(Kiln.class)) {
            for(Field field : c.getDeclaredFields()) {
                if(!field.isAnnotationPresent(KilnField.class))
                    continue;

                if(field.getType().isAssignableFrom(Setter.class)) {
                    if(processStatic && Modifier.isStatic(field.getModifiers())) {
                        UnsafeUtilities.setStaticField(c, field.getName(), generateSetter(field.getAnnotation(KilnField.class)));
                    }else if(processDynamic) {
                        UnsafeUtilities.setField(c, field.getName(), generateSetter(field.getAnnotation(KilnField.class)), t);
                    }
                }

                if(field.getType().isAssignableFrom(Getter.class)) {
                    if(processStatic && Modifier.isStatic(field.getModifiers())) {
                        UnsafeUtilities.setStaticField(c, field.getName(), generateGetter(field.getAnnotation(KilnField.class)));
                    }else if (processDynamic) {
                        UnsafeUtilities.setField(c, field.getName(), generateGetter(field.getAnnotation(KilnField.class)), t);
                    }
                }
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    private static boolean isInvalidField(@NotNull Class<?> clazz, @NotNull KilnField field) {
        try {
            return clazz.getDeclaredField(field.name()) == null; //Just for more "security".
        } catch (NoSuchFieldException e) {
            return true;
        }
    }

    private static <T> @NotNull Setter<T> generateSetter(@NotNull KilnField field) {
        if(isInvalidField(field.target(), field))
            throw new KilnHijackException("Cannot find field!");

        return field.isStaticField() ? new Setter<T>() {
            @Override
            public void set(Object o, T t) {
                this.set(t);
            }

            @Override
            public void set(T t) {
                try {
                    UnsafeUtilities.setStaticField(field.target(), field.name(), t);
                }catch (NoSuchFieldException e) {
                    throw new KilnHijackException("Tried to set provided field, but it does not exist!", e);
                }
            }
        } : new Setter<T>() {
            @Override
            public void set(Object o, T t) {
                try {
                    UnsafeUtilities.setField(field.target(), field.name(), o, t);
                }catch (NoSuchFieldException e) {
                    throw new KilnHijackException("Tried to set provided field, but it does not exist!", e);
                }
            }

            @Override
            public void set(T t) {
                throw new KilnHijackException("Accessing as static while the context field is dynamic!");
            }
        };
    }

    private static <T> @NotNull Getter<T> generateGetter(@NotNull KilnField field) {
        if(isInvalidField(field.target(), field))
            throw new KilnHijackException("Cannot find field!");

        return field.isStaticField() ? new Getter<T>() {
            @Override
            public T get(Object o) {
                return this.get();
            }

            @Override
            public T get() {
                try {
                    return UnsafeUtilities.getStaticField(field.target(), field.name());
                } catch (NoSuchFieldException e) {
                    throw new KilnHijackException("Tried to get provided field, but it does not exist!", e);
                }
            }
        } : new Getter<T>() {
            @Override
            public T get(Object o) {
                try {
                    return UnsafeUtilities.getField(field.target(), field.name(), o);
                } catch (NoSuchFieldException e) {
                    throw new KilnHijackException("Tried to get provided field, but it does not exist!", e);
                }
            }

            @Override
            public T get() {
                throw new KilnHijackException("Accessing as static while the context field is dynamic!");
            }
        };
    }


}
