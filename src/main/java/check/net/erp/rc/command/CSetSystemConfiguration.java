package check.net.erp.rc.command;

import check.net.erp.rc.service.KeyValueService;
import check.net.erp.base.Assert;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.command.Command;
import check.net.erp.rc.bean.SystemConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 设置系统设置
 */
@Command
public class CSetSystemConfiguration extends BaseCommand {

    @Autowired
    private KeyValueService kvService;

    @Param(name = "configuration", required = true)
    private SystemConfiguration configuration; // 配置对象

    @Override
    protected void init() throws Exception {
        Assert.notBlank(configuration.getCompanyName(), "公司名称不能为空！");
        Assert.notBlank(configuration.getCompanyAddress(), "公司地址不能为空！");
    }

    @Override
    protected void doCommand() throws Exception {
        SystemConfiguration persistedConfiguration = kvService.getSystemConfiguration();

        persistedConfiguration.setCompanyName(configuration.getCompanyName());
        persistedConfiguration.setCompanyAddress(configuration.getCompanyAddress());
        persistedConfiguration.setCompanyPhone(configuration.getCompanyPhone());
        persistedConfiguration.setCompanyFax(configuration.getCompanyFax());
        persistedConfiguration.setCompanyPostCode(configuration.getCompanyPostCode());
        persistedConfiguration.setQuantityPrecision(configuration.getQuantityPrecision());
        persistedConfiguration.setPricePrecision(configuration.getPricePrecision());
        persistedConfiguration.setCheckNegativeStock(configuration.isCheckNegativeStock());

        kvService.setSystemConfiguration(persistedConfiguration);

        data.put("configuration", persistedConfiguration);
    }
}
