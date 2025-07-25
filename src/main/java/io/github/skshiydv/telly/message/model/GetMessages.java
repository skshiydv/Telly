package io.github.skshiydv.telly.message.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetMessages {
    private String sender;
    private String receiver;
    private String message;
}
