//import java.util.function.Function;

public class Main
{
	public static void main(String [] args)
	{		
		
		//Call back from last class
		ListADT<Monster> monsterList = new ListADTImpl<Monster>();
		
		monsterList.addBack(new Zombie("Bob"));
		monsterList.addBack(new Werewolf("Pro. Lupin"));
		monsterList.addBack(new Goblin("Nott"));
		
		for(int x = 0; x<monsterList.getSize(); ++x)
		{
			System.out.println(monsterList.get(x));
		}
		
		
		//1. Review this code file. What's changed to match better design?
		/*
		Main.java from the previous MonsterList is a combination of the "polymorphic monster code" and "linked list code" from a previous class, while the newer version only contains linked list code.

		In the current code file, the ListADT interface and ListADTImpl class are used to implement a linked list of Monster objects. The addBack() method is used to add monsters to the list, and the getSize() and get() methods are used to retrieve information about the list. It also follows good object-oriented design principles, as it separates the linked list functionality from the Monster objects.

		The previous version is more focused on the Monster objects themselves, and less on the linked list implementation. We can conclude that the current code file is modular and easier to maintain.
		/*
		
		//2. Map, filter, fold describe each 
		
		// Map -> Method on iterators that given a mapping function A -> B, turns an Iterator<A> into an Iterator<B>
        // Filter -> Method on iterators that given a predicate function, will include or skip the current element of the Iterator
        // Fold -> Method on iterators meant to aggregate an entire iterator into a single final value
        //         an example of a folding operation could be the summation of an integer iterator 
		
        //3. Implement the filter method to get all zombies (ICE Submission)
        ListADT<Zombie> zombies = monsterList.filter(monster -> monster instanceof Zombie).map(monster -> (Zombie) monster);
		//4. Describe how the filter design is good design. (ICE Submission)
		// Iterators in general are a really nice way to work with collections of values
        // filters make it easy to remove unnecessary elements
		//5. Trace the map function doing whatever research is needed to figure out what it is doing.
		//  For each non empty node that its traversing (as it would stop on an empty node), it transforms the value with the given mapping function and rebuilds a new list
		//6. Test your understanding by doing the following: 
		    //Convert the list to a list of 1's (ICE Submission)
		    ListADT<Integer> ones = monsterList.map(_ignore-> 1);
	    	//Convert the list to a list of 1's and 0's where 1's are zombies and 0's are everything else (ICE Submission)
	    	ListADT<Integer> isZombieBinary = monsterList.map(monster -> monster instanceof Zombie ? 1 : 0);
		    //Convert the monsters to a list of strings (ICE Submission)
		    ListADT<String> strings = monsterList.map(Monster::toString); // We can also directly pass existing functions
		    //Convert the monsters to a list of boolean variables based on if they are alive (ICE Submission)
			ListADT<Boolean> alive = monsterList.map(monster -> monster.isAlive());
	
		//7. There's already a specific fold. Where is it?
		// The toString implementation is implicitly a folding operation
		//8. How would you make this work monsterList.getSize(z -> z instanceof Zombie);(ICE Submission)
		// Just filter before calling getSize()
        // => public int getSize(Predicate<T> predicate) { return filter(predicate).count(); }
		
		/*
		//Example from module in case it is useful
		ListADT<String> stringList = new ListADTImpl<String>();
		
		String sentence = "The quick brown fox jumps over the lazy dog";
		String []words = sentence.split("\\s+");
		
		for (String w:words) 
		{
			stringList.addBack(w);
		}

		ListADT<Integer> wordLengths = stringList.map(s->s.length());
		
		System.out.println(stringList.getSize());
		
		for (int i=0;i<words.length;i++) 
		{
			System.out.println(wordLengths.get(i));
		}
		*/
		
	}
	
}