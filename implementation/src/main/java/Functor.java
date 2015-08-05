import java.util.function.Function;

@FunctionalInterface
public interface Functor<A> {
    <B> Functor<B> map(Function<? super A, ? extends B> f);
}
