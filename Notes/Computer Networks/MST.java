import java.util.*;

class Node{
    int n,w;
    public Node(int n, int w){
        this.n=n;
        this.w=w;
    }
}

class MST{
    public static void main(String[] args) {

        ArrayList<ArrayList<Node>> Graph= new ArrayList<>();
        constructGraph(Graph);
        ArrayList<ArrayList<Integer>> MST= new ArrayList<>();
        int visited[]= new int[Graph.size()];
        System.out.println(prims(Graph,0,visited,MST) +" is MST path sum");
        System.out.println("Edge list in MST "+MST);

    }
    private static void constructGraph(ArrayList<ArrayList<Node>> Graph){
        System.out.println("Enter number of Nodes ");
        Scanner sc= new Scanner(System.in);
        int nodes=sc.nextInt();
        for(int i=0;i<nodes;i++){
            System.out.println("Enter degree of node "+i);
            int deg=sc.nextInt();
            ArrayList<Node> al= new ArrayList<>();
            for(int j=0;j<deg;j++){
                System.out.println("enter the node to with node "+i +" is connected");
                int n=sc.nextInt();
                System.out.println("Enter edge weight ");
                int w=sc.nextInt();
                Node no= new Node(n,w);
                al.add(no);
            }
            Graph.add(al);
        }
    }
    private static int prims(ArrayList<ArrayList<Node>> Graph, int start, int[] visited, ArrayList<ArrayList<Integer>> MST) {
        int sum = 0;
        PriorityQueue<ArrayList<Integer>> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(0)));
        ArrayList<Integer> inq = new ArrayList<>(List.of(0, start, -1));
        q.add(inq);

        while (!q.isEmpty()) {
            ArrayList<Integer> pop = q.poll();
            int cost = pop.get(0);
            int node = pop.get(1);
            int parent = pop.get(2);

            if (visited[node] != 1) {
                visited[node] = 1;
                sum += cost;

                if (parent != -1) {
                    ArrayList<Integer> mst = new ArrayList<>();
                    mst.add(parent);
                    mst.add(node);
                    MST.add(mst);
                }

                for (int i = 0; i < Graph.get(node).size(); i++) {
                    int neighbor = Graph.get(node).get(i).n;
                    int weight = Graph.get(node).get(i).w;
                    if (visited[neighbor] != 1) {
                        ArrayList<Integer> sub = new ArrayList<>();
                        sub.add(weight);
                        sub.add(neighbor);
                        sub.add(node);
                        q.add(sub);
                    }
                }
            }
        }
        return sum;
    }

}