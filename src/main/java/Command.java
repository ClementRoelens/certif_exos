import java.util.List;

public class Command {
    private int id;
    private String client;
    private List<String> articles;
    private double montantTotal;
    private boolean estLivree;

    public Command(int id, String client, List<String> articles, double montantTotal, boolean estLivree) {
        this.id = id;
        this.client = client;
        this.articles = articles;
        this.montantTotal = montantTotal;
        this.estLivree = estLivree;
    }

    public int getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public List<String> getArticles() {
        return articles;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public boolean isEstLivree() {
        return estLivree;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", articles=" + articles +
                ", montantTotal=" + montantTotal +
                ", estLivree=" + estLivree +
                '}';
    }
}
