package org.insa.graphs.algorithm.utils;

//lire un graphe
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import org.insa.graphs.algorithm.*;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.*;

import org.junit.*;

public class AStarAlgorithmTest {
    
   
    public void testMap(String mapName, int Mode, int origine, int destination) throws Exception {
		//afficher infos de depart et destination
		System.out.println("Origine : " + origine);
		System.out.println("Destination : " + destination);

		// Create a new BinaryGraphReader that read from the given input stream.
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

		// Read the graph.
		Graph graph = reader.read();

            //Argument invalide
		if (Mode!=0 && Mode!=1) {
			System.out.println("argument incorrecte");
		} else {
                 //// cas:hors du graphe ou sommets inexistent
			if (origine<0 || destination<0 || origine>=(graph.size()-1) || destination>=(graph.size()-1)) { 
				System.out.println("Parametres incorrecte ");

			} else {
                //This class can be used to indicate to an algorithm which arcs can be used and the costs of the usable arcs..
				ArcInspector arcInspectorAstar;

                //ArcInspectorFactory:filtre des arcs d'apres le choix de mode
                    //en temps
				if (Mode == 0) { 
					System.out.println("Mode : Temps");
                    // correspond au troisième filtre "Only road allowed for cars and time"
					arcInspectorAstar = ArcInspectorFactory.getAllFilters().get(2); 
				} 
                else { 
					System.out.println("Mode : Distance");
                    // correspond au premième filtre "No filter (all arcs allowed)"
					arcInspectorAstar = ArcInspectorFactory.getAllFilters().get(0);
				}




				if(origine==destination) {
					System.out.println("Origine et Destination identiques , donc cout solution: 0 ");

				} 
                else 
                {		
					ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspectorAstar);

					AStarAlgorithm D = new AStarAlgorithm(data);
                

					ShortestPathSolution solutionD = D.run();

					double costSolutionD;
		
                    //get le temps ou le longueur d'apres le mode
					if(Mode == 0) { //Temps
						//Calcul du cout de la solution 
						costSolutionD = solutionD.getPath().getMinimumTravelTime();

					} else {//distance
						costSolutionD = solutionD.getPath().getLength();
					
					}
                    System.out.println("Cout solution d'alogorithme A*: " + costSolutionD);
                    


				}
			}
		}
		System.out.println();
		System.out.println();
	}



     @Test 
    public void testMapINSAdistance() throws Exception {

        System.out.println("test de distance avec la carte insa ");

        String mapName = "/Users/yongjiazeng/Desktop/3mic下/be graphe/map/insa.mapgr";

        AStarAlgorithmTest test = new AStarAlgorithmTest();
        int  origine ;
        int destination;

        System.out.println("test avec un chemin normal");
        origine = 700 ;
        destination = 300;
        test.testMap(mapName, 1,origine,destination);


        System.out.println("test avec un chemin null");
        origine = 700 ;
        destination = 90000;
        test.testMap(mapName, 1, origine, destination);

        System.out.println("test avec un chemin null");
        origine = 700 ;
        destination = 700;
        test.testMap(mapName, 1,origine,destination);
    }
        
    @Test 
    public void testMapINSAtemps() throws Exception {

        System.out.println("test de distance avec la carte insa ");

        String mapName = "/Users/yongjiazeng/Desktop/3mic下/be graphe/map/insa.mapgr";
        AStarAlgorithmTest Test = new AStarAlgorithmTest();
        int  origine ;
        int destination;

        System.out.println("test avec un chemin normal");
        origine = 700 ;
        destination = 300;
        Test.testMap(mapName, 0,origine,destination);


        System.out.println("test avec un chemin null");
        origine = 700 ;
        destination = 90000;
        Test.testMap(mapName, 0, origine, destination);

        System.out.println("test avec un chemin null");
        origine = 700 ;
        destination = 700;
        Test.testMap(mapName, 0,origine,destination);

    }
}

