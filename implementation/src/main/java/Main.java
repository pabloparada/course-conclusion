import java.util.DoubleSummaryStatistics;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        final Main main = new Main();

        final Config config = new Config("pabloparada160290!", "pablo.parada");

        Kleisli<Reader<Config, User>, Config, User> k = new Kleisli<>(i -> {
            return main.getUserFromConfig();
        });

        k.run(config);

        Kleisli<Maybe<Integer>, String, Integer> md = new Kleisli<>(s -> new Just<>(Integer.valueOf(s)));
        Kleisli<Maybe<Double>, Integer, Double> mb = new Kleisli<>(d -> new Just<>(d.doubleValue()));

        Kleisli<Maybe<Double>, String, Double> mc = md.andThen(mb);
        Kleisli<Maybe<Double>, String, Double> ma = mb.compose(md);

        System.out.println(mc.run("7").get());

        System.out.println(main.getSessionIdFromConfig(config));
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