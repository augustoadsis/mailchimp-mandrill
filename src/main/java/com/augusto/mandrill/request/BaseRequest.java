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
public class BaseRequest {
    String key;
    String template_name;
    List<TemplateContent> template_content;
    Message message;
    boolean async = true;
}
