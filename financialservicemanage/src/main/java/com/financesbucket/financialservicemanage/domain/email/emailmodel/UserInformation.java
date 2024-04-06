package com.financesbucket.financialservicemanage.domain.email.emailmodel;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation {

    private String username;
    private String email;

}