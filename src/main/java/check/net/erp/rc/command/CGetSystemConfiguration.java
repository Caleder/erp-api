package check.net.erp.rc.command;

import check.net.erp.rc.service.KeyValueService;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.command.Command;
import check.net.erp.rc.bean.SystemConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 获取系统设置
 */
@Command
public class CGetSystemConfiguration extends BaseCommand {

    @Autowired
    private KeyValueService kvService;

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        SystemConfiguration configuration = kvService.getSystemConfiguration();

        data.put("configuration", configuration);
    }
}
