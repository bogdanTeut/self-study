package tree;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by bogdan.teut on 03/12/2014.
 */
public class BinarySearchTreeTest {

    @Test
    public void insertRootElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        binarySearchTree.dfs(binarySearchTree.root);
    }

    @Test
    public void insertLeftElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(11, binarySearchTree.root.left.value.intValue());
        binarySearchTree.dfs(binarySearchTree.root);
    }

    @Test
    public void insertRightElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(35);
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(35, binarySearchTree.root.right.value.intValue());
        binarySearchTree.dfs(binarySearchTree.root);
    }

    @Test
    public void insertLeftLeftElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        binarySearchTree.insert(7);
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(11, binarySearchTree.root.left.value.intValue());
        Assert.assertEquals(7, binarySearchTree.root.left.left.value.intValue());
        binarySearchTree.dfs(binarySearchTree.root);
    }

    @Test
    public void insertLeftRightElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        binarySearchTree.insert(16);
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(11, binarySearchTree.root.left.value.intValue());
        Assert.assertEquals(16, binarySearchTree.root.left.right.value.intValue());
        binarySearchTree.dfs(binarySearchTree.root);
    }

    @Test
    public void insertRightLeftElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(35);
        binarySearchTree.insert(23);
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(35, binarySearchTree.root.right.value.intValue());
        Assert.assertEquals(23, binarySearchTree.root.right.left.value.intValue());
        binarySearchTree.dfs(binarySearchTree.root);
    }

    @Test
    public void insertLeftRightLeftElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        binarySearchTree.insert(16);
        binarySearchTree.insert(13);
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(11, binarySearchTree.root.left.value.intValue());
        Assert.assertEquals(16, binarySearchTree.root.left.right.value.intValue());
        Assert.assertEquals(13, binarySearchTree.root.left.right.left.value.intValue());
        binarySearchTree.dfs(binarySearchTree.root);
    }

    @Test
    public void insertCompleteTest(){
        BinarySearchTree<Integer> binarySearchTree = getCompleteBinarySearchTree();
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(11, binarySearchTree.root.left.value.intValue());
        Assert.assertEquals(7, binarySearchTree.root.left.left.value.intValue());
        Assert.assertEquals(16, binarySearchTree.root.left.right.value.intValue());
        Assert.assertEquals(13, binarySearchTree.root.left.right.left.value.intValue());
        Assert.assertEquals(17, binarySearchTree.root.left.right.right.value.intValue());
        Assert.assertEquals(35, binarySearchTree.root.right.value.intValue());
        Assert.assertEquals(23, binarySearchTree.root.right.left.value.intValue());
        binarySearchTree.dfs(binarySearchTree.root);
    }

    @Test
    public void findRoot(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        Assert.assertEquals(19, binarySearchTree.find(19).value.intValue());
    }

    @Test
    public void findLeftElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        Assert.assertEquals(11, binarySearchTree.find(11).value.intValue());
    }

    @Test
    public void findRightElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(35);
        Assert.assertEquals(35, binarySearchTree.find(35).value.intValue());
    }

    @Test
    public void findCompleteTest(){
        BinarySearchTree<Integer> binarySearchTree = getCompleteBinarySearchTree();
        Assert.assertEquals(35, binarySearchTree.find(35).value.intValue());
        Assert.assertEquals(16, binarySearchTree.find(16).value.intValue());
        Assert.assertEquals(17, binarySearchTree.find(17).value.intValue());
    }

    @Test
    public void elementNotFound(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(35);
        Assert.assertNull(binarySearchTree.find(36));
    }

    @Test
    public void deleteTheOneElement() {
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.delete (19);
        Assert.assertNull(binarySearchTree.find(19));
    }

    @Test
    public void deleteLeftLeaf() {
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        binarySearchTree.delete (11);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNull(binarySearchTree.find(11));
    }

    @Test
    public void deleteRightLeaf() {
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(35);
        binarySearchTree.delete(35);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNull(binarySearchTree.find(35));
    }

    @Test
    public void deleteLeftLeafOfDepthTwoTree() {
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        binarySearchTree.insert(7);
        binarySearchTree.delete (7);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNotNull(binarySearchTree.find(11));
        Assert.assertNull(binarySearchTree.find(7));
    }

    @Test
    public void deleteLeftRightOfDepthTwoTree() {
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        binarySearchTree.insert(7);
        binarySearchTree.insert(16);
        binarySearchTree.delete (16);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNotNull(binarySearchTree.find(11));
        Assert.assertNotNull(binarySearchTree.find(7));
        Assert.assertNull(binarySearchTree.find(16));
    }

    @Test
    public void deleteRightLeftOfDepthTwoTree() {
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(35);
        binarySearchTree.insert(23);
        binarySearchTree.delete (23);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNotNull(binarySearchTree.find(35));
        Assert.assertNull(binarySearchTree.find(23));
    }

    @Test
    public void deleteElementWithOneChildLeft() {
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(35);
        binarySearchTree.insert(23);
        binarySearchTree.delete (35);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNotNull(binarySearchTree.find(23));
        Assert.assertNull(binarySearchTree.find(35));

        binarySearchTree.insert(11);
        binarySearchTree.insert(7);
        binarySearchTree.delete (11);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNotNull(binarySearchTree.find(7));
        Assert.assertNull(binarySearchTree.find(11));

    }

    @Test
    public void deleteElementWithOneChildRight() {
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(35);
        binarySearchTree.insert(40);
        binarySearchTree.delete (35);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNull(binarySearchTree.find(35));
        Assert.assertNotNull(binarySearchTree.find(40));

        binarySearchTree.insert(11);
        binarySearchTree.insert(16);
        binarySearchTree.delete (11);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNull(binarySearchTree.find(11));
        Assert.assertNotNull(binarySearchTree.find(16));
    }

    @Test
    public void deleteElementWithOneChild() {
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(35);
        binarySearchTree.insert(23);
        binarySearchTree.insert(40);
        binarySearchTree.insert(38);
        binarySearchTree.delete (40);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNotNull(binarySearchTree.find(23));
        Assert.assertNotNull(binarySearchTree.find(38));
        Assert.assertNull(binarySearchTree.find(40));

        binarySearchTree.insert(11);
        binarySearchTree.insert(7);
        binarySearchTree.insert(9);
        binarySearchTree.insert(16);
        binarySearchTree.delete (7);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNotNull(binarySearchTree.find(11));
        Assert.assertNotNull(binarySearchTree.find(9));
        Assert.assertNotNull(binarySearchTree.find(16));
        Assert.assertNull(binarySearchTree.find(7));
    }

    @Test
    public void deleteTwoChildrenNode() {
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        binarySearchTree.insert(7);
        binarySearchTree.insert(16);
        binarySearchTree.insert(14);
        binarySearchTree.delete (11);
        Assert.assertNotNull(binarySearchTree.find(19));
        Assert.assertNull(binarySearchTree.find(11));
        Assert.assertNotNull(binarySearchTree.find(7));
        Assert.assertNotNull(binarySearchTree.find(16));
    }


    private BinarySearchTree<Integer> getCompleteBinarySearchTree() {
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        binarySearchTree.insert(7);
        binarySearchTree.insert(16);
        binarySearchTree.insert(13);
        binarySearchTree.insert(17);
        binarySearchTree.insert(35);
        binarySearchTree.insert(23);
        return binarySearchTree;
    }

    private BinarySearchTree<Integer> getBinarySearchTreeWithRootElement() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
        binarySearchTree.insert(19);
        return binarySearchTree;
    }
}
