package relations;
import expressions.*;
import lombok.AllArgsConstructor;
import program.ExecutionState;

@AllArgsConstructor
public class LessEqual implements Relation {

    private final Expression left, right;


    @Override
    public boolean execute(ExecutionState state) {
        return left.execute(state) <= right.execute(state);
    }

    @Override
    public String toString() {
        return left.toString() + " <= " + right.toString();
    }

}
