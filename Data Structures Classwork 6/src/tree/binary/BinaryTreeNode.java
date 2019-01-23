package tree.binary;

public class BinaryTreeNode {

	private String data;
	private BinaryTreeNode left, right;

	BinaryTreeNode(String s) {
		data = s;
		left = null;
		right = null;
	}

	public void add(String s, String child) {
		if (child.equals("L")) {
			left = new BinaryTreeNode(s);
		} else if (child.equals("R")) {
			right = new BinaryTreeNode(s);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public BinaryTreeNode getChild(String child) {
		return (child.equals("L")) ? left : right;
	}

	public String getString() {
		return data;
	}

	public void doubleTree() {
		if (left == null) {
			left = new BinaryTreeNode(data);
			return;
		}
		BinaryTreeNode temp = left;
		left = new BinaryTreeNode(data);
		left.left = temp;
		left.left.doubleTree();
		if (right != null) {
			right.doubleTree();
		}
	}

	public void print() {
		if (left != null) {
			left.print();
		}
		if (right != null) {
			right.print();
		}
		System.out.print(data + ",");
	}

	public static boolean sameTree(BinaryTreeNode n1, BinaryTreeNode n2) {
        if(!n1.data.equals(n2.data)) {
            return false;
        }
        boolean sameTree = true;
        if(n1.left != null && n2.left != null) {
            sameTree = sameTree(n1.left,n2.left);
        }else if(n1.left != n2.left){
            sameTree = false;
        }
        if(n1.right != null && n2.right !=null && sameTree) {
            return sameTree(n1.right,n2.right);
        }else if(n1.right != n2.right){
            sameTree = false;
        }
        return sameTree;   
    }
    public static void main(String[] args) {
        BinaryTreeNode tree = new BinaryTreeNode("a");
        tree.add("b", "L");
        tree.add("c", "R"); 
        tree.print();
        System.out.println();
        BinaryTreeNode tree1 = new BinaryTreeNode("a");
        tree1.add("b", "L");
        tree1.add("c", "R"); 
        System.out.println(sameTree(tree,tree1));
        System.out.println(sameTree(tree1,tree));
        tree1.getChild("L").add("i", "R");
        System.out.println(sameTree(tree1,tree));
        tree.getChild("L").add("i", "R");
        System.out.println(sameTree(tree1,tree));
        tree1.print();
        System.out.println();
        tree.print();
        System.out.println();
        tree.doubleTree();
        tree.print();
    }

}