package program;

import exceptions.UndefinedVariableException;
import instructions.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Stack;

@NoArgsConstructor
public class ExecutionState {

    // STACKS:

    @Getter
    private final Stack<Context> contexts = new Stack<>();

    @Getter
    private final Stack<Instruction> instructions = new Stack<>();


    public int getValue(Character name) throws UndefinedVariableException {
        int position = name - 'a';
        Context context = findContext(position);
        if (context == null) {
            throw new UndefinedVariableException("Variable named " + name + " is undefined.");
        } else {
            return context.getVariables()[position];
        }
    }

    public Context findContext(int position) {
        for (Context context : contexts) {
            if (context.getVariables()[position] != null) {
                return context;
            }
        }
        return null;
    }


}
