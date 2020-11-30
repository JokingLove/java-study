package com.joking.springbootstart.runtime;

import apple.laf.JRSUIUtils;

public class DeepNodeTest {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
        public static TreeNode of(int i) {
            return new TreeNode(i);
        }

        public int getVal() {
            return val;
        }

        public TreeNode setVal(int val) {
            this.val = val;
            return this;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode setLeft(TreeNode left) {
            this.left = left;
            return this;
        }

        public TreeNode getRight() {
            return right;
        }

        public TreeNode setRight(TreeNode right) {
            this.right = right;
            return this;
        }
    }


    public static void main(String[] args) {
        TreeNode root = TreeNode.of(3);
        root.left = TreeNode.of(9);
        root.right = TreeNode.of(20).setLeft(TreeNode.of(15)).setRight(TreeNode.of(17));

        int i = maxDepth(root);
        System.out.println(i);
    }

    public static int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        } else if(root.left == null && root.right == null) {
            return 1;
        } else {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return 1 + (Math.max(left, right));
        }

    }
}
