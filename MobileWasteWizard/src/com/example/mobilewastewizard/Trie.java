/** Name: Burhan Qadri, Justin Li, William Granados
 *  Date: 31/03/16
 *  Purpose: Checks if a string is contained within the a trie data structure
 * */

package com.example.mobilewastewizard;
import java.util.HashMap;

/** A simple Trie data structure*/
public class Trie 
{
	private Node root;
	
	public Trie()
	{
		// the character for the root node is really just filler, it's only
		// useful when debugging
		this.root = new Node('@', false);
	}
	
	/** Inserts the following string into the Trie data structure.
	 *  The complexity is O(|W|) where W is the number of characters
	 *  in str.
	 * @param str the string that is to be inserted
	 */
	public void insert(String str)
	{
		// start off at the first index in str from the root
		this.insert(root, str, 0);
	}

	/** Inserts the following string into the Trie data structure.
	 *  The complexity is O(|W|) where W is the number of characters in str.
	 * @param current the current node we are visiting
	 * @param str the string that is to be inserted
	 * @param it the current index that we are inserting into the ADT
	 */
	private void insert(Node current, String str, int it)
	{
		if(!current.contains(str.charAt(it)))
		{
		    Node nextCharacter;
			// so this character is the end of the string
		    if(it == str.length()-1)
			{
		    	nextCharacter = new Node(str.charAt(it), true);
			}
			else
			{
				nextCharacter = new Node(str.charAt(it), false);
			}
			current.setNextCharacter(nextCharacter);
			// don't recurse beyond the end of the string	
			if(it+1 != str.length())
			{
				insert(nextCharacter, str, it+1);
			}
		}
		else
		{
			// don't recurse beyond the end of the string	
			if(it+1 != str.length())
			{
				Node nextCharacter = current.getNextCharacter(str.charAt(it));
				insert(nextCharacter, str, it+1);
			}
		}
	}

	/** Checks if the the word is contained within this Trie
	 * 	The complexity is O(|W|) where W is the number of characters in str.
	 *  @param str the string that is to be inserted
	 */
	public boolean contains(String str)
	{
		// start off at the first index from the root
		return this.contains(this.root, str, 0);
	}

	/** Checks if the the word is contained within this Trie
	 *  The complexity is O(|W|) where W is the number of characters in str. 
	 * @param current the current node we are visiting
	 * @param str the string that is to be inserted
	 * @param it the current index that we are inserting into the ADT
	 */
	private boolean contains(Node current, String str, int it)
	{
		// check if the current character is contained within the children
		// of the current node
		if(current.contains(str.charAt(it)))
		{
		    Node nextCharacter = current.getNextCharacter(str.charAt(it));
		    // Check if the character we are currently at matches the 
		    // characteristics of the string we are checking.
		    if(it == str.length()-1 
		       && nextCharacter.getCharacter() == str.charAt(it)
		       && nextCharacter.isEndOfWord())
		    {
		    	return true;
		    }
		    // don't recurse beyond the end of the string	
		    else if(it+1 != str.length())
			{
				return contains(nextCharacter, str, it+1);
			}
		    // we have reached the last character of the string and there
		    // was not a valid match
		    else
		    {
		    	return false;
		    }
		}
		// nothing could be matched with the current node's children
		else
		{
			return false;
		}
	}
}

/** Generic node class to be used in the Trie data structure*/
class Node
{
	private char character;
	private boolean isEndOfWord;
	private HashMap<Character, Node>nextCharacter;
	
	public Node(char character, boolean isEndOfWord) 
	{
		this.setCharacter(character);
		this.setEndOfWord(isEndOfWord);
		this.nextCharacter = new HashMap<Character, Node>();
	}
	// getters and setters
	
	/** Returns the character at this node*/
	public char getCharacter() 
	{
		return character;
	}

	/** Sets the character at this node
	 *  @param charactter new character at this node*/
	private void setCharacter(char character) 
	{
		this.character = character;
	}
	
	/** Returns True if this character is the end of a word*/
	public boolean isEndOfWord() 
	{
		return isEndOfWord;
	}
	
	/** Sets whether or not this node/character is the end of a word
	 *  @param charactter new character at this node*/
	private void setEndOfWord(boolean isEndOfWord) 
	{
		this.isEndOfWord = isEndOfWord;
	}

	/** Returns the character from this node*/
	public Node getNextCharacter(Character character) 
	{
		return nextCharacter.get(character);
	}
	
	/** Returns the character from this node*/
	public HashMap<Character, Node> getNextCharacter() 
	{
		return this.nextCharacter; 
	}

	/** Sets the next character in this node
	 * @param nextCharacter next Node to be inserted
	 * */
	public void setNextCharacter(Node nextCharacter) 
	{
		Character nextChar = nextCharacter.getCharacter();
		this.nextCharacter.put(nextChar, nextCharacter);
	}

	/** Sets the next character in this node
	 * @param nextCharacter next Node to be inserted
	 * */
	public void setNextCharacter(Character nextCharacter, boolean isEndOfWord) 
	{
		Node nextNode = new Node(nextCharacter, isEndOfWord);
		this.nextCharacter.put(nextNode.getCharacter(), nextNode);
	}
	
	/** Checks if there is a connection between the current node and the 
	 *  following character
	 * @param character
	 */
	public boolean contains(Character character)
	{
		return this.nextCharacter.containsKey(character);
	}
}