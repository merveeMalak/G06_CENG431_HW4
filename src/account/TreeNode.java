package account;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private AccountComponent accountComponent;


    private List<TreeNode> children;

    public TreeNode(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
        children = new ArrayList<>();
    }

    public AccountComponent getAccountComponent(){
        return accountComponent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChildren(TreeNode node){
        children.add(node);
    }
}
