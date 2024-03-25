package instructions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import program.*;

@AllArgsConstructor
public class Block extends Instruction {

    @Getter
    private final Instruction[] instructions;


    @Override
    public void execute(ExecutionState state) {

        // First instructions are always declarations of variables - we can create a new context:
        state.getContexts().push(new Context());


        // Pushing all instruction to the stack to be able to stack new blocks, for and if's on top of it:
        for (int i = instructions.length - 1; i >= 0; i--) {
            state.getInstructions().push(instructions[i]);
        }

        // Execution:
        for (int i = 0; i < instructions.length; i++) {
            Instruction instruction = state.getInstructions().pop();
            instruction.execute(state);
        }

        // Popping a context:
        state.getContexts().pop();
    }


    @Override
    public void debug(ExecutionState state) {

        // Before invoking this method we pop "Block" from instructions' stack, but we don't add any
        // instructions from the block's body, so our stack has a size of:

        int minStackSize = state.getInstructions().size();
        state.getContexts().push(new Context(minStackSize));

        for (int i = instructions.length - 1; i >= 0; i--) {
            state.getInstructions().push(instructions[i]);
        }

    }


    @Override
    public String toString() {
        return "block";
    }
}
