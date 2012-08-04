package org.ivory.commons.algo.graph;

public class Dijkstra {
	static int[] dijsktra(int[][] weight, int start) {
		int n = weight.length;
		int[] shortPath = new int[n];
		for (int i = 0; i < n; i++)
			shortPath[i] = Integer.MAX_VALUE;
		boolean[] visited = new boolean[n];
		shortPath[start] = 0;
		visited[start] = true;

		for (int count = 1; count < n; count++) {
			int k = -1;
			int dmin = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (!visited[i] && weight[start][i] != Integer.MAX_VALUE
						&& weight[start][i] < dmin) {
					dmin = weight[start][i];
					k = i;
				}
			}
			if (k < 0)
				break;
			shortPath[k] = dmin;
			visited[k] = true;
			for (int i = 0; i < n; i++) {
				if (!visited[i] && weight[start][k] != Integer.MAX_VALUE
						&& weight[k][i] != Integer.MAX_VALUE
						&& weight[start][k] + weight[k][i] < weight[start][i])
					weight[start][i] = weight[start][k] + weight[k][i];
			}
		}

		return shortPath;
	}

	static long[] dijsktra(long[][] weight, int start) {
		int n = weight.length;
		long[] shortPath = new long[n];
		for (int i = 0; i < n; i++)
			shortPath[i] = Long.MAX_VALUE;
		boolean[] visited = new boolean[n];
		shortPath[start] = 0;
		visited[start] = true;

		for (int count = 1; count < n; count++) {
			int k = -1;
			long dmin = Long.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (!visited[i] && weight[start][i] != Long.MAX_VALUE
						&& weight[start][i] < dmin) {
					dmin = weight[start][i];
					k = i;
				}
			}
			if (k < 0)
				break;
			shortPath[k] = dmin;
			visited[k] = true;
			for (int i = 0; i < n; i++) {
				if (!visited[i] && weight[start][k] != Long.MAX_VALUE
						&& weight[k][i] != Long.MAX_VALUE
						&& weight[start][k] + weight[k][i] < weight[start][i])
					weight[start][i] = weight[start][k] + weight[k][i];
			}
		}
		return shortPath;
	}	
}
