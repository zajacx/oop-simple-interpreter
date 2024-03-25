package instructions;

import expressions.*;
import lombok.AllArgsConstructor;
import program.*;

@AllArgsConstructor
public class Print extends Instruction {

    private final Expression expression;


    @Override
    public void execute(ExecutionState state) {
        print(state);
    }


    @Override
    public void debug(ExecutionState state) {
        print(state);
        conditionalPop(state);
    }

    private void print(ExecutionState state) {
        System.out.println(expression.execute(state));
    }


    @Override
    public String toString() {
        return "print " + expression.toString();
    }
}
