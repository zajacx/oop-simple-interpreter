package instructions;

import exceptions.UndefinedVariableException;
import expressions.*;
import lombok.AllArgsConstructor;
import program.Context;
import program.ExecutionState;

@AllArgsConstructor
public class Set extends Instruction {

    private final Character name;

    private final Expression expression;


    @Override
    public void execute(ExecutionState state) throws UndefinedVariableException {
        set(state);
    }


    @Override
    public void debug(ExecutionState state) {
        set(state);
        conditionalPop(state);
    }


    private void set(ExecutionState state) {
        int value = expression.execute(state);
        int position = name - 'a';
        Context context = state.findContext(position);

        if (context == null) {
            throw new UndefinedVariableException("Variable named " + name + " is undefined.");
        } else {
            context.getVariables()[position] = value;
        }
    }


    @Override
    public String toString() {
        return name.toString() + " = " + expression.toString();
    }

}
