// Time Complexity - O(N) + O(V+E) ~ O(V+E) 
// Space Complexity O(N) for queue and O(N) for indegree 
/*
I first build an adjacency list and an indegree array from the prerequisites, where indegree[i] tracks how many prerequisites course i has.
Then I add all courses with indegree 0 to a queue and repeatedly process them, decrementing the indegree of their neighbors and pushing any that become 0 into the queue while counting how many courses I can take.
In the end, if the count of processed courses equals numCourses, it means there is no cycle and I can finish all courses; otherwise, it’s not possible.
*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer,List<Integer>> hmap = new HashMap<>();
        int[] indegree = new int[numCourses];

        for(int[] pre : prerequisites) {
            if(!hmap.containsKey(pre[1])) {
                hmap.put(pre[1], new ArrayList<>());
            }

            hmap.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }

        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i<indegree.length;i++) {
            if(indegree[i] == 0) {
                q.add(i);
                cnt++;
            }
        }

        if(cnt == numCourses) {
            return true;
        }

        if(q.isEmpty()) {
            return false;
        }

        while(!q.isEmpty()) {
            int node = q.poll();
            List<Integer> neighbors = hmap.getOrDefault(node, Collections.emptyList());
            for(int i : neighbors) {
                indegree[i]--;
                if(indegree[i] == 0) {
                    q.add(i);
                    cnt++;
                }
            }
        }

        if(cnt == numCourses) {
            return true;
        } else {
            return false;
        }
    }
}
