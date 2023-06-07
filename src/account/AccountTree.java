package account;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AccountTree {
    private TreeNode root;

    public void insert(AccountComponent accountComponent, int parentIndex) {
        if (root == null) {
            root = new TreeNode(accountComponent);
            return;
        }

        TreeNode parentNode = findNode(root, parentIndex);
        if (parentNode == null) {
            System.out.println("Parent node not found.");
            return;
        }

        TreeNode newNode = new TreeNode(accountComponent);
        parentNode.addChildren(newNode);
    }

    private TreeNode findNode(TreeNode node, int parentIndex) {
        if (node.getAccountComponent().getId() == parentIndex) {
            return node;
        }

        for (TreeNode child : node.getChildren()) {
            TreeNode foundNode = findNode(child, parentIndex);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }

    public void printBreadthFirst() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.getAccountComponent().toString() + " ");

            for (TreeNode child : node.getChildren()) {
                queue.add(child);
            }
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public List<AccountComponent> getChildrenOf(int accountId) {
        TreeNode node = findNode(root, accountId);
        if (node != null) {
            List<AccountComponent> children = new ArrayList<>();
            for (TreeNode child : node.getChildren()) {
                children.add(child.getAccountComponent());
            }
            return children;
        }
        return null;
    }
}







