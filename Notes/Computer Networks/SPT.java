import java.util.*;

class Node{
    int n,w;
    public Node(int n, int w){
        this.n=n;
        this.w=w;
    }
}

class SPT
{
    public static void main(String[] args) {

        ArrayList<ArrayList<Node>> Graph= new ArrayList<>();
        constructGraph(Graph);
        int visited[]= new int[Graph.size()];
        System.out.println("Enter Destination Node ");
        Scanner sc= new Scanner(System.in);
        int dest=sc.nextInt();
        int[] dist = dijkstra(Graph, 0, visited);
        System.out.println("Shortest path from node 0 to node " + dest + " is " + dist[dest]);

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
    private static int[] dijkstra(ArrayList<ArrayList<Node>> Graph, int start, int[] visited) {
        int V = Graph.size();
        int dist[] = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.w));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.n;

            if (visited[u] == 1) continue;
            visited[u] = 1;

            for (Node adj : Graph.get(u)) {
                int v = adj.n;
                int weight = adj.w;
                if (visited[v] != 1 && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }
        return dist;
    }
}
