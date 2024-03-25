package expressions;

import lombok.AllArgsConstructor;
import program.ExecutionState;

@AllArgsConstructor
public class Add implements Expression {

    private final Expression left, right;


    @Override
    public int execute(ExecutionState state) {
        return left.execute(state) + right.execute(state);
    }

    @Override
    public String toString() {
        return left.toString() + " + " + right.toString();
    }

}
