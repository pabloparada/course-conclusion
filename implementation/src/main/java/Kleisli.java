import java.util.function.Function;

class Kleisli<M extends Monad<?, ?>, B, C> {
    private Function<B, M> f;

    public Kleisli(Function<B, M> f) {
        this.f = f;
    }

    public M run(B b) {
        return f.apply(b);
    }

    public <D, MC extends Monad<C, MC>, MD extends Monad<D, ?>> Kleisli<MD, B, D> andThen(Kleisli<MD, C, D> k) {
        return new Kleisli<>(b -> (MD) ((MC) this.run(b)).flatMap(c -> (MC) k.run(c)));
    }
}