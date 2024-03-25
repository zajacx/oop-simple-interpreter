package instructions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import program.ExecutionState;
import relations.*;

@AllArgsConstructor
public class If extends Instruction {

    public Relation relation;

    @Getter
    public Instruction[] instructions;


    @Override
    public void execute(ExecutionState state) {

        boolean condition = relation.execute(state);

        if (condition) {

            for (int i = instructions.length - 1; i >= 0 ; i--){
                state.getInstructions().push(instructions[i]);
            }

            for (int i = 0; i < instructions.length; i++) {
                Instruction instruction = state.getInstructions().pop();
                instruction.execute(state);
            }

        }

    }

    @Override
    public void debug(ExecutionState state) {

        boolean condition = relation.execute(state);

        if (condition) {
            for (int i = instructions.length - 1; i >= 0; i--) {
                state.getInstructions().push(instructions[i]);
            }
        }

        conditionalPop(state);
    }


    @Override
    public String toString() {
        return "if (" + relation.toString() + ")";
    }

}
