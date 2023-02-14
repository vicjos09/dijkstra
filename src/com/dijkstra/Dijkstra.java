package com.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    static final int V = 20






            ;

    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    int[] dijkstra(int graph[][], int src, int dest) {
        int dist[] = new int[V];

        Boolean sptSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < V; v++)

                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        int[] path = new int[V];
        int index = 0;
        for (int i = dest; i != src; i = path[i]) {
            path[index++] = i;
            for (int j = 0; j < V; j++) {
                if (graph[i][j] != 0 && dist[i] == dist[j] + graph[i][j]) {
                    path[index++] = j;
                    break;
                }
            }
        }
        path[index++] = src;

        int[] result = new int[index];
        for (int i = 0; i < index; i++) {
            result[i] = path[index - i - 1];
        }

        return result;
    }


}
