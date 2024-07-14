
public class Defensive {
    
    public void processList(List<String> list) {
        // Prevent the null data go into process.
        if (list == null || list.isEmpty()) {
            return;
        }
        // process the list
    }
    
}
