import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Clustering {
    public static HashMap<Integer, ArrayList<Customer>> points = new HashMap<>();
    public static ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        Clustering clustering = new Clustering();
        clustering.enterDataSet("Mall_Customers.csv");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter amount of points");
        int n = scanner.nextInt();
        for(int i = 0; i < n; i++){
            System.out.println("Enter point #" + (i+1));
            int point = scanner.nextInt();
            points.put(point, new ArrayList<Customer>());
        }

        clustering.doClustering();
        for(Integer point : points.keySet()){
            System.out.println("Cluster: " + point);
            System.out.println(points.get(point).toString());
        }
    }

    private void doClustering(){
        for(Customer customer : customers){
            int min = Integer.MAX_VALUE;
            Integer cluster = null;
            for(int point : points.keySet()){
                int dist = Math.abs(customer.getAge()-point);
                if(dist <= min){
                    min = dist;
                    cluster = point;
                }
            }
            ArrayList<Customer> customers = points.get(cluster);
            customers.add(customer);
            points.put(cluster, customers);
        }
    }

    private void enterDataSet(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + filename));
            String s;
            while((s = br.readLine())!=null){
                String[] data = s.split(",");
                Customer customer = new Customer(Integer.parseInt(data[0]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                customers.add(customer);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
