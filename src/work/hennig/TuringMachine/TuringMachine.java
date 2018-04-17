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
        
        // execute transitions as long as possible
        for (
        		Transition executableTransition = findExecutableTransition(tape, currentState);
        		executableTransition != null;
        		executableTransition = findExecutableTransition(tape, currentState)) {
        	tape.executeTransition(executableTransition);
        	currentState = executableTransition.getNewState();
        }
        
        // check whether last state is a final state
        if (!finalStates.contains(currentState)) {
        	throw new RuntimeException("Turing machine is rejecting");
        }
        
        return tape.getContent();
    }
    
    private Transition findExecutableTransition(Tape tape, State state) {
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
            return transition;
        }
    	return null;
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
