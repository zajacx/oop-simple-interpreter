package instructions;

import exceptions.AlreadyDeclaredException;
import expressions.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import program.Context;
import program.ExecutionState;

@RequiredArgsConstructor
public class Declaration extends Instruction {

    @Getter
    private final Character name;

    private final Expression expression;


    @Override
    public void execute(ExecutionState state) {
        declaration(state);
    }


    @Override
    public void debug(ExecutionState state) {
        declaration(state);
        conditionalPop(state);
    }


    private void declaration(ExecutionState state) throws AlreadyDeclaredException {
        int value = expression.execute(state);
        Context context = state.getContexts().peek();
        int position = name - 'a';

        if (context.getVariables()[position] != null) {
            throw new AlreadyDeclaredException("Variable " + name + " is already declared.");
        } else {
            context.getVariables()[position] = value;
        }
    }


    @Override
    public String toString() {
        return "int " + name + " = " + expression.toString();
    }
}
