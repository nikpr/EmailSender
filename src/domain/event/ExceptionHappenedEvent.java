/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.events;

/**
 *
 * @author Nik
 */
public class ExceptionHappenedEvent {
    private Exception exception;

    public ExceptionHappenedEvent(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    
}
