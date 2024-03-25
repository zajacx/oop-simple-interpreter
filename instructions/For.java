package instructions;
import expressions.*;
import expressions.Variable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import program.*;

@AllArgsConstructor
public class For extends Instruction {

    private final Character iterator;

    private final Expression range;

    @Getter
    private final Instruction[] instructions;


    @Override
    public void execute(ExecutionState state) {

        // Implicit block's context (only to add iterator):
        Context context = new Context();
        int position = iterator - 'a';
        context.getVariables()[position] = 0;

        // Pushing the new context:
        state.getContexts().push(context);

        // Calculating range:
        int rangeValue = range.execute(state);

        // Internal iterator to minimize operations on stack, finding iterator etc.
        int n = 0;

        while (n < rangeValue) {

            // We have to remember iterator's value before the instructions, because it can change:
            int iteratorValue = context.getVariables()[position];

            for (int i = instructions.length - 1; i >= 0 ; i--){
                state.getInstructions().push(instructions[i]);
            }

            for (int i = 0; i < instructions.length; i++) {
                Instruction instruction = state.getInstructions().pop();
                instruction.execute(state);
            }

            // Get iterator's value back:
            context.getVariables()[position] = iteratorValue + 1;

            n++;
        }

        state.getContexts().pop();

    }


    @Override
    public void debug(ExecutionState state) {

        int minStackSize = state.getInstructions().size();
        Context context = new Context(minStackSize);
        int position = iterator - 'a';
        context.getVariables()[position] = 0;
        state.getContexts().push(context);

        int rangeValue = range.execute(state);

        for(int j = 0; j < rangeValue; j++) {
            for (int i = instructions.length - 1; i >= 0 ; i--){
                state.getInstructions().push(instructions[i]);
            }
            // Iterator "remote" incrementation:
            state.getInstructions().push(new Set(iterator, new Add(new Variable(iterator), new Constant(1))));
        }

        conditionalPop(state);
    }


    @Override
    public String toString() {
        return "for (int " + iterator + "; " + iterator + " < " + range.toString() + "; " + iterator + "++)";
    }

}
