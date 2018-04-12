package work.hennig.TuringMachine;

public class Transition {

    private State oldState;
    private char oldSymbol;
    private State newState;
    private char newSymbol;
    private Direction cursorMovement;
    
    public Transition(State oldState, char oldSymbol, State newState,
            char newSymbol, Direction cursorMovement) {
        this.oldState = oldState;
        this.oldSymbol = oldSymbol;
        this.newState = newState;
        this.newSymbol = newSymbol;
        this.cursorMovement = cursorMovement;
    }
    
    public State getOldState() {
        return this.oldState;
    }
    
    public char getOldSymbol() {
        return this.oldSymbol;
    }
    
    public State getNewState() {
        return this.newState;
    }
    
    public char getNewSymbol() {
        return this.newSymbol;
    }
    
    public Direction getCursorMovement() {
        return this.cursorMovement;
    }
    
    public enum Direction {
        LEFT,
        RIGHT,
        NONE;
    }

}
