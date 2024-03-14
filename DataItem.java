public class DataItem implements Comparable <DataItem> {
    public String term, statement;
    public double confidenceScore;

    public DataItem(String term, String statement, double confidenceScore) {
        this.term = term;
        this.statement = statement;
        this.confidenceScore = confidenceScore;
    }
    
    public void setItem (String item){
        System.out.println(item);
    }
    public String getItem(){
        return term;
    }

    @Override 
    public int compareTo(DataItem other) {
        return this.term.compareTo(other.term);
    }
}