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
package kz.chesschicken.cherrydrupe;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * Another cursed way to one line a code of initialization and load of an instance.
 * @author ChessChicken-KZ
 */
public class InitAndApply {

    /**
     * The main method of this class.<br>
     * An example of usage:<br>
     * <pre>{@code
     * Map<Integer, String> aMap = InitAndApply.apply(new HashMap<>(), instance -> {
     *      instance.put(0, "Sky");
     *      instance.put(5, "Cloud");
     * });
     * }</pre>
     *
     * @param instance An instance to be applied, should not be null.
     * @param acceptor {@link Consumer} A consumer that applies some specific code to the instance.
     * @param <A> The instance's type.
     * @return The instance with executed consumer.
     */
    public static<A> @NotNull A get(@NotNull A instance, @NotNull Consumer<A> acceptor) {
        acceptor.accept(instance);
        return instance;
    }
}