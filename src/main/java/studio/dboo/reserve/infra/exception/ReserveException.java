package studio.dboo.reserve.infra.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReserveException extends Exception{
    public ReserveException(String message) {
        super(message);
    };
}
