/**
 * @author Stephen Tselikov
 * @date 11/14/2019
 */
import java.util.*;

public class GraphImplementation implements Graph
{
	//initialize the matrix and vertices for the graph
	int[][]matrix;
	int vertices;
	
	//constructor initializes variables 
	public GraphImplementation(int v)
	{
		vertices = v;
		matrix = new int[vertices][vertices];
	}
	//adds and edge to the graph
	public void addEdge(int v1, int v2) throws Exception
	{
		//if invalid value, then it throws an exception
		if(v1>=matrix.length||v1<0||v2>=matrix.length||v2<0)
		{
			throw new Exception("OUT OF BONDS."); //out of bounds
		}
		matrix[v1][v2] = 1; 
	}
	//topological sort function
	//takes the graph and sorts it such that every directed edge from vertex is in order.
	public List<Integer> topologicalSort()
	{
		//creates a list
		List<Integer> schedule = new ArrayList<Integer>();
		int[] total = new int[matrix.length];
		//gets the total from the matrix per column
		for(int i=0;i<vertices;i++)
		{
			for(int j=0;j<vertices;j++)
			{
				total[i]+=matrix[j][i];
			}
		}
		//will loop through the matrix until every single vertex is in
		for(int i=0;i<vertices;i++)
		{
			int n=findZero(total);
			if(n==-1)
			{
				return schedule;
			}
			schedule.add(n);
			System.out.println(n);
			total[n]=-1;
			for(int j=0;j<vertices;j++)
			{
				total[j]-=matrix[n][j];
			}
		}
		return schedule;
	}
	
	//finds all the 0s
	private int findZero(int[] total) {
		for(int i=0;i<vertices;i++)
		{
			if(total[i]==0)
			{
				return i;
			}
		}
		//return 0 if none is found
		return 0;
	}
	
	//checks the neighboring vertices
	public List<Integer> neighbors(int vertex) throws Exception
	{
		//vertex has to be valid
		if(vertex>=vertices||vertex<0)
		{
			throw new Exception("OUT OF BOUNDS.");
		}
		//creates a neighbor list
		List<Integer> neighbors = new ArrayList<Integer>();
		//loops through each row 
		for(int i=0; i<vertices;i++)
		{
			if(matrix[vertex][i]>0)
			{
				neighbors.add(i);
			}
		}
		return neighbors;
	}
}