package program;

import instructions.Block;

public class Executor {

    private final ExecutionState state = new ExecutionState();

    public void execute(Block program) {
        program.execute(state);
    }

}
