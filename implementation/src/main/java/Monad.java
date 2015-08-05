import java.util.function.Function;

public interface Monad<A, M extends Kind<?, M>> extends Functor<A>, Kind<A, M> {

    <B, MB extends Kind<B, M>> Monad<B, M> flatMap(Function<? super A, MB> f);

    @Override
    <B> Monad<B, M> map(Function<? super A, ? extends B> f);
}