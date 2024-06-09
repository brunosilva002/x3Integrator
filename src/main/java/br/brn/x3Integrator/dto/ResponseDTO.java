package br.brn.x3Integrator.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseDTO<T> {

    private Integer code;
    private Status status;
    private List<String> messagens = new ArrayList<>(0);
    private T data;

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getMessagens() {
        //messagens.add(Arrays.stream(Code.values()).filter(c -> c.getCode()==code).findFirst().get().getMessageCode());
        return messagens;
    }
    public void setMessagens(List<String> messagens) {
        this.messagens = messagens;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public enum Status{
        SUCCESS, ERROR, WARN
    }

    public enum Code{

        ERROR_GENERIC                           (-1),
        SUCCESS                                 (0),
        ERROR_BUSINESS_RULE                     (1);

        private Integer code = 0;

        Code(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
        public void setCode(Integer code) {
            this.code = code;
        }

    }
}
