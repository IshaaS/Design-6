// Design Autocomplete System (https://leetcode.com/problems/design-search-autocomplete-system/)


// Time Complexity : O(l)
// Space Complexity : O(n*l)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// Each node keeps the top 3 most frequent sentences starting with that prefix,
// and as the user types characters, it returns suggestions;
//when # is entered, the input is stored as a new sentence in the system.

class AutocompleteSystem {
    class TrieNode{
        List<String> startsWith;
        //TrieNode[] children;
        HashMap<Character, TrieNode> children;
        public TrieNode(){
            this.children= new HashMap<>();
            this.startsWith = new ArrayList<>();
        }
    }
    private void insert(String sentence){
        TrieNode curr = root;
        for(int i=0;i<sentence.length();i++){
            char c = sentence.charAt(i);
            if(!curr.children.containsKey(c)) curr.children.put(c, new TrieNode());
            curr=curr.children.get(c);
            List<String> li = curr.startsWith;
            if(!li.contains(sentence))curr.startsWith.add(sentence);
            Collections.sort(li,(a,b)->{
                int cnta= map.get(a);
                int cntb= map.get(b);
                if(cnta==cntb)return a.compareTo(b);
                return cntb-cnta; 
            });
            if(li.size()>3)li.remove(li.size()-1);
        }
    }
    private List<String> search(String prefix){
        TrieNode curr = root;
        for(int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if(!curr.children.containsKey(c)) return new ArrayList<>();
            curr = curr.children.get(c);
        }
        return curr.startsWith;
    }
    private HashMap<String, Integer> map;
    private StringBuilder inputString;
    private TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map=new HashMap<>();
        this.root= new TrieNode();
        this.inputString = new StringBuilder();
        for(int i=0;i<sentences.length;i++){
            String sentence = sentences[i];
            int count = times[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + count);
            insert(sentence);
        }
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            String in = inputString.toString();
            map.put(in, map.getOrDefault(in, 0)+1);
            insert(in);
            inputString = new StringBuilder();
            return new ArrayList<>();
        }
        inputString.append(c);
        String prefix = inputString.toString();
        List<String> startsWith = search(prefix);
        return search(prefix);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
