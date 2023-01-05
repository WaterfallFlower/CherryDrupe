/*
    CherryDrupe - an useful set of experimental tools.
    Copyright (C) 2023 ChessChicken-KZ

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
package kz.chesschicken.cherrydrupe.kiln.accessor;

import org.jetbrains.annotations.Nullable;

import java.lang.annotation.*;

/**
 * An annotation to define a field that should accept hijack functions.
 * @see kz.chesschicken.cherrydrupe.kiln.field.Getter Hijack function for field getter.
 * @see kz.chesschicken.cherrydrupe.kiln.field.Setter Hijack function for field setter.
 * @author ChessChicken-KZ
 * @since 0.3
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface KilnField {

    /**
     * A default name of the field.
     * @return Field name.
     */
    String name();

    /**
     * Deobfuscated name of the field (can be "" if there isn't any obfuscation).
     * @return Deobfuscated field name.
     */
    String deobfuscated() default "";

    /**
     * Returns the target, where the hijacking needs to be performed.
     * @return Target class.
     */
    Class<?> target();

    /**
     * Returns true if the given field is static.
     * @return Static field boolean.
     */
    boolean isStaticField() default false;

}
