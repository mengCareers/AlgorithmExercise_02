package CompanyOriented.Google.Interview;

import CompanyOriented.Google.TreeNode;

/*
input: two trees
output: true if two trees are isomorphic

if one of them can be obtained from other by FLIPS
    FLIPS - swapping left and right children of a number of nodes
    any number of nodes at any level can have theri children swapped



 */
public class TreeIsomorphismProblem {
    boolean isIsomorphic(TreeNode root1, TreeNode root2) {

        /* Base cases. */
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        if (root1.val != root2.val)
            return false;


        return (isIsomorphic(root1.left, root2.left) && isIsomorphic(root1.right, root2.right))
                || (isIsomorphic(root1.left, root2.right) && isIsomorphic(root1.right, root2.left));
    }
}
