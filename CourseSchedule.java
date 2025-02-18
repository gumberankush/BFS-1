// Time Complexity: O(V + E) where V is number of courses and E is number of prerequisites
// Space Complexity: O(V + E) where V is number of courses and E is number of prerequisites
// Approach: We will use BFS to traverse the graph level by level. We will use a queue to store the nodes at each level.
// We will also use an indegrees array to keep track of the number of prerequisites for each course.
// We will also use a map to store the edges between the courses.
// We will add the independent courses to the queue and decrement the indegrees of the dependent courses.

import java.util.*;


class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses == 0){
            return true;
        }

        int[] indegrees = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        // edges
        int n = prerequisites.length;
        for(int i = 0; i < n; i++){
            int ind = prerequisites[i][1];
            int dep = prerequisites[i][0];
            indegrees[dep]++;

            if(!map.containsKey(ind)){
                map.put(ind, new ArrayList<>());
            }
            map.get(ind).add(dep);
        }

        int count = 0;
        // add independent to the queue
        for(int i = 0; i < indegrees.length; i++){
            if(indegrees[i] == 0){
                queue.add(i);
                count++;
            }
        }
        if(count == numCourses){
            return true;
        }
        if(queue.isEmpty()){
            return false;
        }

        while(!queue.isEmpty()){
            int curr = queue.poll();

            if(!map.containsKey(curr)){
                continue;
            }
            List<Integer> children = map.get(curr);
            for(Integer child: children){
                indegrees[child]--;

                if(indegrees[child] == 0){
                    queue.add(child);
                    count++;

                    if(count == numCourses){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
