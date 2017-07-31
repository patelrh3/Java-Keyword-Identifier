/*
 * Rishabh Patel
 * Project 5 - Java Keyword Identifier
 * CMSC 256 - 001
 * Due Date: 04/29/17
 * Class Description - Class created by Debra Duke, used as a reference to create a level-order tree. Two methods for printing the 
 * tree traversal were added
 */


import java.util.HashMap;
import java.util.*;

    // BinarySearchTree class
    //
    // CONSTRUCTION: with no initializer
    //
    // ******************PUBLIC OPERATIONS*********************
    // void insert( x )       --> Insert x
    // void remove( x )       --> Remove x (unimplemented)
    // T find( x )            --> Return item that matches x
    // T findMin( )           --> Return smallest item
    // T findMax( )           --> Return largest item
    // boolean isEmpty( )     --> Return true if empty; else false
    // void makeEmpty( )      --> Remove all items
    // void printTree( )      --> Print tree in sorted order

    /**
     * Implements an AVL tree.
     * Note that all "matching" is based on the compareTo method.
     * @author Mark Allen Weiss
     */
    public class AVLTree<T extends Comparable<? super T>>{
        /**
         * Construct the tree.
         */
        public AVLTree( ){
            root = null;
        }

        /**
         * Insert into the tree; duplicates are ignored.
         * @param x the item to insert.
         */
        public void insert(T x) {
            root = insert( x, root );
        }

        /**
         * Remove from the tree. Nothing is done if x is not found.
         * @param x the item to remove.
         */
        public void remove(T x) {
            throw new UnsupportedOperationException( "remove not unimplemented" );
        }

        /**
         * Find the smallest item in the tree.
         * @return smallest item or null if empty.
         */
        public T findMin( ) {
            return elementAt(findMin(root));
        }

        /**
         * Find the largest item in the tree.
         * @return the largest item of null if empty.
         */
        public T findMax( ) {
            return elementAt(findMax(root));
        }

        /**
         * Find an item in the tree.
         * @param x the item to search for.
         * @return the matching item or null if not found.
         */
        public T find(T x) {
            return elementAt(find( x, root));
        }

        /**
         * Make the tree logically empty.
         */
        public void makeEmpty( ) {
            root = null;
        }

        /**
         * Test if the tree is logically empty.
         * @return true if empty, false otherwise.
         */
        public boolean isEmpty( ) {
            return root == null;
        }

        /**
         * Print the tree contents in sorted order.
         */
        public void printTree( ) {
            if(isEmpty())
                System.out.println( "Empty tree" );
            else
                printTree( root );
        }

         
        /**
         * Internal method to get element field.
         * @param t the node.
         * @return the element field or null if t is null.
         */
        private T  elementAt(AVLNode<T> t) {
            return t == null ? null : t.element;
        }

        /**
         * Internal method to insert into a subtree.
         * @param x the item to insert.
         * @param t the node that roots the tree.
         * @return the new root.
         */
        private AVLNode<T> insert(T x, AVLNode<T> t) {
            if( t == null )
                t = new AVLNode<T>( x, null, null );
            else if( x.compareTo( t.element ) < 0 ) {
                t.left = insert( x, t.left );
                if( height( t.left ) - height( t.right ) == 2 )
                    if( x.compareTo( t.left.element ) < 0 )
                        t = rotateWithLeftChild( t );
                    else
                        t = doubleWithLeftChild( t );
            }
            else if( x.compareTo( t.element ) > 0 ) {
                t.right = insert( x, t.right );
                if( height( t.right ) - height( t.left ) == 2 )
                    if( x.compareTo( t.right.element ) > 0 )
                        t = rotateWithRightChild( t );
                    else
                        t = doubleWithRightChild( t );
            }
            else
                ;  // Duplicate; do nothing
            t.height = max( height( t.left ), height( t.right ) ) + 1;
            return t;
        }

        /**
         * Internal method to find the largest item in a subtree.
         * @param t the node that roots the tree.
         * @return node containing the largest item.
         */
        private AVLNode<T> findMax(AVLNode<T> t){
            if( t == null ) return t;

            while( t.right != null )
                t = t.right;
            return t;
        }
        
        /**
         * Internal method to find the smallest item in a subtree.
         * @param t the node that roots the tree.
         * @return node containing the smallest item.
         */
        private AVLNode<T> findMin(AVLNode<T> t){
            if( t == null ) return t;

            while( t.left != null )
                t = t.left;
            return t;
        }

        /**
         * Internal method to find an item in a subtree.
         * @param x is item to search for.
         * @param t the node that roots the tree.
         * @return node containing the matched item.
         */
        private  AVLNode<T> find( T x, AVLNode<T> t) {
            while( t != null )
                if( x.compareTo( t.element ) < 0 )
                    t = t.left;
                else if( x.compareTo( t.element ) > 0 )
                    t = t.right;
                else
                    return t;    // Match

            return null;   // No match
        }

        /**
         * Internal method to print a subtree in sorted order.
         * @param t the node that roots the tree.
         */
        private void printTree( AVLNode t ) {
            if( t != null ) {
            	System.out.println( t.element + ","+ t.height);
            	printTree( t.left );
                printTree( t.right );
            }
        }
        
        /**
         * Print a subtree in level order traversal .
         */
        public void printTreeTraversal()
        {
            printTreeTraversalOrder(root); 
        }
        /**
         * Internal method to print a subtree in level order traversal .
         * @param t the node to print.
         * @param level the level of the node to print.
         */
        private void printTreeTraversalOrder(AVLNode startNode)
        {
        	Queue<AVLNode> currentLevel = new LinkedList<AVLNode>();
        	Queue<AVLNode> nextLevel = new LinkedList<AVLNode>();
        	
        	int depth = 0;
        	currentLevel.add(startNode);
        	
        	while(!currentLevel.isEmpty())
        	{
        		Iterator<AVLNode> iter = currentLevel.iterator();
        		while(iter.hasNext())
        		{
        			AVLNode currentNode = iter.next();
        			if(currentNode.left != null)
            		{
            			nextLevel.add(currentNode.left);
            		}
        			if(currentNode.right != null)
        			{
        				nextLevel.add(currentNode.right);
        			}
        			System.out.print(currentNode.element + " ");
        		}
        		depth++;
        		System.out.println();
        		currentLevel = nextLevel;
        		nextLevel = new LinkedList<AVLNode>();
        	}
        	System.out.println("\nThe depth of the last node is " + depth);

        }
        
        
        /**
         * Return the height of node t, or -1, if null.
         */
        private int height( AVLNode t ) {
            return t == null ? -1 : t.height;
        }

        /**
         * Return maximum of lhs and rhs.
         */
        private int max( int lhs, int rhs ) {
            return lhs > rhs ? lhs : rhs;
        }

        /**
         * Rotate binary tree node with left child.
         * For AVL trees, this is a single rotation for case 1.
         * Update heights, then return new root.
         */
        private  AVLNode<T> rotateWithLeftChild( AVLNode<T> k2 ) {
            AVLNode<T> k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
            k1.height = max( height( k1.left ), k2.height ) + 1;
            return k1;
        }

        /**
         * Rotate binary tree node with right child.
         * For AVL trees, this is a single rotation for case 4.
         * Update heights, then return new root.
         */
        private AVLNode<T> rotateWithRightChild( AVLNode<T> k1 ) {
            AVLNode<T> k2 = k1.right;
            k1.right = k2.left;
            k2.left = k1;
            k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
            k2.height = max( height( k2.right ), k1.height ) + 1;
            return k2;
        }

        /**
         * Double rotate binary tree node: first left child
         * with its right child; then node k3 with new left child.
         * For AVL trees, this is a double rotation for case 2.
         * Update heights, then return new root.
         */
        private AVLNode<T> doubleWithLeftChild( AVLNode<T> k3 ) {
            k3.left = rotateWithRightChild( k3.left );
            return rotateWithLeftChild( k3 );
        }

        /**
         * Double rotate binary tree node: first right child
         * with its left child; then node k1 with new right child.
         * For AVL trees, this is a double rotation for case 3.
         * Update heights, then return new root.
         */
        private AVLNode<T> doubleWithRightChild( AVLNode<T> k1 ) {
            k1.right = rotateWithLeftChild( k1.right );
            return rotateWithRightChild( k1 );
        }

          /** The tree root. */
        private AVLNode<T> root;
}