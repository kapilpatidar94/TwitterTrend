import java.io.*;
import java.util.*; 
import java.lang.*; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TwitterTrend {
public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    Pattern pattern = Pattern.compile("(#\\w+)");
    Matcher match;

  HashMap<String, Integer> hm = new HashMap<String, Integer>();
    for (int i = 0; i < t; i++) {
        String line = br.readLine();
        match = pattern.matcher(line);
        while (match.find()) {
            // System.out.println("***** "+match);
            if (hm.get(match.group()) != null ) {
                hm.put(match.group(), (hm.get(match.group()) + 1));
            } else {
                hm.put(match.group(), new Integer(1));
            }

        }

    }
  sortByValue(hm);
}
public static void sortByValue(HashMap<String, Integer> hm) 
    {  
       List<Map.Entry<String, Integer>> data = new ArrayList<Map.Entry<String, Integer>>(hm.entrySet());
    Collections.sort(data, new Comparator<Map.Entry<String, Integer>>() {
        public int compare(Map.Entry<String, Integer> x, Map.Entry<String, Integer> y) {
            if (y.getValue().equals(x.getValue()))
                return x.getKey().compareTo(y.getKey());
            else
                return y.getValue().compareTo(x.getValue());
        }
    });
    Map<String, Integer> sort = new LinkedHashMap<String, Integer>();
    for (Map.Entry<String, Integer> entry : data) {
        sort.put(entry.getKey(), entry.getValue());
    }
    Iterator it = sort.entrySet().iterator();
    int count = 1;
    while (it.hasNext()) {
        Map.Entry trend = (Map.Entry) it.next();
        System.out.println(trend.getKey());
        it.remove();
        if (count == 10)
            break;
        count++;
    } 
    } 
}

