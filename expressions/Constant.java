package expressions;

import lombok.AllArgsConstructor;
import program.ExecutionState;

@AllArgsConstructor
public class Constant implements Expression {

    private final Integer value;

    @Override
    public int execute(ExecutionState state) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
