import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class KleisliArrowLaws {

    final private Integer a = 5;

    @Test
    public void identity() {
        final Function<Integer, Maybe> f = i -> Just.pure(i + 1);

        assertEquals(new Kleisli<>(f).run(a), f.apply(a));
    }

    @Test
    public void associativity() {
        final Kleisli<Maybe<Integer>, Integer, Integer> f = new Kleisli<>(i -> Just.pure(i + 1));
        final Kleisli<Maybe<Integer>, Integer, Integer> g = new Kleisli<>(i -> Just.pure(i + 2));
        final Kleisli<Maybe<Integer>, Integer, Integer> h = new Kleisli<>(i -> Just.pure(i - 5));

        assertEquals(g.andThen(h).andThen(f).run(a), f.andThen(g.andThen(h)).run(a));
    }
}