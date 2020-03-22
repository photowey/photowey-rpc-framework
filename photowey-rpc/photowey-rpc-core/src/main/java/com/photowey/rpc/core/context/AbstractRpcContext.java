package com.photowey.rpc.core.context;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * AbstractRpcContext
 *
 * @author WcJun
 * @date 2020/02/21
 * @since v1.0.0
 */
public abstract class AbstractRpcContext implements RpcContext {

    protected Map<String, Object> context = new HashMap<>();

    protected ExpressionParser expressionParser;

    protected EvaluationContext evaluationContext;

    public AbstractRpcContext() {
        // add header-container to context
        Map<String, Object> headers = new HashMap<>();
        this.context.put(HEADER_CONTAINER_NAME, headers);
    }

    // -----------------------------------------------------------------------------------

    /**
     * parse the expression
     *
     * @param express the spring SpEL Expression
     * @param type    Class
     * @param <T>     T
     * @return T
     */
    public <T> T doParse(String express, Class<T> type) {
        return this.expressionParser.parseExpression(express).getValue(this.evaluationContext, type);
    }
    // -----------------------------------------------------------------------------------

    @Override
    public <T> void addHeader(String key, T value) {
        Map<String, T> headers = this.getHeaders();
        headers.put(key, value);
    }

    @Override
    public <T> T getHeader(String key) {
        Map<String, T> headers = this.getHeaders();
        return headers.get(key);
    }

    @Override
    public <T> Map<String, T> getHeaders() {
        Map<String, Object> headers = (Map<String, Object>) this.context.get(HEADER_CONTAINER_NAME);

        return (Map<String, T>) headers;
    }

    @Override
    public <T> T getAttribute(String key) {
        return (T) this.context.get(key);
    }

    @Override
    public <T> void setAttribute(String key, T value) {
        this.context.put(key, value);
    }

    @Override
    public <T> Map<String, T> getContext() {
        return (Map<String, T>) this.context;
    }

    // -----------------------------------------------------------------------------------

    /**
     * get value by key
     *
     * @param key      key
     * @param supplier {@link Supplier}
     * @param <T>
     * @return value
     */
    @Override
    public <T> T getAttribute(String key, Supplier<T> supplier) {
        if (this.context.containsKey(key)) {
            return (T) this.context.get(key);
        }
        T target = supplier.get();
        this.context.put(key, target);

        return target;
    }

    // -----------------------------------------------------------------------------------

    public ExpressionParser getExpressionParser() {
        return expressionParser;
    }

    public void setExpressionParser(ExpressionParser expressionParser) {
        this.expressionParser = expressionParser;
    }

    public EvaluationContext getEvaluationContext() {
        return evaluationContext;
    }

    public void setEvaluationContext(EvaluationContext evaluationContext) {
        this.evaluationContext = evaluationContext;
    }

    // -----------------------------------------------------------------------------------
}
