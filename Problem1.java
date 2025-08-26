// Design Phone Directory (https://leetcode.com/problems/design-phone-directory/)


// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// Use a Queue (q) to store available numbers in order.
// Use a HashSet (set) to quickly check whether a number is still available.

//Initialization:
//Fill both q and set with all numbers from 0 to maxNumbers - 1.
//get()
//If no numbers are available, return -1.
//Otherwise, remove one from q, delete it from set, and return it.

//check(number)
//Simply check if the number exists in set.

//release(number)
//If the number is not already in set, add it back to both q and set.

class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.set= new HashSet<>();
        for(int i=0;i<maxNumbers;i++){
            q.add(i);
            set.add(i);
        }
    }
    
    public int get() {
        if(q.isEmpty()) return -1;
        int result = q.poll();
        set.remove(result);
        return result;
    }
    
    public boolean check(int number) {
        if(set.contains(number)) return true;
        return false;
    }
    
    public void release(int number) {
        if(!set.contains(number)){
            set.add(number);
            q.add(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */


