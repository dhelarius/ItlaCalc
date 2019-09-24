package com.itla.itlacalc;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class Operation {

    private String expression;

    Operation(String expression){
        this.expression = expression;
    }

    private Number getResult(String expression){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("rhino");

        try {
            Object operation = engine.eval(expression);

            if (operation instanceof Double) {
                return (Double) operation;
            }
            if (operation instanceof Integer) {
                return (Integer) operation;
            }
        }catch(ScriptException e){
            return null;
        }

        return null;
    }

    Number getResult(){
        return getResult(expression);
    }

}
