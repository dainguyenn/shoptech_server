package com.dn.shoptech.funct.pageable;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@Component
public class ResponsePage {

    public Map<String, Object> response(Page page){
        Map<String,Object> data = new HashMap<>();
        data.put("content",page.getContent());
        data.put("totalPages",page.getTotalPages());
        data.put("currentPage",page.getNumber()+1);
        data.put("totalItems",page.getTotalElements());

        return data;
    }
}
