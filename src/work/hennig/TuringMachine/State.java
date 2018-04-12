package work.hennig.TuringMachine;

public class State {

    private int id;
    private static int index = 0;
    
    public State() {
    	this(index++);
    }
    
    private State(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        if (this.id != ((State)other).id) {
            return false;
        }
        return true;
    }

}
