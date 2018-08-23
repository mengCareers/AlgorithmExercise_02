package 算法竞赛入门经典.TreeDFSTraversalRecursion;

/*
     1
   /  \
  2    3
 /\   /
4  5 6

preorder: the first one must be the root
[1, 2, 4, 5, 3, 6]
inorder: locate root, [0, ri-1] left sub, [ri+1, len-1] right sub
[4, 2, 5, 1, 6, 3]
for [4, 2, 5] len = 3, first 3 of preorder after root will be set for it
 */
public class GivenInorderPreorder {
}
