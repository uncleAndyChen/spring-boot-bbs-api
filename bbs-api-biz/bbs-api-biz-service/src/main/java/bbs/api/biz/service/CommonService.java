package bbs.api.biz.service;

import bbs.api.biz.model.view.GlobalView;

public class CommonService {
    public static int removeGlobalIdPrefixAndConvertToInt(String id) {
        return Integer.parseInt(id.replace(GlobalView.idPrefix, ""));
    }
}
