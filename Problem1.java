// Time Complexity - O(N) since we traverse all Nodes
// Space Complexity - O(N/2) ~ O(N) since at any level we will have n/2 nodes in the queue
/*
I used a queue to iterate through the tree level by level, starting with the root.
For each level, I captured the current queue size and processed exactly those many nodes to build that level’s list.
While processing each node, I added its left and right children back into the queue and appended the collected level list to the final result.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.tree.TreeNode;

public class Problem1 {
    List<List<Integer>> resDfs;
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if(root == null){
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0;i<size;i++) {
                TreeNode node = q.poll();
                temp.add(node.val);

                if(node.left != null) {
                    q.add(node.left);
                }

                if(node.right != null) {
                    q.add(node.right);
                }
            }

            res.add(temp);
        }

        return res;
    }

    // public List<List<Integer>> levelOrderWithDfs(TreeNode root) {
    //     resDfs = new ArrayList<>();
    //     if(root == null) {
    //         return resDfs;
    //     }
    //     dfs(root, 0);
    //     return resDfs;
    // }

    // private void dfs(TreeNode root, int level) {
    //     if(root == null) {
    //         return;
    //     }

    //     if(level == resDfs.size()) {
    //         resDfs.add(new ArrayList<>());
    //     }

    //     resDfs.get(level).add(root.val);
    //     dfs(root.left, level+1);
    //     dfs(root.right, level+1);
    // }
}