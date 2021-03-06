public final class Just<A> implements Maybe<A> {
    private A value;

    private Just(A value) {
        this.value = value;
    }

    public static <A> Maybe<A> pure(A a) {
        if (a == null) return new Nothing();
        else return new Just<>(a);
    }

    @Override
    public Boolean isDefined() {
        return true;
    }

    @Override
    public A get() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Just(" + get() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Just just = (Just) o;

        if (value != null ? !value.equals(just.value) : just.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
