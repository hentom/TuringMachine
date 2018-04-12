package work.hennig;

import java.util.HashSet;
import java.util.Set;

import work.hennig.TuringMachine.State;
import work.hennig.TuringMachine.Transition;
import work.hennig.TuringMachine.TuringMachine;

public class MultiplicationTM {

	public static void main(String[] args) {
		// Create states q_0, q_1, ..., q_8, q_f
		State q[] = new State[9];
		for (int i = 0; i < 9; ++i) {
			q[i] = new State();
		}
		State qf = new State();
		
		// Create transitions
		Set<Transition> transitions = new HashSet<>();
		transitions.add(new Transition(q[0], '0', q[1], ' ', Transition.Direction.RIGHT));
		transitions.add(new Transition(q[0], ' ', q[8], ' ', Transition.Direction.RIGHT));
		
		transitions.add(new Transition(q[1], '0', q[1], '0', Transition.Direction.RIGHT));
		transitions.add(new Transition(q[1], ' ', q[2], ' ', Transition.Direction.RIGHT));
		transitions.add(new Transition(q[2], '0', q[3], '1', Transition.Direction.RIGHT));
		transitions.add(new Transition(q[3], '0', q[3], '0', Transition.Direction.RIGHT));
		transitions.add(new Transition(q[3], ' ', q[4], ' ', Transition.Direction.RIGHT));
		transitions.add(new Transition(q[4], '0', q[4], '0', Transition.Direction.RIGHT));
		transitions.add(new Transition(q[4], ' ', q[5], '0', Transition.Direction.LEFT));
		transitions.add(new Transition(q[5], '0', q[5], '0', Transition.Direction.LEFT));
		transitions.add(new Transition(q[5], ' ', q[5], ' ', Transition.Direction.LEFT));
		transitions.add(new Transition(q[5], '1', q[2], '0', Transition.Direction.RIGHT));
		transitions.add(new Transition(q[2], ' ', q[6], ' ', Transition.Direction.LEFT));
		
		transitions.add(new Transition(q[6], '0', q[6], '0', Transition.Direction.LEFT));
		transitions.add(new Transition(q[6], ' ', q[7], ' ', Transition.Direction.LEFT));
		transitions.add(new Transition(q[7], '0', q[7], '0', Transition.Direction.LEFT));
		transitions.add(new Transition(q[7], ' ', q[0], ' ', Transition.Direction.RIGHT));
		
		transitions.add(new Transition(q[0], ' ', q[8], ' ', Transition.Direction.RIGHT));
		transitions.add(new Transition(q[8], '0', q[8], ' ', Transition.Direction.RIGHT));
		transitions.add(new Transition(q[8], ' ', qf, ' ', Transition.Direction.RIGHT));
		
		// Create set with single final state q_f
		Set<State> finalStates = new HashSet<>();
		finalStates.add(qf);
		
		// Create TM
		TuringMachine Amul = new TuringMachine(transitions, q[0], finalStates);
		
		// Test Amul with different tapes
		System.out.println("0 = " + Amul.run().trim().length());
		System.out.println("2 * 0 = " + Amul.run("00 ").trim().length());
		System.out.println("1 * 1 = " + Amul.run("0 0").trim().length());
		System.out.println("3 * 4 = " + Amul.run("000 0000").trim().length());
		System.out.println("9 * 7 = " + Amul.run("000000000 0000000 ").trim().length());
	}

}
