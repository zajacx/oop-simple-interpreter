package expressions;

import exceptions.ArithmeticErrorException;
import lombok.AllArgsConstructor;
import program.ExecutionState;

@AllArgsConstructor
public class Divide implements Expression {

    private final Expression left, right;


    @Override
    public int execute(ExecutionState state) throws ArithmeticErrorException {
        int leftValue = left.execute(state);
        int rightValue = right.execute(state);
        if(rightValue != 0) {
            return leftValue / rightValue;
        } else {
            throw new ArithmeticErrorException("You can't divide by 0.");
        }
    }

    @Override
    public String toString() {
        return left.toString() + " / " + right.toString();
    }
}
