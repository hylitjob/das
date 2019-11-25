package com.ppdai.das.console.dao.base;

import com.ppdai.das.client.DasClient;
import com.ppdai.das.client.DasClientFactory;
import com.ppdai.das.console.common.utils.ResourceUtil;
import com.ppdai.das.console.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class DasClientGetter {

    private static DasClient client;

    public synchronized static DasClient getClient(boolean isLoaderFile) {
        if (null == client) {
            try {
                if (isLoaderFile) {
                    ResourceUtil.getSingleInstance().resetDalConfigUrl();
                }
                client = DasClientFactory.getClient(ResourceUtil.DATA_SET_BASE);
            } catch (SQLException e) {
                log.error(StringUtil.getMessage(e));
            }
        }
        return client;
    }

}
