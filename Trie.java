public class Trie {

    // Alphabet size (# of symbols)
    static final int SYMBOL_SIZE = 16; // hexadecimal

    // trie node
    static class TrieNode {
        TrieNode[] children = new TrieNode[SYMBOL_SIZE];
        int nodeIndex;

        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < SYMBOL_SIZE; i++)
                children[i] = null;
            nodeIndex = -1;
        }
    };

    static TrieNode root;

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node
    static void encode(String hexString) {
        TrieNode currentNode = root;
        int nodeCount = -1;
        int decimalVal;
        int parentIndex = -1;

        for (int i = 0; i < hexString.length(); i ++) {
            // Extract the next hex symbol and convert it to int. 
            int index = Integer.parseInt(hexString.substring(i, i + 1), 16);

            //if root doesnt have symbol as a child, add it
            if(currentNode.children[index] == null){

                //keep track of the parent nodes index in the trie.
                parentIndex = currentNode.nodeIndex;

                //insert symbol as child of current trie
                currentNode.children[index] = new TrieNode();
                nodeCount++;
                currentNode.children[index].nodeIndex = nodeCount;
                //get decimal value of the hex symbol to print
                // decimalVal = Integer.parseInt(String.valueOf(index), 16);
                decimalVal = index;
                // Output the integer pair
                System.out.println(parentIndex + " " + decimalVal);

                //mark it as the end of a word
                currentNode = currentNode.children[index];
                currentNode.isEndOfWord = true;

                //go back to begginning of Trie for the next word to insert.
                currentNode = root;
                parentIndex = currentNode.nodeIndex;
                
            }
            else{
                //traverse the trie and get the next symbol to insert
                currentNode = currentNode.children[index];

            }
        }
    
        // Mark the very last node as the end of a word
        currentNode.isEndOfWord = true;
        // get decimal value of the hex symbol to print
        decimalVal = Integer.parseInt(String.valueOf(currentNode.nodeIndex), 16);
        // Output the integer pair. "-1" is end 
        System.out.println(decimalVal + " " + "-1");
        

    }

    // Returns true if key is in trie
    static boolean search(String key) {
        int level;
        int length = key.length();
        int index;
        TrieNode currentNode = root;

        for (level = 0; level < length; level++) {
            index = Character.digit(key.charAt(level), 16);

            if (currentNode.children[index] == null)
                return false;

            currentNode = currentNode.children[index];
        }

        return (currentNode.isEndOfWord);
    }


    public static void main(String args[]) {
        // Input key (use only 0-9 and a-f)
        String key = "61626162636261626161";

        String output[] = { "Not present in trie", "Present in trie" };

        root = new TrieNode();
        encode(key);


        // Search for different keys
        // if (search("6")) // represents "ab"
        //     System.out.println("6 --- " + output[1]);
        // else
        //     System.out.println("6 --- " + output[0]);
        //     if (search("1")) // represents "ab"
        //     System.out.println("1 --- " + output[1]);
        // else
        //     System.out.println("1 --- " + output[0]);
        //     if (search("62")) // represents "ab"
        //     System.out.println("62 --- " + output[1]);
        // else
        //     System.out.println("62 --- " + output[0]);
        //     if (search("61")) // represents "ab"
        //     System.out.println("61 --- " + output[1]);
        // else
        //     System.out.println("61 --- " + output[0]);
        //     if (search("626")) // represents "ab"
        //     System.out.println("626 --- " + output[1]);
        // else
        //     System.out.println("626 --- " + output[0]);

        // if (search("62616")) // represents "abc"
        //     System.out.println("62616 --- " + output[1]);
        // else
        //     System.out.println("62616 --- " + output[0]);

        // if (search("3")) // represents "ba"
        //     System.out.println("3 --- " + output[1]);
        // else
        //     System.out.println("3 --- " + output[0]);

        // if (search("6261")) // represents "cb"
        //     System.out.println("6261 --- " + output[1]);
        // else
        //     System.out.println("6261 --- " + output[0]);

        //     if (search("26")) // represents "ab"
        //     System.out.println("26 --- " + output[1]);
        // else
        //     System.out.println("26 --- " + output[0]);

        //     if (search("36")) // represents "ab"
        //     System.out.println("36 --- " + output[1]);
        // else
        //     System.out.println("36 --- " + output[0]);

    }
}
