import java.util.NoSuchElementException;

public final class Nothing<A> implements Maybe<A> {
    public Nothing() {}

    @Override
    public Boolean isDefined() {
        return false;
    }

    @Override
    public A get() {
        throw new NoSuchElementException("There's no value to get.");
    }

    @Override
    public String toString() {
        return "Nothing";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Nothing;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}