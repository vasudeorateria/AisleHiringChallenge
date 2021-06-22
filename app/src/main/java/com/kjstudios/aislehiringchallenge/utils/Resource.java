package com.kjstudios.aislehiringchallenge.utils;

import androidx.annotation.NonNull;

public abstract class Resource<T> {
    T data;
    Throwable throwable;

    public Resource(T data, Throwable throwable) {
        this.data = data;
        this.throwable = throwable;
    }

    public static final class Success<T> extends Resource {
        @NonNull
        T data;
        Throwable throwable;

        public Success(@NonNull T data, Throwable throwable) {
            super(data, throwable);
            this.data = data;
            this.throwable = throwable;
        }

        @NonNull
        public T getData() {
            return data;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }

    public static final class Error<T> extends Resource {
        T data;
        @NonNull
        Throwable throwable;

        public Error(T data, @NonNull Throwable throwable) {
            super(data, throwable);
            this.data = data;
            this.throwable = throwable;
        }

        public T getData() {
            return data;
        }

        @NonNull
        public Throwable getThrowable() {
            return throwable;
        }
    }

    public static final class Loading<T> extends Resource {
        T data;
        Throwable throwable;

        public Loading(T data, Throwable throwable) {
            super(data, throwable);
            this.data = data;
            this.throwable = throwable;
        }

        public T getData() {
            return data;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }

}
