public class GenericsKbAVLApp {
    public String item, statement;
    public double confidenceScore;

    public GenericsKbAVLApp(String item, String statement, double confidenceScore) {
        this.item = item;
        this.statement = statement;
        this.confidenceScore = confidenceScore;
    }
    public void setItem (String item){
        System.out.println(item);
    }
    public String getItem(){
        return item;
    }


    public static void main (String [] args){

       // GenericsKbAVLApp object = new GenericsKbAVLApp(null, null, 0);

    }
}
