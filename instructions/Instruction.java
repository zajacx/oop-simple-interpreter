package instructions;

import program.ExecutionState;

public abstract class Instruction {

    public abstract void execute(ExecutionState state);

    public abstract void debug(ExecutionState state);

    protected void conditionalPop(ExecutionState state) {
        if (state.getInstructions().size() <= state.getContexts().peek().getMinStackSize()) {
            state.getContexts().pop();
        }
    }

    /*
    protected int findIterator(ExecutionState state) {
        Context context = state.findContext();
    }
*/
}
