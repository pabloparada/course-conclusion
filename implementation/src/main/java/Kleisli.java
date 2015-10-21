import java.util.function.Function;

class Tuple<A, B> {
    public final A a;
    public final B b;
    public Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }
}

class Kleisli<M extends Monad<?, ?>, B, C> {
    private Function<B, M> f;

    public Kleisli(Function<B, M> f) {
        this.f = f;
    }

    public M run(B b) {
        return f.apply(b);
    }

    public <D, MC extends Monad<C, MC>, MD extends Monad<D, MD>, MDD extends Monad<D, ?>> Kleisli<MDD, B, D> andThen(Kleisli<MDD, C, D> k) {
        return new Kleisli<>(b -> (MDD) ((MC) this.run(b)).flatMap(c -> (MC) k.run(c)));
    }

    public <D, MB extends Monad<B, MB>, MBB extends Monad<B, ?>, MDD extends Monad<D, ?>, MCC extends Monad<C, ?>> Kleisli<MDD, C, D> compose(Kleisli<MBB, C, B> k) {
        return new Kleisli<>(c -> (MDD) ((MB) k.run(c)).flatMap(b -> (MB) this.run(b)));
    }
}