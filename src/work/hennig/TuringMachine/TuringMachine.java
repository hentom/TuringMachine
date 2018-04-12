package work.hennig.TuringMachine;

import java.util.Iterator;
import java.util.Set;

public class TuringMachine {

    private Set<Transition> transitionRelation;
    private State initialState;
    private Set<State> finalStates;
    
    public TuringMachine(Set<Transition> transitions,
            State initialState, Set<State> finalStates) {
        this.transitionRelation = transitions;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }
    
    public String run() {
        return run("");
    }
    
    public String run(String tapeContent) {
        Tape tape = new Tape(tapeContent);
        State currentState = initialState;
        
        while (!finalStates.contains(currentState)) {
            currentState = executeSingleStep(tape, currentState);
        }
        
        return tape.getContent();
    }
    
    private State executeSingleStep(Tape tape, State state) {
        char currentSymbol = tape.getCurrentSymbol();
        Transition transition;
        for (Iterator<Transition> it = transitionRelation.iterator();
                it.hasNext();) {
            transition = it.next();
            if (!transition.getOldState().equals(state)) {
                continue;
            }
            if (transition.getOldSymbol() != currentSymbol) {
                continue;
            }
            tape.executeTransition(transition);
            return transition.getNewState();
        }
        throw new RuntimeException("no possible transition found");
    }
    
    private class Tape {
        private String content;
        private int position;
        
        public Tape(String content) {
            this.content = content;
            this.position = 0;
        }
        
        public void executeTransition(Transition transition) {
            if (transition.getOldSymbol() != content.charAt(position)) {
                throw new RuntimeException("transition cannot be executed");
            }
            content = content.substring(0, position) +
                    transition.getNewSymbol() + content.substring(position + 1);
            switch (transition.getCursorMovement()) {
                case LEFT:
                    if (position != 0) {
                        --position;
                    } else {
                        content = " " + content;
                    }
                    break;
                case RIGHT:
                    ++position;
                    if (position == content.length()) {
                        content = content + " ";
                    }
                    break;
                case NONE:
                	
            }
        }
        
        public String getContent() {
            return this.content;
        }
        
        public char getCurrentSymbol() {
        	if (content.isEmpty()) {
        		content = " ";
        	}
            return this.content.charAt(position);
        }
    }

}
