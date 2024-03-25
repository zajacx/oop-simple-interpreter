package program;

import exceptions.IllegalValueException;
import instructions.Block;
import instructions.Instruction;

import java.util.*;

public class Debugger{

    private final ExecutionState state = new ExecutionState();
    public void debug(Block program) throws IllegalValueException {

        boolean ok = true;
        program.debug(state);

        while(ok) {

            char instruction = scanInstruction();

            if (instruction == 'c') {
                parseContinue(state);
                ok = false;
            } else if (instruction == 's') {
                ok = parseStep(state);
            } else if (instruction == 'd') {
                parseDisplay(state);
            } else if (instruction == 'e') {
                ok = false;
            } else {
                System.out.println("There is no instruction named " + instruction + "!");
            }
        }
    }

    private void parseContinue(ExecutionState state) {
        while (!state.getInstructions().isEmpty()) {
            Instruction newInstruction = state.getInstructions().pop();
            newInstruction.execute(state);
        }
    }

    private boolean parseStep(ExecutionState state) {
        int steps = scanNumber();
        if (steps <= 0) {
            System.out.println("Number of steps must be greater than 0.");
            return true;
        } else {
            for (int i = 0; i < steps; i++) {
                // Popping and executing single instruction from stack counts as one step.
                Instruction newInstruction = state.getInstructions().pop();
                newInstruction.debug(state);
                if(state.getInstructions().isEmpty()) {
                    return false;
                }
            }
            System.out.println(state.getInstructions().peek().toString());
            return true;
        }
    }

    private void parseDisplay(ExecutionState state) {
        System.out.println("Choose level from 0 to " + (state.getContexts().size() - 1));
        int levels = scanNumber();
        if (levels >= 0 && levels < state.getContexts().size()) {
            Integer[] values = new Integer[26];
            int levelNumber = 0;
            for (Context context : state.getContexts()) {
                Integer[] level = context.getVariables();
                if (levelNumber >= levels) {
                    for (int i = 0; i < 26; i++) {
                        if (values[i] == null && level[i] != null) {
                            values[i] = level[i];
                        }
                    }
                }
                levelNumber++;
            }
            for(int i = 0; i < 26; i++) {
                if(values[i] != null) {
                    char name = (char) ('a' + i);
                    System.out.println(name + " == " + values[i]);
                }
            }
        } else {
            System.out.println("Max level is " + (state.getContexts().size() - 1));
        }
    }

    private char scanInstruction() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        while (input.length() != 1) {
            System.out.println("There is no instruction named " + input + "!");
            input = scanner.next();
        }

        return input.charAt(0);
    }

    private int scanNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}