package org.insa.graphs.algorithm.utils;

import java.util.ArrayList;

/**
 * Implements a binary heap containing elements of type E.
 *
 * Note that all comparisons are based on the compareTo method, hence E must
 * implement Comparable
 * 
 * @author Mark Allen Weiss
 * @author DLB
 */
public class BinaryHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    // Number of elements in heap.
    private int currentSize;

    // The heap array.
    protected final ArrayList<E> array;

    /**
     * Construct a new empty binary heap.
     */
    public BinaryHeap() {
        this.currentSize = 0;
        this.array = new ArrayList<E>();
    }

    /**
     * Construct a copy of the given heap.
     * 
     * @param heap Binary heap to copy.
     */
    public BinaryHeap(BinaryHeap<E> heap) {
        this.currentSize = heap.currentSize;
        this.array = new ArrayList<E>(heap.array);
    }

    /**
     * Set an element at the given index.
     * 
     * @param index Index at which the element should be set.
     * @param value Element to set.
     */
    private void arraySet(int index, E value) {
        if (index == this.array.size()) {
            this.array.add(value);
        }
        else {
            this.array.set(index, value);
        }
    }

    /**
     * @return Index of the parent of the given index.
     */
    protected int indexParent(int index) {
        return (index - 1) / 2;
    }

    /**
     * @return Index of the left child of the given index.
     */
    protected int indexLeft(int index) {
        return index * 2 + 1;
    }

    /**
     * Internal method to percolate up in the heap.  
     * 
     * @param index Index at which the percolate begins.
     */
    private void percolateUp(int index) {   //reorganiser les element devant de index vers le haut
        E x = this.array.get(index);

        for (; index > 0
                && x.compareTo(this.array.get(indexParent(index))) < 0; index = indexParent(
                        index)) {
            E moving_val = this.array.get(indexParent(index));
            this.arraySet(index, moving_val);
        }

        this.arraySet(index, x);
    }

    /**
     * Internal method to percolate down in the heap.
     * 
     * @param index Index at which the percolate begins.
     */
    private void percolateDown(int index) { //reorganiser les element apres de index vers le bas
        int ileft = indexLeft(index);
        int iright = ileft + 1;

        if (ileft < this.currentSize) {
            E current = this.array.get(index);  //recuprere element d'indice index de list
            E left = this.array.get(ileft);
            boolean hasRight = iright < this.currentSize;
            E right = (hasRight) ? this.array.get(iright) : null;


            if (!hasRight || left.compareTo(right) < 0) {
                // Left is smaller
                if (left.compareTo(current) < 0) {
                    this.arraySet(index, left);
                    this.arraySet(ileft, current);
                    this.percolateDown(ileft);
                }
            }
            else {
                // Right is smaller
                if (right.compareTo(current) < 0) {
                    this.arraySet(index, right);
                    this.arraySet(iright, current);
                    this.percolateDown(iright);
                }
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    @Override
    public int size() {
        return this.currentSize;    //this.currentSize retourne la taille de tas
    }

    @Override
    public void insert(E x) {
        int index = this.currentSize++;
        this.arraySet(index, x);
        this.percolateUp(index);
    }
    /*
     * tas init:[6, 8, 4, 9, 10, 15, 25]    remove('9')
     * position=3
     * position_dernier=6 ; dernier_element='25' ; remplace '9' par '25' => [6, 8, 4, 25, 10, 15,25]
     * this.array.remove(this.currentSize);   ==>  [6, 8, 4, 25, 10, 15 ]
     * ensuite re-organiser  ordre de tas
     */
    @Override
    public void remove(E x) throws ElementNotFoundException {
         int position;
         int position_dernier;
         E dernier_element;
        if(this.isEmpty()){
            throw new ElementNotFoundException(x);
        }
        else{
            position=this.array.indexOf(x);  //retourner l'indice de x dans tas(list)
            //trouve 'x' dans tas
            if(position>=0 && position<this.currentSize ) {
                //if(position_dernier=position)
                position_dernier=--this.currentSize;       //position_dernier = this.currentSize - 1
                if(position_dernier>position){
                    dernier_element=this.array.get(position_dernier);
                    this.array.set(position, dernier_element);//remplacer element d'indice "position" par "dernier_element"."x" n'exite plus alors
                  //this.array.remove(this.currentSize);    //supprimer e dernier element de tas
                    this.percolateDown(position);
                    this.percolateUp(position);
                }
            }
            //par trouve dans le tas
            else{   
                throw new ElementNotFoundException(x);
            }

        }
    }

    @Override
    public E findMin() throws EmptyPriorityQueueException {
        if (isEmpty())
            throw new EmptyPriorityQueueException();
        return this.array.get(0);
    }

    @Override
    public E deleteMin() throws EmptyPriorityQueueException {
        E minItem = findMin();
        E lastItem = this.array.get(--this.currentSize);
        this.arraySet(0, lastItem); //remplacer
        this.percolateDown(0);
        return minItem;
    }

    /**
     * Creates a multi-lines string representing a sorted view of this binary heap.
     * 
     * @return a string containing a sorted view this binary heap.
     */
    public String toStringSorted() {
        return BinaryHeapFormatter.toStringSorted(this, -1);
    }

    /**
     * Creates a multi-lines string representing a sorted view of this binary heap.
     * 
     * @param maxElement Maximum number of elements to display. or {@code -1} to
     *                   display all the elements.
     * 
     * @return a string containing a sorted view this binary heap.
     */
    public String toStringSorted(int maxElement) {
        return BinaryHeapFormatter.toStringSorted(this, maxElement);
    }

    /**
     * Creates a multi-lines string representing a tree view of this binary heap.
     * 
     * @return a string containing a tree view of this binary heap.
     */
    public String toStringTree() {
        return BinaryHeapFormatter.toStringTree(this, Integer.MAX_VALUE);
    }

    /**
     * Creates a multi-lines string representing a tree view of this binary heap.
     * 
     * @param maxDepth Maximum depth of the tree to display.
     * 
     * @return a string containing a tree view of this binary heap.
     */
    public String toStringTree(int maxDepth) {
        return BinaryHeapFormatter.toStringTree(this, maxDepth);
    }

    @Override
    public String toString() {
        return BinaryHeapFormatter.toStringTree(this, 8);
    }

}
