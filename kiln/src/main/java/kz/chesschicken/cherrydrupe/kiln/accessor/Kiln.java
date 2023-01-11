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

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * The annotation to define a class to be manipulated inside a specific class, which had got tagged this annotation.
 *
 * @author ChessChicken-KZ
 * @since 0.3
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Kiln {

    /**
     * A class that needs to be manipulated with Kiln.
     *
     * @return A class value.
     */
    @NotNull Class<?> value();

}
