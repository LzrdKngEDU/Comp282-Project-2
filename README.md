 # COMP 282 Project 2: Balance Trees
Idea: Binary search trees are a nice idea and frequently work well, but sometimes we
have more stringent requirements for performance.
Requirements: Write a java program that implements the BalanceTree interface for an
AVL tree, Splay tree, and Red Black Tree. The behavior should follow the behavior for
these trees discussed in class.

Your program must have at least one class called Driver which runs the program.
Your program must have the following classes: (1) AVL.java, (2) Splay.java, and (3)
RedBlack.java.
## These classes should have a no argument constructor and implement the following interface.

public interface BalanceTree<E extends Comparable<E>> {
public void insert(E item);//adds an item to the tree
public boolean find(E item);//returns true if item is found
public void delete(E item);//delete an item
public void printInOrder();//prints the items in the tree in ascending order
}

## In addition to implementing the interface, for each of the tree data structures, you will implement the following additional methods:
AVL tree
public void heightAVL()
{
//prints the height of the AVL tree
// e.g., AVL height = 2
}
public void printAVL()
{
//print the items and balance factor in ascending order
//e.g., (5,0)(7,-1)(9,-1)(15,0)
}
Splay tree
public void printRoot()
{
//print the item contained in the root
// the root contains: 7
}
public void printSplay()
{
//prints the items and node height in ascending order
//e.g., (5,0)(7,2)(9,1)(15,0)
}
Red Black tree
public void statusRB()
{
//prints the number of (1)Red nodes, (2)Black nodes, and (3)the black height
//e.g., Red = 1 Black = 3 BlackHeight = 2
}
public void printRedBlack()
{
//prints the items and node color in ascending order
//e.g., (5,B)(7,B)(9,B)(15,R)
}

The group must also have a file called groupstatus.txt that contains your member names
and a short description of the status of your program. This file should be an ascii file.
Though you may create it with MS Word (or notepad/wordpad/jGrasp/etc), you should be
certain that it is a text file.

Submission: Prior to the deadline, one group member uploads your files (java and
groupstatus.txt) to Canvas (class files and data files are neither necessary nor wanted).
Each student uploads an evaluation.txt. I would suggest uploading long before the
deadline and updating/replacing as you go (work on it today and upload, work on it
tomorrow and replace, work on it the next day and replace, . . ..
Cheating: This project is a group project (no more than 4 students per group). You can
discuss this project with other students. You can explain what needs to be done and give
suggestions on how to do it. You can use the web to find ideas. You cannot share source
code with students outside your group or submit solutions written by someone outside of
your group (including code downloaded from the internet).