import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import java.io.PrintWriter;
public class csvReader {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<String>();
        try{
            Scanner in = new Scanner(new File("names.csv"));
            while(in.hasNextLine()){
                names.add(in.nextLine());
            }
        }
        catch(Exception e){}
        int n = names.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // to compare one string with other strings
                if (names.get(i).compareTo(names.get(j)) > 0) {
                    // swapping
                    String temp = names.get(i);
                    names.set(i,names.get(j));
                    names.set(j,temp);
                }
            }
        }
        File csvOutputFile = new File("names.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            for (String string : names) {
                String[] row = {string};
                pw.println(convertToCSV(row));
            }
        }catch (Exception e) {

        }
    
    }
    public static String convertToCSV(String[] data){
        return Stream.of(data).collect(Collectors.joining(","));
    }

}
