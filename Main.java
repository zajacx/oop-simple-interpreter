import expressions.*;
import expressions.Variable;
import program.*;
import instructions.*;
import relations.*;

public class Main {
    public static void main(String[] args) {

        Block program =
                new Block(new Instruction[]{
                        new Declaration('n', new Constant(30)),
                        new For('k', new Substract(new Variable('n'), new Constant(1)), new Instruction[]{
                                new Block(new Instruction[]{
                                        new Declaration('p', new Constant(1)),
                                        new Set('k', new Add(new Variable('k'), new Constant(2))),
                                        new For('i', new Substract(new Variable('k'), new Constant(2)), new Instruction[]{
                                                new Set('i', new Add(new Variable('i'), new Constant(2))),
                                                new If(new Equal(new Modulo(new Variable('k'), new Variable('i')), new Constant(0)), new Instruction[]{
                                                        new Set('p', new Constant(0))
                                                })
                                        }),
                                        new If(new Equal(new Variable('p'), new Constant(1)), new Instruction[]{
                                                new Print(new Variable('k'))
                                        })
                                })
                        })
                });


        // Manipulate this variable to enable debugger:
        boolean debug = false;

        if(debug) {
            Debugger debugger = new Debugger();
            debugger.debug(program);
        } else {
            Executor executor = new Executor();
            executor.execute(program);
        }

    }
}