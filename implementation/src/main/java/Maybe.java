import java.util.function.Function;
import java.util.function.Supplier;

public interface Maybe<A> extends Monad<A, Maybe<?>> {

    Boolean isDefined();

    A get();

    default A orElse(A other) {
        if (isDefined()) return get();
        else return other;
    }

    default A orElseGet(Supplier<? extends A> other) {
        return orElse(other.get());
    }

    @Override
    default <B, MB extends Kind<B, Maybe<?>>> Maybe<B> flatMap(Function<? super A, MB> f) {
        if (isDefined()) return (Maybe<B>) f.apply(get());
        else return new Nothing<>();
    }

    @Override
    default <B> Maybe<B> map(Function<? super A, ? extends B> f) {
        if (isDefined()) return Just.pure(f.apply(get()));
        else return new Nothing<>();
    }
}