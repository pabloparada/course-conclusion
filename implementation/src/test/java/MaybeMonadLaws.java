import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class MaybeMonadLaws {

    private Function<Integer, Maybe<String>> f = i -> Just.pure(i.toString());
    private Function<String, Maybe<Integer>> g = s -> Just.pure(Integer.valueOf(s));

    private Maybe<Integer> m = Just.pure(3);

    @Test
    public void leftIdentity() {
        final int a = 3;
        assertEquals(Just.pure(a).flatMap(f), f.apply(a));
    }

    @Test
    public void rightIdentity() {
        assertEquals(m.flatMap(Just::pure), m);
    }

    @Test
    public void associativity() {
        assertEquals(m.flatMap(f).flatMap(g), m.flatMap(x -> f.apply(x).flatMap(g)));
    }
}