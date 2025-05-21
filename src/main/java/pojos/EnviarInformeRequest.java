package pojos;
import java.util.List;

public class EnviarInformeRequest {
    private List<Integer> ids;
    private String email;

    // getters y setters

    public List<Integer> getIds() {
        return ids;
    }
    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
