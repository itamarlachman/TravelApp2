package il.co.examplefinalproject2.interfaces;

public class DataResult {
    public enum Operation {
        None,
        Login,
        Register,
        Add,
        Update,
        Select;
    }

    public Exception getException() {
        return ex;
    }

    public enum Entity {
        None,
        Travels,
        Companies
    }

    protected Operation operation = Operation.None;
    protected Entity entity = Entity.None;
    protected boolean isSuccess = false;
    protected Object Result;
    protected Exception ex;

    public DataResult() { }

    public Operation getOperation() {
        return operation;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public DataResult(Operation operation, Entity entity, Boolean isSuccess,Object result, Exception ex) {
        this.operation = operation;
        this.entity = entity;
        this.isSuccess = isSuccess;
        this.Result = result;
        this.ex = ex;

    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Object getResult() {
        return Result;
    }

    public void setResult(Object result) {
        Result = result;
    }
}
