package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.ResourceDoc;

import java.util.Map;

public interface IResourceDocService {

    int insertResourceDoc(ResourceDoc resourceDoc);

    int updateResourceDoc(ResourceDoc resourceDoc);

    Map<Object,Object> ResourceDocInTen();

    Map<Object,Object> ResourceDocPaging(int pageNum,int pageSize);

}
