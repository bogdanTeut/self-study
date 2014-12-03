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
    }

    @Test
    public void insertLeftElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(11, binarySearchTree.root.left.value.intValue());
    }

    @Test
    public void insertRightElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(35);
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(35, binarySearchTree.root.right.value.intValue());
    }

    @Test
    public void insertLeftLeftElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        binarySearchTree.insert(7);
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(11, binarySearchTree.root.left.value.intValue());
        Assert.assertEquals(7, binarySearchTree.root.left.left.value.intValue());
    }

    @Test
    public void insertLeftRightElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(11);
        binarySearchTree.insert(16);
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(11, binarySearchTree.root.left.value.intValue());
        Assert.assertEquals(16, binarySearchTree.root.left.right.value.intValue());
    }

    @Test
    public void insertRightLeftElement(){
        BinarySearchTree<Integer> binarySearchTree = getBinarySearchTreeWithRootElement();
        binarySearchTree.insert(35);
        binarySearchTree.insert(23);
        Assert.assertEquals(19, binarySearchTree.root.value.intValue());
        Assert.assertEquals(35, binarySearchTree.root.right.value.intValue());
        Assert.assertEquals(23, binarySearchTree.root.right.left.value.intValue());
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
