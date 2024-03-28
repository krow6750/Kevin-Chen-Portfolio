import java.util.ArrayList;
import java.util.Iterator;

//Name repository utilizing an ArrayList
class NameRepositoryType2 implements Iterable<NameRepositoryType2> {
    
    ArrayList<String> namesList = new ArrayList<String>();
    int index;
    
    public NameRepositoryType2() {
        namesList.add("Jane");
        namesList.add("Jinx");
        namesList.add("Jarel");
        namesList.add("Joe");
    }
    
    @Override
    public Iterator<NameRepositoryType2> iterator() {
        return new NameIterator();
    }
    
    //Iterator private class within container
    private class NameIterator implements Iterator<NameRepositoryType2> {
        
        @Override
        public boolean hasNext() {
            return index < namesList.size();
        }
        
        @Override
        public NameRepositoryType2 next() {
            if (this.hasNext()) {
                index++;
                return NameRepositoryType2.this;
            }
            return null;
        }
    }
    
    @Override
    public String toString() {
        return namesList.get(index - 1);
    }
}

public class Main {
    public static void main(String[] args) {
        NameRepositoryType2 namesRepo = new NameRepositoryType2();
        Iterator<NameRepositoryType2> it = namesRepo.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
