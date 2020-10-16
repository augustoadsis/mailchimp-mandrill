package com.augusto.mandrill.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String text;
    private String subject;
    private String from_email;
    private String from_name;
    private List<To> to;
    private String bcc_address;
    private List<GlobalMergeVar> global_merge_vars;
    private String merge_language = "mailchimp";
}
