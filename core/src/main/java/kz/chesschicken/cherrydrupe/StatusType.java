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

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Avoid using this until I get the idea of this.
 * @author ChessChicken-KZ
 * @param <T> Something...
 */
@ApiStatus.Experimental
public class StatusType<T> {

    public enum Status {
        SUCCESS,
        FAIL,
        NULL
    }

    final @Nullable T value;
    final @NotNull Status status;

    public @Nullable T getValue() {
        return this.value;
    }

    public @NotNull Status getStatus() {
        return this.status;
    }

    public boolean isSuccess() {
        return this.status == Status.SUCCESS;
    }

    StatusType(@Nullable T t, @NotNull Status s) {
        this.value = t;
        this.status = s;
    }

    public static <A> @NotNull StatusType<A> success(@Nullable A a) {
        return new StatusType<>(a, Status.SUCCESS);
    }

    public static <A> @NotNull StatusType<A> fail(@Nullable A a) {
        return new StatusType<>(a, Status.FAIL);
    }

    public static <A> @NotNull StatusType<A> _null() {
        return new StatusType<>(null, Status.NULL);
    }
}
