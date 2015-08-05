import java.util.Objects;
import java.util.function.Function;

public class Reader<E, A> implements Monad<A, Reader<?, ?>> {

    private Function<? super E, ? extends A> f;

    public Reader(Function<? super E, ? extends A> f) {
        Objects.requireNonNull(f);
        this.f = f;
    }

    public A run(E e) {
        return f.apply(e);
    }

    @Override
    public <B, MB extends Kind<B, Reader<?, ?>>> Reader<E, B> flatMap(Function<? super A, MB> g) {
        return new Reader<>((E e) -> ((Reader<E, B>) g.apply(run(e))).run(e));
    }

    @Override
    public <B> Reader<E, B> map(Function<? super A, ? extends B> g) {
        return new Reader<>((E e) -> g.apply(run(e)));
    }
}
