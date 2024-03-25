package relations;
import expressions.*;
import lombok.AllArgsConstructor;
import program.ExecutionState;

@AllArgsConstructor
public class Different implements Relation {

    private final Expression left, right;


    @Override
    public boolean execute(ExecutionState executionState) {
        return left.execute(executionState) != right.execute(executionState);
    }

    @Override
    public String toString() {
        return left.toString() + " <> " + right.toString();
    }

}
