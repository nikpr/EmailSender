package domain.events;

/**
 *
 * @author Nik
 */
public class StatusChangedEvent {

    private String statusText;
    private Boolean isError;

    public StatusChangedEvent(String statusText, Boolean isError) {
        this.statusText = statusText;
        this.isError = isError;
    }

    public StatusChangedEvent() {
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Boolean getIsError() {
        return isError;
    }

    public void setIsError(Boolean isError) {
        this.isError = isError;
    }

}
