import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class MaybeMonadLaws {

    private Function<Integer, Maybe<String>> f = i -> new Just<>(i.toString());
    private Function<String, Maybe<Integer>> g = s -> new Just<>(Integer.valueOf(s));

    private Maybe<Integer> m = new Just<>(3);

    @Test
    public void leftIdentity() {
        final Integer a = 3;
        assertEquals(new Just<>(a).flatMap(f), (f.apply(a)));
    }

    @Test
    public void rightIdentity() {
        assertEquals(m.flatMap(Just::new), m);
    }

    @Test
    public void associativity() {
        assertEquals(m.flatMap(f).flatMap(g), m.flatMap(x -> f.apply(x).flatMap(g)));
    }
}