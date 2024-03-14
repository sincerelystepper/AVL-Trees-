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

    public String getStatement (){
        return statement;
    }

    public double getConfidence(){
        return confidenceScore;
    }


    @Override 
    public int compareTo(DataItem other) {
        return this.term.compareTo(other.term);
    }
}