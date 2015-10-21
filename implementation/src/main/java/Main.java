import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        final Main main = new Main();

        final Config config = new Config("pabloparada160290!", "pablo.parada");

        Kleisli<Reader<Config, User>, Config, User> k = new Kleisli<>(i -> {
            return main.getUserFromConfig();
        });

        k.run(config);

        Kleisli<Maybe<Double>, Integer, Double> mb = new Kleisli<>(d -> Just.pure(d.doubleValue()));
        Kleisli<Maybe<String>, Double, String> ma = new Kleisli<>(d -> Just.pure(d.toString()));

        System.out.println(main.getSessionIdFromConfig(config));

        System.out.println(main.h().andThen(main.f().andThen(main.g())).run(5));

    }

    private Kleisli<Maybe<Integer>, Integer, Integer> h() {
        return new Kleisli<>(i -> Just.pure(i - 5));
    }

    private Kleisli<Maybe<Integer>, Integer, Integer> f() {
        return new Kleisli<>(i -> Just.pure(i + 1));
    }

    Kleisli<Maybe<Integer>, Integer, Integer> g() {
        return new Kleisli<>(i -> new Nothing());
    }

    public Maybe<Double> divide(double a, double b) {
        if (b == 0) return new Nothing();
        else return Just.pure(a / b);
    }

    public String getSessionIdFromConfig(Config config) {
        return getUserFromConfig().run(config).getSessionId();
    }

    public Reader<Config, User> getUserFromConfig() {
        return getPassword().flatMap(pass -> getUsername().map(name -> new User(pass, name, UUID.randomUUID().toString())));
    }

    public Reader<Config, String> getPassword() {
        return new Reader<>(p -> p.getPassword());
    }

    public Reader<Config, String> getUsername() {
        return new Reader<>(c -> c.getUsername());
    }

}

class User {

    private String password;
    private String username;
    private String sessionId;

    public User(String password, String username, String sessionId) {
        this.password = password;
        this.username = username;
        this.sessionId = sessionId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

class Config {

    private String password;
    private String username;

    public Config() {}

    public Config(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}