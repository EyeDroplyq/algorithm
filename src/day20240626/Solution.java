package day20240626;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-26 09:14
 * @version: 1.0
 */
public class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> ans = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                ans.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(ans);
        }
        return res;
    }

    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return res;
    }

    // 返回以root为根的二叉树的最大深度
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        res = Math.max(res, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 子树是不是对称的
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        // base case
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        return check(left.right, right.left) & check(left.left, right.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) & isSameTree(p.right, q.right);
    }

//    public TreeNode invertTree(TreeNode root) {
//        if (root == null) {
//            return root;
//        }
//        TreeNode left = invertTree(root.left);
//        TreeNode right = invertTree(root.right);
//        root.left = right;
//        root.right = left;
//        return root;
//    }

    public TreeNode invertTree(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        traverse(root.left);
        traverse(root.right);
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n];
        int[] res = new int[n];
        if (bookings.length > 0) {
            for (int[] booking : bookings) {
                int start = booking[0];
                int end = booking[1];
                int val = booking[2];
                if (start < n) {
                    diff[start] += val;
                }
                if (end + 1 < n) {
                    diff[end + 1] -= val;
                }
            }
        }
        res[0] = diff[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001];
        int[] res = new int[diff.length];
        for (int[] trip : trips) {
            int num = trip[0];
            int start = trip[1];
            // 因为trip[2]中存放的是终点，到这里的时候已经下车了
            int end = trip[2] - 1;
            diff[start] += num;
            if (end + 1 < diff.length) {
                diff[end + 1] -= num;
            }
        }
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            int val = res[i - 1] + diff[i];
            res[i] = val;
        }
        for (int i = 0; i < res.length; i++) {
            if (res[i] > capacity) {
                return false;
            }
        }
        return true;
    }

//    public class Singleton {
//        private static volatile Singleton uniqueInstance;
//
//        public Singleton() {
//
//        }
//
//        public static Singleton getUniqueInstance() {
//            if (uniqueInstance == null) {
//                synchronized (Singleton.class) {
//                    if (uniqueInstance == null) {
//                        uniqueInstance = new Singleton();
//                    }
//                }
//            }
//            return uniqueInstance;
//        }
//    }

}
