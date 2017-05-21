import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
* Kruskal’s Algorithm
* @version Manejo de Estructuras de Datos
* @author @JC_Worthing 
*/
public class kruskal
{ //Start of class Kruskal

   static class Edge implements Comparator<Edge>
   { // Start of class Edge
      int surce;
      int destiny;
      int weight;

      /**
      * Comparator, compare the current node with another according to weight.
      * @param compareEdge Node with which to compare.
      */
      @Override
      public int compare(Edge e1, Edge e2)
      {
         return e1.weight - e2.weight;
      }

   }; // End of class Edge

   static final int MAX = 100;
   static int parent[] = new int[MAX]; //represent a subset for union-find
   static int V;  // V no. of vertices
   static int E = -1;  // E no. of edges
   static Edge arista[] = new Edge[MAX]; // This will store the Edges values
   static Edge MST[] = new Edge[MAX];  // This will store the resultant MST

   /**
   * Method that initializes the subset of vertices
   * @param Vertices Number of vertices
   */
   static void makeSubSet(int vertices)
   {
	    for( int i = 1; i <= vertices; ++i){
          parent[i] = i;
       }
	}

   /**
   * Find root of i
   * @param i Vertice to search root
   */
   static int find( int i)
   {
    return(i == parent[i]) ? i : (parent[i] = find(parent[i]));
	}

   /**
   * method that does union of two sets of x and y
   * @param x Specifies vertex related with vertex y
   * @param y Specifies vertex related with vertex x
   */
   static void union(int x, int y)
   {
      int xRoot = find(x);
      int yRoot = find(y);
      parent[xRoot] = yRoot;
	}

   /**
   * method that determinates if the union of two vertices cause cycle.
   * @param x Specifies vertice related with vertice y
   * @param y Specifies vertice related with vertice x
   */
	public static boolean notCycle (int x, int y)
   {
	    if(find(x) == find(y))
         return true;
       else
	      return false;
	}

   /**
   * The main function to construct MST using Kruskal's algorithm
   * @param
   */
   static void kruskalMST()
   {
      int surce   = 0;
      int destiny = 0;
      int weight  = 0;
	   int total   = 0;
	   int numEdges = 0;

      BufferedWriter bw = null;
		FileWriter fw = null;

		try{

			String title = "Kruskal’s Algorithm\n";

			fw = new FileWriter("Results.txt");
			bw = new BufferedWriter(fw);
			bw.write(title);

		}catch (IOException e){
			e.printStackTrace();
      }

      makeSubSet(V);
      Arrays.sort(arista, 0, E + 1, new Edge());


      for( int i = 0; i <= E; i++)
      {
         surce = arista[i].surce;
         destiny = arista[i].destiny;
         weight  = arista[i].weight;

      if(!notCycle(surce, destiny))
         {
            total += weight;
	         MST[numEdges ++] = arista[i];
	         union(surce, destiny);
         }
      }

      if(V - 1 != numEdges)
      {
            try{
               String error = "This is the content to write into file\n";
               bw.append(error);
            }catch(IOException e){
				      e.printStackTrace();
            }

      return;

      }else{

            try{

               String tree = "The MST tree\n";
               String minimumCost = "the minimum cost is: ";
               String optimalEdges = "Optimal edges: \n";

               bw.append(tree);
               bw.append(minimumCost + total + "\n");
               bw.append(optimalEdges);
               for(int i = 0; i < numEdges; ++i){
                  bw.append("( " + MST[i].surce + "  --->  " + MST[ i ].destiny + " )" + " : " + MST[ i ].weight + "\n");
               }

            }catch(IOException e){
                  e.printStackTrace();

            }finally{

            try{

               if (bw != null)
                  bw.close();

               if (fw != null)
                  fw.close();

            }catch(IOException ex){
               ex.printStackTrace();
            }
         }
         }
   }

   public static void main(String[] args)
   {
     Scanner objectScanner;
     BufferedWriter bw = null;
	  FileWriter fw = null;

     int counter = 0;
     long startTime = System.currentTimeMillis();


     try
     {
        objectScanner = new Scanner(new FileReader("datos.txt"));

        while (objectScanner.hasNextInt())
        {

           if(counter == 0){
             V = objectScanner.nextInt();
           }

             arista[counter] = new Edge();
             arista[counter].surce   = objectScanner.nextInt();
             arista[counter].destiny = objectScanner.nextInt();
             arista[counter].weight  = objectScanner.nextInt();
             counter += 1;
             E += 1;
        }

        objectScanner.close();

     }catch(FileNotFoundException e){
        e.printStackTrace();
     }

     try
     {
         fw = new FileWriter("Results.txt");
			bw = new BufferedWriter(fw);

         kruskalMST();

         long endTime = System.currentTimeMillis();
         long duration = ((endTime - startTime));

         bw.append("total time: \n" + duration + "  milliseconds\n");

     }catch(IOException e){
        e.printStackTrace();

     }finally{

        	try{
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
            }catch(IOException ex){
               ex.printStackTrace();
            }
         }
     }

} // End of class Kruskal
