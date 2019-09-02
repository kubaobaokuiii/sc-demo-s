package com.bocloud.boms.framework.order.controller;

/**
 * @author gs
 * @create time
 * @create 2019/7/30
 */
public class JsonResult {

    private String status = null;
    private Object result = null;

    public JsonResult() {
    }

    public String getStatus() {
        return this.status;
    }

    public Object getResult() {
        return this.result;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setResult(final Object result) {
        this.result = result;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof JsonResult)) {
            return false;
        } else {
            JsonResult other = (JsonResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                Object this$result = this.getResult();
                Object other$result = other.getResult();
                if (this$result == null) {
                    if (other$result != null) {
                        return false;
                    }
                } else if (!this$result.equals(other$result)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {

        return other instanceof JsonResult;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $result = this.getResult();
        result = result * 59 + ($result == null ? 43 : $result.hashCode());
        return result;
    }

    @Override
    public String toString() {

        return "JsonResult(status=" + this.getStatus() + ", result=" + this.getResult() + ")";
    }

}
