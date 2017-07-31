/*
 * Rishabh Patel
 * Project 5 - Java Keyword Identifier
 * CMSC 256 - 001
 * Due Date: 04/29/17
 * Class Description - Class created by Debra Duke, used as a reference to help create a level-order tree. Nothing was changed.
 */

// Basic node stored in AVL trees

    class AVLNode<T extends Comparable<? super T>> {
    
        public AVLNode(T theElement)  {
            this( theElement, null, null );
        }

        public AVLNode(T theElement, AVLNode<T> lt, AVLNode<T> rt ) {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
        }

        T element;               
        AVLNode<T> left;      
        AVLNode<T> right;        
        int height;        
    }