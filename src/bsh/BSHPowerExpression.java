/* Generated By:JJTree: Do not edit this line. BSHPowerExpression.java */
package bsh;

import bsh.operators.ExtendedMethod;
import bsh.operators.OperatorProvider;
import bsh.operators.OperatorType;

public class BSHPowerExpression extends SimpleNode {

    ExtendedMethod opMethod;

    BSHPowerExpression(int id) {
        super(id);
    }

    public Object eval(CallStack callstack, Interpreter interpreter)
            throws EvalError {
        
        int nchild = this.jjtGetNumChildren();
        if(nchild==1) {
            return ((SimpleNode) jjtGetChild(0)).eval(callstack, interpreter);
        }
        
        // Evaluate right to left
        
        Object lhs = ((SimpleNode) jjtGetChild(0)).eval(callstack, interpreter);
        Object rhs = ((SimpleNode) jjtGetChild(1)).eval(callstack, interpreter);

        Object lhs2 = Primitive.unwrap(lhs);
        Object rhs2 = Primitive.unwrap(rhs);
        Class type1 = (lhs2!=null)?lhs2.getClass():null;
        Class type2 = (rhs2!=null)?rhs2.getClass():null;
        OperatorType opType = OperatorType.POWER;
        opMethod = OperatorProvider.findMethod(interpreter.getNameSpace(),opType.getMethodName(), opMethod, opType.getAllowLeftCast(), type1, type2);
        if (opMethod != null) {
            Object result = opMethod.eval(lhs2, rhs2);
            if (Primitive.isWrapperType(result.getClass())) {
                return new Primitive(result);
            }
            else {
                return result;
            }
        }
        else {
            throw new EvalError("No exponentiation method found for given data types.", this, callstack);
        }
    }
}
