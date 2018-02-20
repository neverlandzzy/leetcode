package oa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	
    static int getDefectCount(int[][] image) {
        // Write your code here.

        int m = image.length;
        int n = image[0].length;
        
        int counter = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (image[i][j] == 1) {
                    counter++;
                    bfs(image, i, j);
                }
            }
        }
        return counter;
    }

    static void bfs(int[][] image, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        visited(image, i, j, queue);
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            
            int x = cell[0];
            int y = cell[1];
            visited(image, x + 1, y, queue);
            visited(image, x - 1, y, queue);
            visited(image, x, y + 1, queue);
            visited(image, x, y - 1, queue);
        }
    }

    static void visited(int[][] image, int i, int j, Queue<int[]> queue) {
        int m = image.length;
        int n = image[0].length;
        
        if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || image[i][j] != 1) {
            return;
        }
        
        image[i][j] = -1;
        queue.offer(new int[]{i, j});
    }
	

}
