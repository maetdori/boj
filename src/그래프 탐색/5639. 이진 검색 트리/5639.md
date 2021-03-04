import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Node {
	int val;
	Node left;
	Node right;

	Node(int val) {
		this.val = val;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node root = new Node(Integer.parseInt(br.readLine()));

		String line;
		while((line=br.readLine())!=null && line.length()!=0) {
			Node node = new Node(Integer.parseInt(line));
			insert(root, node);
		}

		postorder(root);
	}

	static void insert(Node parent, Node node) {
		if(node.val < parent.val) {
			if(parent.left != null)
				insert(parent.left, node);
			else parent.left = node;
		}

		if(node.val > parent.val) {
			if(parent.right != null)
				insert(parent.right, node);
			else parent.right = node;
		}
	}

	private static void postorder(Node node) {
		if(node.left != null)
			postorder(node.left);
		if(node.right != null)
			postorder(node.right);
		System.out.println(node.val);
	}
}