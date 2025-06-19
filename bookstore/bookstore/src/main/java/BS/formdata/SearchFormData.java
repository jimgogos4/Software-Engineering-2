package BS.formdata;

public class SearchFormData {
    private String query;

    public SearchFormData() {
    }

    public SearchFormData(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
