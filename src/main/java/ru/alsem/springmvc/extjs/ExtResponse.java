package ru.alsem.springmvc.extjs;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Base class which for a minimal payload to be returned
 */
public class ExtResponse {

    public final static ExtResponse SUCCESS = new ExtResponse(true, null);

    protected String message = null;

    protected boolean success = false;

    public ExtResponse() {
    }

    public ExtResponse(boolean success) {
        this(success, null);
    }

    public ExtResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}